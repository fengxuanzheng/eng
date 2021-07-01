package com.EnergyProject.controller;

import com.EnergyProject.dao.ZoneDAO;
import com.EnergyProject.pojo.Zone;
import com.EnergyProject.server.ZoneServer;
import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.runtime.directive.MacroParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.ParameterizedType;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class ZoneController {
    @Autowired
    private ZoneServer zoneServer;

    @Autowired
    private ZoneDAO zoneDAO;
    private Map<String,Object> frontEndData=new HashMap<>();
    @GetMapping("/getYesterdayData")
    public Map<String, Object> getYesterdayData(Boolean initialization) throws ParseException {
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        LocalDateTime localDateTime = LocalDateTime.now().plusDays(-1);
        int year = localDateTime.getYear();
        int monthValue = localDateTime.getMonthValue();
        int dayOfMonth = localDateTime.getDayOfMonth();
        stringObjectHashMap.put("starttime", LocalDateTime.of(2021,6,27,0,0,0));
        stringObjectHashMap.put("eid", 26);
        stringObjectHashMap.put("sizes", 1);
        stringObjectHashMap.put("addtime", 1);
        List<Zone> zoneList = zoneServer.getZoneList(stringObjectHashMap, 1, 2);
        frontEndData.put("main",zoneList);
        if (initialization)
        {
            Zone zone = zoneDAO.selectTotalZoneForDay(26, 6, 2021);
            frontEndData.put("init",zone);

        }
        return frontEndData;
    }

    @GetMapping("/getTesterdayDataForMonth")
    public List<Zone> getTesterdayDataForMonth() throws ParseException {
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        LocalDateTime localDateTime = LocalDateTime.now().plusMonths(-1);
        int year = localDateTime.getYear();
        int monthValue = localDateTime.getMonthValue();
        int dayOfMonth = localDateTime.getDayOfMonth();
        stringObjectHashMap.put("starttime", LocalDateTime.of(2021,5,1,0,0,0));
        stringObjectHashMap.put("eid", 26);
        stringObjectHashMap.put("sizes", 2);
        stringObjectHashMap.put("addtime", 1);
        List<Zone> zoneList = zoneServer.getZoneListForDay(stringObjectHashMap, 3, 1);
        return zoneList;
    }
}
