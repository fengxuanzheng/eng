package com.EnergyProject.dao;

import com.EnergyProject.pojo.Zone;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ZoneDAOForEhcache {

    public Zone selectTotalZoneForDay(@Param("day") Integer day, @Param("month") Integer month, @Param("year") Integer year,@Param("eid") Integer eid);
    public List<Integer> getZoneAllNode();

}
