package com.EnergyProject.server;

import com.EnergyProject.dao.ZoneDAO;
import com.EnergyProject.dao.ZoneDAOForNoCallable;
import com.EnergyProject.pojo.ProAmount;
import com.EnergyProject.pojo.Zone;
import com.EnergyProject.utils.SelectMode;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

@Service
@Slf4j
public class SseEmitterService {
    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Autowired
    private ZoneDAO zoneDAO;
    @Autowired
    private ZoneServer zoneServer;
    @Autowired
    private SelectMode selectMode;
    @Autowired
    private ProAmountServer proAmountServer;

    private static Integer integer=0;
    private static Integer sqlservercount=0;

    private static List<Integer> totalValue=new ArrayList<>();
    private Integer totalNum=0;
    private LocalDateTime nowDateTime;
    private List<String> ssesourceIdNumForZero=new ArrayList<>();
    private List<String> filterSseEmitter=new ArrayList<>();
    private List<Zone> searchZone=new ArrayList<>();
    private List<Zone> searchZoneForDay=new ArrayList<>();
    private HashSet<String> sseEmitterKeySet=new HashSet<>();
    private HashMap<String, Object> stringListHashMap = new HashMap<>();
    private Boolean isQuickEntry=false;

    private ArrayList<String> totalModeForHours = new ArrayList<>();
    private ArrayList<String> totalModeForDays = new ArrayList<>();
    private ArrayList<String> noTotalModeForHours = new ArrayList<>();
    private ArrayList<String> noTotalModeForDays = new ArrayList<>();


    @Async
    public void sends(String id, ConcurrentHashMap<String,SseEmitter> concurrentHashMap)
    {
        System.out.println(threadPoolTaskExecutor.getActiveCount());
        System.out.println(Thread.currentThread().getName());
        Zone zone= new Zone();
        zone.setEid(1);
        zone.settTime(LocalDateTime.now());
        zone.settValue(12343);
        SseEmitter sseEmitter = concurrentHashMap.get(id);
        try {
            sseEmitter.send(SseEmitter.event().id(Integer.toString(integer)).data(zone));
            //sseEmitter.send(SseEmitter.event().id(Integer.toString(integer)).name("message").data(employee));
            integer+=1;
            //sseEmitter.send(employee, MediaType.APPLICATION_JSON);

        } catch (IOException e) {
            e.printStackTrace();
            sseEmitter.completeWithError(e); }
    }


  @Async
  public void sqlservertransferDao(Map<String,SseEmitter> sseEmitterMap,Map<String,Integer> integerMap ,Map<String,String>modeMap,Map<String,String>selectModes,Map<String,Integer>noTotalForEid,Map<String,Integer> nototalForNode,boolean global)  {

      modeMap.forEach((key,value)->{
          if ("total".equals(value))
          {
              if ("hours".equals(selectModes.get(key)))
              {
                  totalModeForHours.add(key);
              }
              else
              {
                  totalModeForDays.add(key);
              }
          }
          else
          {
              if ("hours".equals(selectModes.get(key)))
              {
                  noTotalModeForHours.add(key);
              }
              else
              {
                  noTotalModeForDays.add(key);
              }
          }
      });


      if (sseEmitterMap.size() != 0 )
      {
         final Map<String, Object> totalHours=new HashMap<>();
         final Map<String, Object> totalDays=new HashMap<>();
          integerMap.forEach((key, value) -> {
              if (value == 0) {
                  ssesourceIdNumForZero.add(key);
                  filterSseEmitter.add(key);
                  integerMap.put(key, 1);
              }
          });
          List<Zone> everyZoneData=zoneServer.getsqlserverSelectAllLastZone();
          stringListHashMap.put("every",everyZoneData);
          if (!ssesourceIdNumForZero.isEmpty())
          {
              HashMap<String, Map<String, Object>> sendToSseemiter = new HashMap<>();
              Set<Map.Entry<String, SseEmitter>> entries = sseEmitterMap.entrySet();
              entries.forEach(value->{
                  sseEmitterKeySet.add(value.getKey());
              });

              HashMap<String, Object> stringObjectHashMap = new HashMap<>();
              LocalDateTime now = LocalDateTime.now();
              //int year = now.getYear();
              //int monthValue = now.getMonthValue();
              //int dayOfMonth = now.getDayOfMonth();

              int year = now.getYear();
              int monthValue = 6;
              int dayOfMonth = 28;
              ssesourceIdNumForZero.forEach(iteam->{
                  if (totalModeForHours.contains(iteam))
                  {
                      if (totalHours.isEmpty())
                      {
                          totalHours.putAll(selectDataOfSseOnMode(year, monthValue, dayOfMonth, true, "hours", null, null, false));
                      }
                      sendToSseemiter.put(iteam,totalHours);
                  }
                  else if (totalModeForDays.contains(iteam))
                  {
                      if (totalDays.isEmpty())
                      {
                          totalDays.putAll(selectDataOfSseOnMode(year, monthValue, dayOfMonth, true, "days", null, null, false));
                      }

                      sendToSseemiter.put(iteam,totalDays);
                  }
                  else if (noTotalModeForHours.contains(iteam))
                  {
                      Map<String, Object> hours = selectDataOfSseOnMode(year, monthValue, dayOfMonth, false, "hours", noTotalForEid.get(iteam), null, false);
                      sendToSseemiter.put(iteam,hours);

                  }
                  else if (noTotalModeForDays.contains(iteam))
                  {
                      Map<String, Object> hours = selectDataOfSseOnMode(year, monthValue, dayOfMonth, false, "days", noTotalForEid.get(iteam), null, false);
                      sendToSseemiter.put(iteam,hours);
                  }
              });

              /*LocalDateTime of = LocalDateTime.of(year, 6,28, 0, 0, 0);
              stringObjectHashMap.put("starttime", of);
              stringObjectHashMap.put("eid", 26);
              stringObjectHashMap.put("endTime", LocalDateTime.of(year, 6,28, 23, 50, 0));
              stringObjectHashMap.put("addtime", 1);
              searchZone.addAll(zoneServer.getzonelistForCUROS(stringObjectHashMap));
              HashMap<String, Object> stringObjectHashMapForDay = new HashMap<>();

                  stringObjectHashMapForDay.put("starttime", LocalDateTime.of(year, monthValue, 1, 0, 0, 0));
                  stringObjectHashMapForDay.put("eid", 26);
                  stringObjectHashMapForDay.put("endTime", LocalDateTime.of(year, monthValue+1,1, 0, 0, 0));
                  stringObjectHashMapForDay.put("addtime", 1);
                  searchZoneForDay.addAll(zoneServer.getzonelistForCUROSForDay(stringObjectHashMapForDay));

              stringListHashMap.put("hh",searchZone);
              stringListHashMap.put("dd",searchZoneForDay);*/

              Iterator<String> iterator = ssesourceIdNumForZero.iterator();
              String next="";
              while (iterator.hasNext()){
                  try {
                       next = iterator.next();
                      sseEmitterMap.get(next).send(SseEmitter.event().id(Integer.toString(integerMap.get(next))).data(sendToSseemiter.get(next)));
                      integerMap.put(next,integerMap.get(next)+1);

                  }
                  catch (IOException e) {
                      e.printStackTrace();
                      sseEmitterMap.get(next).complete();
                      sseEmitterMap.remove(next);
                      integerMap.remove(next);
                      totalModeForDays.remove(next);
                      totalModeForHours.remove(next);
                      noTotalModeForDays.remove(next);
                      noTotalModeForHours.remove(next);

                      modeMap.remove(next);
                      selectModes.remove(next);
                      noTotalForEid.remove(next);
                      nototalForNode.remove(next);

                  }
                  finally {
                      sseEmitterKeySet.remove(next);
                      iterator.remove();
                  }

              }
             /* searchZone.clear();
              searchZoneForDay.clear();*/

              if (sseEmitterKeySet.size()!=0)
              {
                  isQuickEntry=true;
                  sseEmitterKeySet.clear();
              }
              else
              {
                  isQuickEntry=false;
                  filterSseEmitter.clear();
                  everyZoneData.clear();
              }

          }
          else
          {
              isQuickEntry=true;
          }
          if ( isQuickEntry && global )
          {
              HashMap<String, Object> storageTotalMode = new HashMap<>();

              HashMap<String, Map<String, Object>> sendToSseemiter = new HashMap<>();
              if (totalModeForHours.size()!=0)
              {

                  selectDataForTotalMode("hours",storageTotalMode,sendToSseemiter);
              }
               if (noTotalModeForHours.size()!=0)
              {

                  selectDataForNoTotalMode("hours",noTotalForEid,sendToSseemiter);
              }

              //Zone zone = zoneDAO.selectTotalZone();
              //zones.add(zone);

              //stringListHashMap.put("hh",zones);
              //stringListHashMap.remove("dd");
              if (!filterSseEmitter.isEmpty())
              {
                  sseEmitterMap.forEach((key, value) -> {
                      try {
                          if (!filterSseEmitter.contains(key))
                          {
                              if (totalModeForHours.contains(key) || noTotalModeForHours.contains(key))
                              {
                                  value.send(SseEmitter.event().id(Integer.toString(integerMap.get(key))).data(sendToSseemiter.get(value), MediaType.APPLICATION_JSON));
                                  integerMap.put(key,integerMap.get(key)+1);
                              }

                          }


                      } catch (IOException e) {
                          e.printStackTrace();
                          log.info(e.getMessage());
                          value.complete();
                          sseEmitterMap.remove(key);
                          integerMap.remove(key);
                          totalModeForDays.remove(key);
                          totalModeForHours.remove(key);
                          noTotalModeForDays.remove(key);
                          noTotalModeForHours.remove(key);
                          modeMap.remove(key);
                          selectModes.remove(key);
                          noTotalForEid.remove(key);
                          nototalForNode.remove(key);
                      }
                  });
                  filterSseEmitter.clear();
              }
              else
              {
                  sseEmitterMap.forEach((key, value) -> {
                      try {
                          if (totalModeForHours.contains(key) || noTotalModeForHours.contains(key))
                          {
                              value.send(SseEmitter.event().id(Integer.toString(integerMap.get(key))).data(sendToSseemiter.get(value), MediaType.APPLICATION_JSON));
                              integerMap.put(key,integerMap.get(key)+1);
                          }


                      } catch (IOException e) {
                          e.printStackTrace();
                          log.info(e.getMessage());
                          value.complete();
                          sseEmitterMap.remove(key);
                          integerMap.remove(key);
                          totalModeForDays.remove(key);
                          totalModeForHours.remove(key);
                          noTotalModeForDays.remove(key);
                          noTotalModeForHours.remove(key);
                          modeMap.remove(key);
                          selectModes.remove(key);
                          noTotalForEid.remove(key);
                          nototalForNode.remove(key);
                      }
                  });
              }
              everyZoneData.clear();

          }
          //sendEvery(sseEmitterMap,integerMap);
          }
      }

      public void  sendEvery(Map<String,SseEmitter> sseEmitterMap,Map<String,Integer> integerMap)
      {
          List<Zone> everyZoneData=zoneServer.getsqlserverSelectAllLastZone();
          stringListHashMap.put("every",everyZoneData);
          sseEmitterMap.forEach((key,value)->{
              try {
                  value.send(SseEmitter.event().id(Integer.toString(integerMap.get(key))).data(stringListHashMap, MediaType.APPLICATION_JSON));
              } catch (IOException e) {
                  e.printStackTrace();
                  value.complete();
                  sseEmitterMap.remove(key);
                  integerMap.remove(key);
              }
          });
      }

      public void selectDataForTotalMode(String mode,Map<String,Object> storageTotalMode,Map<String,Map<String,Object>> sendToSseemiter)
      {
          Zone selectSingleZoneForT3 = zoneServer.getSelectSingleZone(26);
          Zone selectSingleZoneForT4 = zoneServer.getSelectSingleZone(27);
          Zone totalZone = new Zone(selectSingleZoneForT3.getEid(), selectSingleZoneForT3.gettValue() + selectSingleZoneForT4.gettValue(), selectSingleZoneForT3.gettTime());
          ProAmount singleProAmount = proAmountServer.getSingleProAmount(5);
          storageTotalMode.put("T3",selectSingleZoneForT3);
          storageTotalMode.put("T4",selectSingleZoneForT4);
          storageTotalMode.put("total",totalZone);
          storageTotalMode.put("amount",singleProAmount);
          if ("hours".equals(mode))
          {
              totalModeForHours.forEach(value->{
                  sendToSseemiter.put(value,storageTotalMode);
              });
          }
          else
          {
              totalModeForDays.forEach(iteam->{
                  sendToSseemiter.put(iteam,storageTotalMode);
              });
          }

      }
      public void selectDataForNoTotalMode(String mode,Map<String,Integer>noTotalForEid,Map<String,Map<String,Object>> sendToSseemiter)
      {
          if ("hours".equals(mode))
          {
              noTotalModeForHours.forEach(value->{
                  Zone selectSingleZone = zoneServer.getSelectSingleZone(noTotalForEid.get(value));
                  ArrayList<Zone> zones = new ArrayList<>(1);
                  HashMap<String, Object> storageNoTatalMode = new HashMap<>();
                  zones.add(selectSingleZone);
                  storageNoTatalMode.put("single",zones);
                  sendToSseemiter.put(value,storageNoTatalMode);

              });
          }
          else
          {
              noTotalModeForDays.forEach(iteam->{
                  Zone selectSingleZone = zoneServer.getSelectSingleZone(noTotalForEid.get(iteam));
                  ArrayList<Zone> zones = new ArrayList<>(1);
                  HashMap<String, Object> storageNoTatalMode = new HashMap<>();
                  zones.add(selectSingleZone);
                  storageNoTatalMode.put("single",zones);
                  sendToSseemiter.put(iteam,storageNoTatalMode);
              });
          }

      }

      public void sendSseEmitterControllerDataForDay(Map<String,Integer>noTotalForEid,Map<String,SseEmitter> sseEmitterMap,Map<String,Integer> integerMap,Map<String,String>modeMap,Map<String,String>selectModes,Map<String,Integer>nototalForNode)
      {
          HashMap<String, Object> storageTotalMode = new HashMap<>();

          HashMap<String, Map<String, Object>> sendToSseemiter = new HashMap<>();
          if (totalModeForDays.size()!=0)
          {
              selectDataForTotalMode("days",storageTotalMode,sendToSseemiter);
          }
          if (noTotalModeForDays.size()!=0)
          {
              selectDataForNoTotalMode("days",noTotalForEid,sendToSseemiter);
          }
          sseEmitterMap.forEach((key, value) -> {
              try {
                  if (totalModeForDays.contains(key) || noTotalModeForDays .contains(key))
                  {
                      value.send(SseEmitter.event().id(Integer.toString(integerMap.get(key))).data(sendToSseemiter.get(key)));
                      integerMap.put(key,integerMap.get(key)+1);
                  }

              } catch (IOException e) {
                  e.printStackTrace();
                  value.complete();
                  sseEmitterMap.remove(key);
                  integerMap.remove(key);
                  totalModeForDays.remove(key);
                  totalModeForHours.remove(key);
                  noTotalModeForDays.remove(key);
                  noTotalModeForHours.remove(key);
                  modeMap.remove(key);
                  selectModes.remove(key);
                  noTotalForEid.remove(key);
                  nototalForNode.remove(key);
              }
          });
      }
      public void closeSseEmitter(String id,Map<String,SseEmitter> sseEmitterMap,Map<String,Integer> integerMap ,Map<String,String>modeMap,Map<String,String>selectModes,Map<String,Integer>noTotalForEid,Map<String,Integer> nototalForNode)
      {

              SseEmitter sseEmitter = sseEmitterMap.get(id);
              sseEmitter.complete();
              sseEmitterMap.remove(id);
              integerMap.remove(id);
              modeMap.remove(id);
              selectModes.remove(id);
              noTotalForEid.remove(id);
              nototalForNode.remove(id);
              totalModeForDays.remove(id);
              totalModeForHours.remove(id);
              noTotalModeForDays.remove(id);
              noTotalModeForHours.remove(id);



      }
      public Map<String,Object>selectDataOfSseOnMode(Integer year,Integer monthValue,Integer dayOfMonth,Boolean isTotalMode,String selectMode,@Nullable Integer eid,@Nullable Integer node,@Nullable Boolean isSelectAmount)
      {
          HashMap<String, Object> outputData = new HashMap<>();
          HashMap<String, Object> stringObjectHashMap = new HashMap<>();
          List<ProAmount> proAmounts = null;
          List<Zone> zoneForT3=null;
          List<Zone> zonesForT4=null;
          List<Zone> totalZones=new ArrayList<>();
          List<Zone> selectSingleZones=null;
          if (isTotalMode)
          {
              if ("hours".equals(selectMode))
              {
                  stringObjectHashMap.put("starttime", LocalDateTime.of(year, monthValue,dayOfMonth, 0, 0, 0));
                  stringObjectHashMap.put("eid", 26);
                  stringObjectHashMap.put("endTime", LocalDateTime.of(year, monthValue,dayOfMonth+1, 0, 0, 0));
                  stringObjectHashMap.put("addtime", 1);
                  zoneForT3= this.selectMode.selectModeForZoneServer(selectMode, stringObjectHashMap);
                  stringObjectHashMap.put("eid",27);
                  zonesForT4 = this.selectMode.selectModeForZoneServer(selectMode, stringObjectHashMap);
              }
              else
              {
                  stringObjectHashMap.put("starttime", LocalDateTime.of(year, monthValue,1, 0, 0, 0));
                  stringObjectHashMap.put("eid", 26);
                  stringObjectHashMap.put("endTime", LocalDateTime.of(year, monthValue+1,1, 0, 0, 0));
                  stringObjectHashMap.put("addtime", 1);
                  zoneForT3= this.selectMode.selectModeForZoneServer(selectMode, stringObjectHashMap);
                  stringObjectHashMap.put("eid",27);
                  zonesForT4 = this.selectMode.selectModeForZoneServer(selectMode, stringObjectHashMap);
              }

               if (zoneForT3.size()!=zonesForT4.size())
               {
                   throw new RuntimeException("T3和T4集合大小不一");
               }
               for (int i=0;i<zoneForT3.size();i++)
               {
                   Zone zoneT3 = zoneForT3.get(i);
                   Zone zoneT4= zonesForT4.get(i);
                   totalZones.add(new Zone(zoneT3.getEid(),zoneT3.gettValue()+zoneT4.gettValue(),zoneT3.gettTime()));
               }
              stringObjectHashMap.put("node",5);
              if ("hours".equals(selectMode))
              {
                  proAmounts= proAmountServer.getProAmountList(stringObjectHashMap);
              }
              else
              {
                  proAmounts = proAmountServer.getProAmountListForDay(stringObjectHashMap);
              }

              outputData.put("T3",zoneForT3);
              outputData.put("T4",zonesForT4);
              outputData.put("total",totalZones);
              outputData.put("amount",proAmounts);

          }
          else
          {
              if ("hours".equals(selectMode))
              {
                  stringObjectHashMap.put("starttime", LocalDateTime.of(year, monthValue,dayOfMonth, 0, 0, 0));
                  stringObjectHashMap.put("eid", eid);
                  stringObjectHashMap.put("endTime", LocalDateTime.of(year, monthValue,dayOfMonth+1, 0, 0, 0));
                  stringObjectHashMap.put("addtime", 1);
                  selectSingleZones = this.selectMode.selectModeForZoneServer(selectMode, stringObjectHashMap);
              }
              else
              {
                  stringObjectHashMap.put("starttime", LocalDateTime.of(year, monthValue,1, 0, 0, 0));
                  stringObjectHashMap.put("eid", eid);
                  stringObjectHashMap.put("endTime", LocalDateTime.of(year, monthValue+1,1 , 0, 0));
                  stringObjectHashMap.put("addtime", 1);
                  selectSingleZones = this.selectMode.selectModeForZoneServer(selectMode, stringObjectHashMap);
              }

              outputData.put("single",selectSingleZones);
              if (isSelectAmount)
              {
                  stringObjectHashMap.put("node",node);
                  if ("hours".equals(selectMode))
                  {
                      proAmounts= proAmountServer.getProAmountList(stringObjectHashMap);
                  }
                  else
                  {
                      proAmounts = proAmountServer.getProAmountListForDay(stringObjectHashMap);
                  }
                  outputData.put("singleAmount",proAmounts);
              }

          }

          return outputData;
      }


}
