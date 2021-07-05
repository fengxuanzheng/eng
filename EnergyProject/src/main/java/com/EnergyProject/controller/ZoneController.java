package com.EnergyProject.controller;

import com.EnergyProject.dao.ZoneDAO;
import com.EnergyProject.dao.ZoneDAOForNoCallable;
import com.EnergyProject.pojo.Zone;
import com.EnergyProject.server.ZoneServer;
import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.runtime.directive.MacroParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.ParameterizedType;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;

@RestController
@Slf4j
public class ZoneController {
    @Autowired
    private ZoneServer zoneServer;

    @Autowired
    private ZoneDAO zoneDAO;
    @Autowired
    private ZoneDAOForNoCallable zoneDAOForNoCallable;
    private Map<String,Object> frontEndData=new HashMap<>();
    private Map<String,Object> yesterdayFrontEndData=new HashMap<>();
    @GetMapping("/getYesterdayData")
    public Map<String, Object> getYesterdayData(Boolean initialization) throws ParseException {
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
            frontEndData.remove("init");
        LocalDateTime localDateTime = LocalDateTime.now().plusDays(-1);
        int year = localDateTime.getYear();
        int monthValue = localDateTime.getMonthValue();
        int dayOfMonth = localDateTime.getDayOfMonth();
        stringObjectHashMap.put("starttime", LocalDateTime.of(2021,5,24,0,0,0));
        stringObjectHashMap.put("eid", 26);
        stringObjectHashMap.put("sizes", 1);
        stringObjectHashMap.put("addtime", 1);
        List<Zone> zoneList = zoneServer.getZoneList(stringObjectHashMap, 1, 2);
        frontEndData.put("main",zoneList);
        if (initialization)
        {
            Zone zone = zoneServer.getselectTotalZoneForDay(23, 5, 2021,26);
            frontEndData.put("init",zone);

        }
        return frontEndData;
    }

    @GetMapping("/getTesterdayDataForMonth")
    public Map<String, Object> getTesterdayDataForMonth(Boolean initialization) throws ParseException {
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        yesterdayFrontEndData.remove("init");
        LocalDateTime localDateTime = LocalDateTime.now().plusMonths(-1);
        int year = localDateTime.getYear();
        int monthValue = localDateTime.getMonthValue();
        int dayOfMonth = localDateTime.getDayOfMonth();
        stringObjectHashMap.put("starttime", LocalDateTime.of(2021,4,1,0,0,0));
        stringObjectHashMap.put("eid", 26);
        stringObjectHashMap.put("sizes", 2);
        stringObjectHashMap.put("addtime", 1);
        List<Zone> zoneList = zoneServer.getZoneListForDay(stringObjectHashMap, 3, 1);
        yesterdayFrontEndData.put("main",zoneList);
        if (initialization)
        {
            LocalDateTime localDateTime1 = localDateTime.plusMonths(-1);
            Month month = localDateTime1.getMonth();
            int maxDay = month.maxLength();
            int year1 = localDateTime1.getYear();
            int monthValue1 = localDateTime1.getMonthValue();
            Zone zone = zoneServer.getselectTotalZoneForDay(31, 3, 2021,26);
            yesterdayFrontEndData.put("init",zone);

        }
        return yesterdayFrontEndData;
    }

    @GetMapping("/getZoneAllNode")
    public List<Integer> getZoneAllNode()
    {
       return zoneServer.getZoneAllNode();
    }

    @GetMapping("/getNodeZoneData")
    public List<Zone> getNodeZoneData(Integer node, @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startTime,@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endTime, String selectMode,String selectType)
    {
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("starttime", startTime);
        stringObjectHashMap.put("eid", node);
        stringObjectHashMap.put("endTime",endTime);
        if ("minutes".equals(selectMode)){
            stringObjectHashMap.put("addtime", 302);
        }
        else
        {
            stringObjectHashMap.put("addtime", 1);
        }

        List<Zone> nodeZoneTotalData = zoneServer.getNodeZoneTotalData(stringObjectHashMap, selectMode);
        if ("total".equals(selectType))
        {
            return nodeZoneTotalData;
        }
        else
        {
            LocalDateTime lastDataTime=null;
            ArrayList<Zone> zones = new ArrayList<>();
            nodeZoneTotalData.forEach(iteam->{
                zones.add(new Zone(iteam.getEid(),iteam.gettValue(),iteam.gettTime()));
            });
            System.out.println(zones);
            Zone zone=null;
            if ("hours".equals(selectMode))
            {
               lastDataTime= startTime.plusHours(-1);
                 zone= zoneServer.getselectTotalZoneForDay(lastDataTime.getDayOfMonth(), lastDataTime.getMonthValue(), lastDataTime.getYear(), node);

            }
            else if ("days".equals(selectMode))
            {
                lastDataTime=startTime.plusDays(-1);
                zone= zoneServer.getselectTotalZoneForDay(lastDataTime.getDayOfMonth(), lastDataTime.getMonthValue(), lastDataTime.getYear(), node);
            }
            else
            {
                lastDataTime=startTime.plusSeconds(-302);
                zone= zoneServer.getselectTotalZoneForDay(lastDataTime.getDayOfMonth(), lastDataTime.getMonthValue(), lastDataTime.getYear(), node);
            }
            for (int i=0;i<nodeZoneTotalData.size();i++)
            {
                if (i==0)
                {
                    zones.get(i).settValue(nodeZoneTotalData.get(i).gettValue()-zone.gettValue());
                }
                else
                {
                    zones.get(i).settValue(nodeZoneTotalData.get(i).gettValue()-nodeZoneTotalData.get(i-1).gettValue());
                }
            }
            return zones;
        }


    }
}
