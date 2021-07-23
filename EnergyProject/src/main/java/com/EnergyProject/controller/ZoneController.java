package com.EnergyProject.controller;

import com.EnergyProject.dao.ZoneDAO;
import com.EnergyProject.dao.ZoneDAOForNoCallable;
import com.EnergyProject.pojo.ProAmount;
import com.EnergyProject.pojo.Zone;
import com.EnergyProject.server.ProAmountServer;
import com.EnergyProject.server.ZoneServer;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@RestController
@Slf4j
public class ZoneController {
    @Autowired
    private ZoneServer zoneServer;

    @Autowired
    private ZoneDAO zoneDAO;
    @Autowired
    private ProAmountServer proAmountServer;
    @Autowired
    private ZoneDAOForNoCallable zoneDAOForNoCallable;
    private Map<String,Object> frontEndData=new HashMap<>();
    private Map<String,Object> yesterdayFrontEndData=new HashMap<>();

    private AtomicInteger atomicInteger=new AtomicInteger();
    /*@GetMapping("/getYesterdayData")
    public Map<String, Object> getYesterdayData(@RequestParam(value = "isAmount",defaultValue ="false") Boolean isAmount) throws ParseException {
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
            frontEndData.remove("init");
        LocalDateTime localDateTime = LocalDateTime.now().plusDays(-1);
        int year = localDateTime.getYear();
        int monthValue = localDateTime.getMonthValue();
        int dayOfMonth = localDateTime.getDayOfMonth();
        stringObjectHashMap.put("starttime", LocalDateTime.of(year,6,27,0,0,0));
        stringObjectHashMap.put("eid", 26);
        stringObjectHashMap.put("endTime", LocalDateTime.of(year,6,27,23,30,0));
        stringObjectHashMap.put("addtime", 1);
        List<Zone> zoneList = zoneServer.getzonelistForCUROS(stringObjectHashMap);
        frontEndData.put("main",zoneList);

            stringObjectHashMap.put("starttime", LocalDateTime.of(year,6, 28,0,0,0));
            stringObjectHashMap.put("endTime", LocalDateTime.of(year,6,28,0,30,0));
            List<Zone> zone = zoneServer.getzonelistForCUROS(stringObjectHashMap);
            frontEndData.put("init",zone);
            if (isAmount)
            {
               zoneServer.hoursAmount(frontEndData,year,monthValue,dayOfMonth,true,5);
            }

        return frontEndData;
    }*/

    @GetMapping("/getYesterdayData")
    public Map<String, Object> getYesterdayData(@RequestParam(value = "isAmount",defaultValue ="true") Boolean isAmount,@RequestParam(value = "isHours",defaultValue = "hours") String Hours) throws ParseException {
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        LocalDateTime localDateTime = LocalDateTime.now().plusDays(-1);
        int year = localDateTime.getYear();
        int monthValue = localDateTime.getMonthValue();
        int dayOfMonth = localDateTime.getDayOfMonth();
        zoneServer.handlerDateOfAmount(frontEndData,year,monthValue,dayOfMonth,true,Hours,null);
        if (isAmount)
        {
            zoneServer.hoursAmount(frontEndData,year,monthValue,dayOfMonth,Hours,true,5);
        }

        return frontEndData;
    }

    @GetMapping("/getNowDayData")
    public Map<String,Object> getNowDayData(@RequestParam(value = "isAmount",defaultValue ="true") Boolean isAmount,@RequestParam(value = "isTotal",defaultValue ="true") Boolean isTotal,@RequestParam(value = "selectMode",defaultValue = "hours") String selectMode)
    {
        LocalDateTime localDateTime = LocalDateTime.now();
        int year = localDateTime.getYear();
        int monthValue = localDateTime.getMonthValue();
        int dayOfMonth = localDateTime.getDayOfMonth();
        zoneServer.handlerDateOfAmount(frontEndData,year,6,27,isTotal,selectMode,null);
        if (isAmount)
        {
            zoneServer.hoursAmount(frontEndData,year,6,27,selectMode,isTotal,5);
        }

        return frontEndData;
    }

    @PostMapping("/getTodayTotalAllData")
    public List<Zone> getTodayTotalAllData()
    {
        List<Zone> zones = zoneServer.selectTotalZone();
        IntSummaryStatistics collect = zones.stream().collect(Collectors.summarizingInt(Zone::gettValue));
        List<Zone> collectFiler = zones.stream().filter(value -> value.getEid() != 27).collect(Collectors.toList());
        collectFiler.add(new Zone(null,(int) collect.getSum(),collectFiler.get(0).gettTime()));

        return collectFiler;
    }
    @PostMapping("/getYesterdayAllData")
    public Map<String, Object> getYesterdayAllData()
    {
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        LocalDateTime localDateTime = LocalDateTime.now().plusDays(-1);
        List<Zone> zoneYesterday = zoneServer.getselectTotalZoneForYesterday(localDateTime.getDayOfMonth(), localDateTime.getMonthValue(), localDateTime.getYear());
        List<Zone> zoneBeforeDay = zoneServer.getselectTotalZoneForYesterday(localDateTime.getDayOfMonth()-1, localDateTime.getMonthValue(), localDateTime.getYear());
        List<Zone> zoneLastMonth = zoneServer.getselectTotalZoneForYesterday(localDateTime.getDayOfMonth(), localDateTime.getMonthValue()-1, localDateTime.getYear());
        if (zoneYesterday.size()!=0&&zoneBeforeDay.size()!=0&&zoneLastMonth.size()!=0)
        {

        }
        IntSummaryStatistics collectYesterday = zoneYesterday.stream().collect(Collectors.summarizingInt(Zone::gettValue));
        IntSummaryStatistics collectBeforeDay = zoneBeforeDay.stream().collect(Collectors.summarizingInt(Zone::gettValue));
        IntSummaryStatistics collectLastMonth = zoneLastMonth.stream().collect(Collectors.summarizingInt(Zone::gettValue));
        List<Zone> collectFiler = new ArrayList<>();
        collectFiler.add(new Zone(null,(int) collectYesterday.getSum(),zoneYesterday.size()!=0?zoneYesterday.get(0).gettTime():null));
        Double beforeDayPercentage=null;
        Double lastMonthPercentage=null;
        if (collectYesterday.getSum()!=0 && collectBeforeDay.getSum()!=0)
        {
             beforeDayPercentage=(collectYesterday.getSum()-collectBeforeDay.getSum())/(collectBeforeDay.getSum()*1.0)*100;
        }
        if (collectYesterday.getSum()!=0 && collectLastMonth.getSum()!=0)
        {
            lastMonthPercentage=(collectYesterday.getSum()-collectLastMonth.getSum())/(collectLastMonth.getSum()*1.0)*100;
        }

        stringObjectHashMap.put("before",beforeDayPercentage);
        stringObjectHashMap.put("lastMonth",lastMonthPercentage);
        stringObjectHashMap.put("yesterdayData",collectFiler);
        System.out.println(stringObjectHashMap);
        return stringObjectHashMap;

    }


    @GetMapping("/getTesterdayDataForMonth")

    public Map<String, Object> getTesterdayDataForMonth() throws ParseException {
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        LocalDateTime localDateTime = LocalDateTime.now().plusMonths(-1);
        int year = localDateTime.getYear();
        int monthValue = localDateTime.getMonthValue();
        Month month = localDateTime.getMonth();
        int maxDay = month.maxLength();
        int dayOfMonth = localDateTime.getDayOfMonth();
        stringObjectHashMap.put("starttime", LocalDateTime.of(year,monthValue,dayOfMonth,0,0,0));
        stringObjectHashMap.put("eid", 26);
        stringObjectHashMap.put("endTime", LocalDateTime.of(year,monthValue,maxDay,0,30,0));
        stringObjectHashMap.put("addtime", 1);
        List<Zone> zoneList = zoneServer.getzonelistForCUROSForDay(stringObjectHashMap);
        yesterdayFrontEndData.put("main",zoneList);
        stringObjectHashMap.put("starttime", LocalDateTime.of(year,monthValue+1,1,0,0,0));
        stringObjectHashMap.put("endTime", LocalDateTime.of(year,monthValue+1,1,23,0,0));
            List<Zone> zone = zoneServer.getzonelistForCUROSForDay(stringObjectHashMap);
            yesterdayFrontEndData.put("init",zone);


        return yesterdayFrontEndData;
    }

    @GetMapping("/getZoneAllNode")
    public List<Integer> getZoneAllNode()
    {
       return zoneServer.getZoneAllNode();
    }

    @GetMapping("/getEastEnergyConsumptionDistributed")
    public List<Zone> getEastEnergyConsumptionDistributed()
    {
        LocalDateTime now = LocalDateTime.now();
       return zoneServer.getselectTotalZoneForYesterday(27,6,2021);
    }
    @GetMapping("/getInPutSingleAreaEnergyData")
    public Map<String,Object> getInPutSingleAreaEnergyData(String areaType)
    {
        LocalDateTime now = LocalDateTime.now();
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("starttime",LocalDateTime.of(2021,6,27,0,0,0));
        stringObjectHashMap.put("addtime",1);
        stringObjectHashMap.put("endTime",LocalDateTime.of(2021,6,28,0,0,0));
        if ("T3".equals(areaType))
        {
            stringObjectHashMap.put("eid",26);
        }
        else {
            stringObjectHashMap.put("eid",27);
        }
        return null;
    }

    @GetMapping("/getNodeZoneData")
    public Map<String,Object> getNodeZoneData(Integer node, @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startTime, @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endTime, String selectMode, String selectType,@RequestParam(value = "isAmount",defaultValue ="false") Boolean isAmounr,@RequestParam(value = "isNoProductionSelect",defaultValue = "false") Boolean isNoProductionSelect,@RequestParam(value = "nonProductionValueLimit",defaultValue = "150") Integer nonProductionValueLimit )
    {
        List<Zone> nodeZoneTotalData=new ArrayList<>();
        List<ProAmount> proAmountList=null;
        HashMap<String, Object> outPutHashmap = new HashMap<String, Object>();
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
            stringObjectHashMap.put("starttime", startTime);
            stringObjectHashMap.put("eid", node);
            stringObjectHashMap.put("node",5);
            stringObjectHashMap.put("addtime", 1);
            stringObjectHashMap.put("endTime",endTime);
        if (!"minutes".equals(selectMode))
        {
            if (isAmounr)
            {
                stringObjectHashMap.put("eid",26);
                List<Zone> nodeZoneTotalDataForT3 = zoneServer.getNodeZoneTotalData(stringObjectHashMap, selectMode);
                stringObjectHashMap.put("eid",27);
                List<Zone> nodeZoneTotalDataForT4 = zoneServer.getNodeZoneTotalData(stringObjectHashMap, selectMode);
                if (nodeZoneTotalDataForT3.size()!=nodeZoneTotalDataForT4.size())
                {
                    log.error("两个集合大小不相等");
                    throw new RuntimeException("两个集合大小不相等");
                }
                if (nodeZoneTotalDataForT3.size()==0 || nodeZoneTotalDataForT4.size()==0)
                {
                     outPutHashmap.put("message","数据库无值");
                     return outPutHashmap;
                }
                for (int i=0;i<nodeZoneTotalDataForT3.size();i++)
                {
                    Zone zone = nodeZoneTotalDataForT3.get(i);
                    Zone zoneNext = nodeZoneTotalDataForT4.get(i);
                    nodeZoneTotalData.add(new Zone(null,zone.gettValue()+zoneNext.gettValue(),zone.gettTime()));
                }
                switch (selectMode)
                {
                    case "hours" ->{
                        proAmountList = proAmountServer.getProAmountList(stringObjectHashMap);

                    }
                    case "days" ->{
                        proAmountList=proAmountServer.getProAmountListForDay(stringObjectHashMap);
                    }
                }

            }
            else
            {
                nodeZoneTotalData= zoneServer.getNodeZoneTotalData(stringObjectHashMap, selectMode);
            }
        }
        else
        {
           nodeZoneTotalData= zoneServer.selectDafultForMininunt(stringObjectHashMap);
        }

        if ("total".equals(selectType))
        {
            outPutHashmap.put("main",nodeZoneTotalData);
            return outPutHashmap;
        }
        else
        {

            ArrayList<Zone> zones = new ArrayList<>();
            nodeZoneTotalData.forEach(iteam->{
                zones.add(new Zone(iteam.getEid(),iteam.gettValue(),iteam.gettTime()));
            });
            System.out.println(zones);

            for (int i=0;i<nodeZoneTotalData.size()-1;i++)
            {
                zones.get(i).settValue(nodeZoneTotalData.get(i+1).gettValue()-nodeZoneTotalData.get(i).gettValue());
            }
            zones.remove(zones.size()-1);
            if (isAmounr)
            {
                ArrayList<ProAmount> proAmounts = new ArrayList<>();
                proAmountList.forEach(iteam->{
                    proAmounts.add(new ProAmount(iteam.getNode(),iteam.gettValue(),iteam.gettTime()));
                });
                for (int i=0;i<proAmountList.size()-1;i++)
                {
                    proAmounts.get(i).settValue(proAmountList.get(i+1).gettValue()-proAmountList.get(i).gettValue());
                }
                proAmounts.remove(proAmounts.size()-1);
                if (isNoProductionSelect)
                {
                    atomicInteger.set(0);
                    ArrayList<ProAmount> filterProAmountList=new ArrayList<>();
                    ArrayList<ProAmount> finallFilterProAmountList=new ArrayList<>();
                    if (zones.size()!=proAmounts.size()&&zones.size()!=0&&proAmounts.size()!=0)
                    {
                        switch (selectMode)
                        {
                            case "hours"->{
                                zones.forEach(item->{
                                    int dayOfMonth = item.gettTime().getDayOfMonth();
                                    int hour = item.gettTime().getHour();
                                    filterProAmountList.addAll(proAmounts.stream().filter(itemAmounts->{
                                        int dayOfMonthForAmount = itemAmounts.gettTime().getDayOfMonth();
                                        int dayOfMonthForAmountHour = itemAmounts.gettTime().getHour();
                                        return dayOfMonth==dayOfMonthForAmount && hour==dayOfMonthForAmountHour;
                                    }).collect(Collectors.toList()));
                                });
                            }
                            case "days"->{
                                zones.forEach(item->{
                                    int monthValue = item.gettTime().getMonthValue();
                                    int dayOfMonth = item.gettTime().getDayOfMonth();
                                    filterProAmountList.addAll(proAmounts.stream().filter(itemAmounts->{
                                        int monthValueForAmount = itemAmounts.gettTime().getMonthValue();
                                        int dayOfMonthForAmountHour = itemAmounts.gettTime().getDayOfMonth();
                                        return dayOfMonth==dayOfMonthForAmountHour && monthValue==monthValueForAmount;
                                    }).collect(Collectors.toList()));
                                });
                            }
                        }

                    }
                    if (filterProAmountList.size()==0)
                    {
                        finallFilterProAmountList.addAll(proAmounts);
                    }
                    else
                    {
                        finallFilterProAmountList.addAll(filterProAmountList);
                    }
                    List<Zone> collect = zones.stream().filter(zoneItem -> {
                        int i = atomicInteger.get();
                        atomicInteger.getAndIncrement();
                        return finallFilterProAmountList.get(i).gettValue() == 0 && zoneItem.gettValue() <= nonProductionValueLimit;
                    }).collect(Collectors.toList());
                    IntSummaryStatistics collectOfTotal = zones.stream().collect(Collectors.summarizingInt(Zone::gettValue));
                    outPutHashmap.put("main",collect);
                    outPutHashmap.put("totalSum",collectOfTotal.getSum());
                    return outPutHashmap;

                }
                outPutHashmap.put("ha",proAmounts);
            }
            outPutHashmap.put("main",zones);
            return outPutHashmap;
        }


    }
}
