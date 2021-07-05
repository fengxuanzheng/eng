package com.EnergyProject.dao;

import com.EnergyProject.pojo.Zone;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ZoneDAO  {

    @SuppressWarnings("MybatisXMapperMethodInspection")
    public List<List<Zone>> getzonelist(Map<String,Object> intoZone);
    @SuppressWarnings("MybatisXMapperMethodInspection")
    public List<List<Zone>> getzonelistForDay(Map<String,Object> intoZone);
    @SuppressWarnings("MybatisXMapperMethodInspection")
    public List<List<Zone>> getzonelistForMinuteForManger(Map<String,Object> intoZone);
    @SuppressWarnings("MybatisXMapperMethodInspection")
    public List<List<Zone>> getzonelistForManger(Map<String,Object> intoZone);
    @SuppressWarnings("MybatisXMapperMethodInspection")
    public List<List<Zone>> getzonelistForDayForManger(Map<String,Object> intoZone);
    public Zone sqlservertransfer(Integer eid);
    public List<Zone> sqlserverSelectAllLastZone();
    public Zone selectTotalZone();



}
