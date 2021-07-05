package com.EnergyProject.utils;

import com.EnergyProject.dao.ZoneDAO;
import com.EnergyProject.pojo.Zone;

import java.util.List;
import java.util.Map;

public class SelectMode {

    public static List<List<Zone>> selectModeForZoneServer(String selectMode, ZoneDAO zoneDAO, Map<String,Object> intoParament){
        switch (selectMode)
        {
            case "hours":
               return zoneDAO.getzonelistForManger(intoParament);

            case "days":
                return zoneDAO.getzonelistForDayForManger(intoParament);
            default:
                return zoneDAO.getzonelistForMinuteForManger(intoParament);
        }
    }
}
