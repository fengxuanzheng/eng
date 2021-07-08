package com.EnergyProject.server;

import com.EnergyProject.dao.ZoneDAO;
import com.EnergyProject.dao.ZoneDAOForNoCallable;
import com.EnergyProject.pojo.Zone;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

@Service
@Slf4j
public class SseEmitterService {
    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Autowired
    private ZoneDAO zoneDAO;
    @Autowired
    private ZoneServer zoneServer;

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
            sseEmitter.completeWithError(e);
        }
    }


  //@Async
  public void sqlservertransferDao(Map<String,SseEmitter> sseEmitterMap,Map<String,Integer> integerMap ,boolean global)  {

      if (sseEmitterMap.size() != 0 )
      {

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
              Set<Map.Entry<String, SseEmitter>> entries = sseEmitterMap.entrySet();
              entries.forEach(value->{
                  sseEmitterKeySet.add(value.getKey());
              });

              HashMap<String, Object> stringObjectHashMap = new HashMap<>();
              LocalDateTime now = LocalDateTime.now();
              int year = now.getYear();
              int monthValue = now.getMonthValue();
              int dayOfMonth = now.getDayOfMonth();
              LocalDateTime of = LocalDateTime.of(year, 6,28, 0, 0, 0);
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
              stringListHashMap.put("dd",searchZoneForDay);

              Iterator<String> iterator = ssesourceIdNumForZero.iterator();
              String next="";
              while (iterator.hasNext()){
                  try {
                       next = iterator.next();
                      sseEmitterMap.get(next).send(SseEmitter.event().id(Integer.toString(integerMap.get(next))).data(stringListHashMap));
                      integerMap.put(next,integerMap.get(next)+1);

                  }
                  catch (IOException e) {
                      e.printStackTrace();
                      sseEmitterMap.get(next).complete();
                      integerMap.remove(next);
                      sseEmitterMap.remove(next);
                  }
                  finally {
                      sseEmitterKeySet.remove(next);
                      iterator.remove();
                  }

              }
              searchZone.clear();
              searchZoneForDay.clear();

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
              ArrayList<Zone> zones = new ArrayList<>();
              Zone zone = zoneDAO.selectTotalZone();
              zones.add(zone);

              stringListHashMap.put("hh",zones);
              stringListHashMap.remove("dd");
              if (!filterSseEmitter.isEmpty())
              {
                  sseEmitterMap.forEach((key, value) -> {
                      try {
                          if (!filterSseEmitter.contains(key))
                          {
                              value.send(SseEmitter.event().id(Integer.toString(integerMap.get(key))).data(stringListHashMap, MediaType.APPLICATION_JSON));
                              integerMap.put(key,integerMap.get(key)+1);
                          }


                      } catch (IOException e) {
                          e.printStackTrace();
                          log.info(e.getMessage());
                          value.complete();
                          sseEmitterMap.remove(key);
                      }
                  });
                  filterSseEmitter.clear();
              }
              else
              {
                  sseEmitterMap.forEach((key, value) -> {
                      try {
                          value.send(SseEmitter.event().id(Integer.toString(integerMap.get(key))).data(stringListHashMap, MediaType.APPLICATION_JSON));
                          integerMap.put(key,integerMap.get(key)+1);

                      } catch (IOException e) {
                          e.printStackTrace();
                          log.info(e.getMessage());
                          value.complete();
                          sseEmitterMap.remove(key);
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

}

/*
class OutFrontSseData{
    private Integer evalue;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",locale = "GTM+8")
    private LocalDateTime nowDateTime;

    public OutFrontSseData(Integer evalue, LocalDateTime nowDateTime) {
        this.evalue = evalue;
        this.nowDateTime = nowDateTime;
    }

    public OutFrontSseData() {
    }

    public Integer getEvalue() {
        return evalue;
    }

    public void setEvalue(Integer evalue) {
        this.evalue = evalue;
    }

    public LocalDateTime getNowDateTime() {
        return nowDateTime;
    }

    public void setNowDateTime(LocalDateTime nowDateTime) {
        this.nowDateTime = nowDateTime;
    }

    @Override
    public String toString() {
        return "OutFrontSseData{" +
                "evalue=" + evalue +
                ", nowDateTime=" + nowDateTime +
                '}';
    }
}
*/
