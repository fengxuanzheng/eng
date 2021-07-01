package com.EnergyProject.dao;

import com.EnergyProject.pojo.Zone;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ZoneDAO extends BaseMapper<Zone> {

    @SuppressWarnings("MybatisXMapperMethodInspection")
    public List<List<Zone>> getzonelist(Map<String,Object> intoZone);
    @SuppressWarnings("MybatisXMapperMethodInspection")
    public List<List<Zone>> getzonelistForDay(Map<String,Object> intoZone);
    @SuppressWarnings("MybatisXMapperMethodInspection")
    public List<List<Zone>> getzonelistForMinute(Map<String,Object> intoZone);
    public Zone sqlservertransfer(Integer eid);
    public List<Zone> sqlserverSelectAllLastZone();
    public Zone selectTotalZone();
    public Zone selectTotalZoneForDay(@Param("day") Integer day, @Param("month") Integer month, @Param("year") Integer year);


}
