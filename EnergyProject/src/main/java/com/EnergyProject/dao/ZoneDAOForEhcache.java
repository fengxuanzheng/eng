package com.EnergyProject.dao;

import com.EnergyProject.pojo.Zone;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ZoneDAOForEhcache {

    public Zone selectTotalZoneForDay(@Param("day") Integer day, @Param("month") Integer month, @Param("year") Integer year,@Param("eid") Integer eid);
    public List<Integer> getZoneAllNode();
    public List<Zone> selectDafultForMininunt(Map<String,Object> inputParameter);

}
