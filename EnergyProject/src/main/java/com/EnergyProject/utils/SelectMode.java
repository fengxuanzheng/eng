package com.EnergyProject.utils;

import com.EnergyProject.dao.ZoneDAO;
import com.EnergyProject.dao.ZoneDAOForEhcache;
import com.EnergyProject.pojo.Zone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
@Component
public class SelectMode {
  @Autowired
  private  ZoneDAOForEhcache zoneDAOForEhcache;
  @Autowired
  private ZoneDAO zoneDAO;

    public  List<Zone> selectModeForZoneServer(String selectMode, Map<String,Object> intoParament){
        switch (selectMode)
        {
            case "hours":
               return zoneDAO.getzonelistForCUROS(intoParament);

            case "days":
                return zoneDAO.getzonelistForCUROSForDay(intoParament);
            default:
                return zoneDAOForEhcache.selectDafultForMininunt(intoParament);

        }
    }
}
