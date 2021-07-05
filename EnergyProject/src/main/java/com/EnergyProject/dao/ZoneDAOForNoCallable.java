package com.EnergyProject.dao;

import com.EnergyProject.pojo.Zone;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ZoneDAOForNoCallable {

    public List<Zone> sqlserverSelectAllLastZone();


}
