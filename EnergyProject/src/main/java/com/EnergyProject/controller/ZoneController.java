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

import javax.persistence.criteria.CriteriaBuilder;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Period;
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
    public List<Zone> getEastEnergyConsumptionDistributed(@RequestParam(value ="selectMode",defaultValue = "hours") String selectMode)
    {
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        LocalDateTime now = LocalDateTime.now();
        stringObjectHashMap.put("endDay",27);
        stringObjectHashMap.put("endMonth",6);
        stringObjectHashMap.put("endYear",now.getYear());
        ArrayList<Zone> ouPutZoneList = new ArrayList<>();
        List<Zone> zones=null;
        if ("hours".equals(selectMode))
        {
            stringObjectHashMap.put("startDay",27);
            stringObjectHashMap.put("startMonth",6);
            stringObjectHashMap.put("startYear",now.getYear());

             zones = zoneServer.getselectTotalZoneForCurrentTime(stringObjectHashMap);
        }
        else
        {
            List<Zone> currentMonthFirstDayForZoneData = zoneServer.getCurrentMonthFirstDayForZoneData(6, now.getYear());
            if (currentMonthFirstDayForZoneData.size()==2)
            {

                //stringObjectHashMap.put("startDay",currentMonthFirstDayForZoneData.get(0).gettTime().getDayOfMonth());
                stringObjectHashMap.put("startDay",26);
                stringObjectHashMap.put("startMonth",6);
                stringObjectHashMap.put("startYear",now.getYear());
                zones = zoneServer.getselectTotalZoneForCurrentTime(stringObjectHashMap);
            }
            else
            {
                return null;
            }

        }
        if (zones.size()==4)
        {
            List<Zone> collectForT3 = zones.stream().filter(item -> {
            return item.getEid() == 26;
        }).collect(Collectors.toList());
            List<Zone> collectForT4 = zones.stream().filter(item -> {
                return item.getEid() == 27;
            }).collect(Collectors.toList());
            Zone zoneT3 = new Zone(collectForT3.get(0).getEid(), collectForT3.get(1).gettValue() - collectForT3.get(0).gettValue(), collectForT3.get(0).gettTime());
            Zone zoneT4 = new Zone(collectForT4.get(0).getEid(), collectForT4.get(1).gettValue() - collectForT4.get(0).gettValue(), collectForT4.get(0).gettTime());
            ouPutZoneList.add(zoneT3);
            ouPutZoneList.add(zoneT4);
            return ouPutZoneList;
        }
       return null;
    }
    @GetMapping("/getInPutSingleAreaEnergyData")
    public Map<String,Object> getInPutSingleAreaEnergyData(String areaType,@RequestParam(value = "selectMode",defaultValue = "hours") String selectMode)
    {
        ArrayList<Zone> filterZones = new ArrayList<>();
        HashMap<String, Object> ouPutHashMap = new HashMap<>();
        LocalDateTime now = LocalDateTime.now();
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("addtime",1);
        if ("hours".equals(selectMode))
        {
            stringObjectHashMap.put("starttime",LocalDateTime.of(2021,6,27,0,0,0));
            stringObjectHashMap.put("endTime",LocalDateTime.of(2021,6,28,0,0,0));
        }
        else
        {
            stringObjectHashMap.put("starttime",LocalDateTime.of(2021,6,1,0,0,0));
            stringObjectHashMap.put("endTime",LocalDateTime.of(2021,7,1,0,0,0));
        }

        if ("T3".equals(areaType))
        {
            stringObjectHashMap.put("eid",26);
        }
        else {
            stringObjectHashMap.put("eid",27);
        }
        List<Zone> zones = zoneServer.getNodeZoneTotalData(stringObjectHashMap, selectMode);
        if (zones.size()!=0)
        {
            zones.forEach(item->{
                filterZones.add(new Zone(item.getEid(),item.gettValue(),item.gettTime()));
            });
            for (int i=0;i<zones.size()-1;i++)
            {
                filterZones.get(i).settValue(zones.get(i+1).gettValue()-zones.get(i).gettValue());
            }
            filterZones.remove(filterZones.size()-1);
            ouPutHashMap.put("main",filterZones);
            return ouPutHashMap;
        }
        ouPutHashMap.put("main",null);
        return ouPutHashMap;
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

    @GetMapping("/selectNoProductionOfMonth")
    public Map<String,Object> selectNoProductionOfMonth( @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startTime, @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endTime,Integer nonProductionValueLimit)
    {
        int diffDay = endTime.compareTo(startTime);
        HashMap<String, Object> localDateTimeObjectHashMap = new HashMap<>();
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        Long totalEnerngy=0L;
        Long totalNoProductionEnergy=0L;
        ArrayList<Zone> FinallOutPutZoneList = new ArrayList<>();
        for (int i=0;i<=diffDay;i++)
        {
            atomicInteger.set(0);
            ArrayList<Zone> totalZoneData = new ArrayList<>();
            ArrayList<Zone> finallTotalZoneData = new ArrayList<>();
            ArrayList<ProAmount> finallProAmounts = new ArrayList<>();
            stringObjectHashMap.put("starttime",startTime);
            stringObjectHashMap.put("addtime",1);
            stringObjectHashMap.put("eid",26);
            stringObjectHashMap.put("endTime",startTime.plusDays(1));
            List<Zone> zonesT3 = zoneServer.getzonelistForCUROS(stringObjectHashMap);
            stringObjectHashMap.put("eid",27);
            List<Zone> zonesT4 = zoneServer.getzonelistForCUROS(stringObjectHashMap);
            for (int j=0;j<zonesT3.size();j++)
            {
                totalZoneData.add(new Zone(null,zonesT3.get(j).gettValue()+zonesT4.get(j).gettValue(),zonesT3.get(j).gettTime()));
            }
            stringObjectHashMap.put("node",5);
            List<ProAmount> proAmountListForDay = proAmountServer.getProAmountList(stringObjectHashMap);
            if (proAmountListForDay.size()!=0)
            {
                proAmountListForDay.forEach(item->{
                    finallProAmounts.add(new ProAmount(null,item.gettValue(),item.gettTime()));
                });
                for (int g=0;g<proAmountListForDay.size()-1;g++)
                {
                    finallProAmounts.get(g).settValue(proAmountListForDay.get(g+1).gettValue()-proAmountListForDay.get(g).gettValue());
                }
                finallProAmounts.remove(finallProAmounts.size()-1);
            }
            else
            {
                break;
            }
            if (totalZoneData.size()!=0)
            {
                totalZoneData.forEach(item->{
                    finallTotalZoneData.add(new Zone(null,item.gettValue(),item.gettTime()));
                });
                for (int c=0;c<totalZoneData.size()-1;c++)
                {
                    finallTotalZoneData.get(0).settValue(totalZoneData.get(c+1).gettValue()-totalZoneData.get(c).gettValue());
                }
                finallTotalZoneData.remove(finallTotalZoneData.size()-1);
            }
            else
            {
                break;
            }

            ArrayList<ProAmount> filterProAmountList=new ArrayList<>();
            ArrayList<ProAmount> finallFilterProAmountList=new ArrayList<>();
            if (finallTotalZoneData.size()!=finallProAmounts.size()&&finallTotalZoneData.size()!=0&&finallProAmounts.size()!=0)
            {
                finallTotalZoneData.forEach(item->{
                            int monthValue = item.gettTime().getMonthValue();
                            int dayOfMonth = item.gettTime().getDayOfMonth();
                            filterProAmountList.addAll(finallProAmounts.stream().filter(itemAmounts->{
                                int monthValueForAmount = itemAmounts.gettTime().getMonthValue();
                                int dayOfMonthForAmountHour = itemAmounts.gettTime().getDayOfMonth();
                                return dayOfMonth==dayOfMonthForAmountHour && monthValue==monthValueForAmount;
                            }).collect(Collectors.toList()));
                        });



            }
            if (filterProAmountList.size()==0)
            {
                finallFilterProAmountList.addAll(finallProAmounts);
            }
            else
            {
                finallFilterProAmountList.addAll(filterProAmountList);
            }

            List<Zone> collect = finallTotalZoneData.stream().filter(zoneItem -> {
                int index = atomicInteger.get();
                atomicInteger.getAndIncrement();
                return finallFilterProAmountList.get(index).gettValue() == 0 && zoneItem.gettValue() <= nonProductionValueLimit;
            }).collect(Collectors.toList());
            IntSummaryStatistics collectOfTotal = finallTotalZoneData.stream().collect(Collectors.summarizingInt(Zone::gettValue));
            totalEnerngy+=collectOfTotal.getSum();
            IntSummaryStatistics collectForNoProduction = collect.stream().collect(Collectors.summarizingInt(Zone::gettValue));
            int sum = (int)collectForNoProduction.getSum();
            totalNoProductionEnergy+=sum;
            Zone Finallzone = new Zone(null, sum, startTime);
            FinallOutPutZoneList.add(Finallzone);
            startTime=startTime.plusDays(1);
        }
        localDateTimeObjectHashMap.put("main",FinallOutPutZoneList);
        localDateTimeObjectHashMap.put("totalEnergy",totalEnerngy);
        localDateTimeObjectHashMap.put("totalEnergyOfNoProduction",totalNoProductionEnergy);
        return localDateTimeObjectHashMap;
    }
}
