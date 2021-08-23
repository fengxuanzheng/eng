package com.EnergyProject.dao;

import com.EnergyProject.pojo.Zone;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface ZoneDAOForEhcache {

    public Zone selectZoneForSpecificTime(@Param("day") Integer day, @Param("month") Integer month, @Param("year") Integer year,@Param("eid") Integer eid);
    public List<Integer> getZoneAllNode();
    public List<Zone> selectDafultForMininunt(Map<String,Object> inputParameter);
    public List<Zone> selectTotalZoneForCurrentTime(Map<String,Object> intoHashMap);
    public List<Zone> getselectTotalZoneForYesterday(@Param("day")Integer day, @Param("month")Integer month, @Param("year")Integer year);
    public List<Zone> getselectTotalZoneForYesterdayOfAsc(@Param("day")Integer day, @Param("month")Integer month, @Param("year")Integer year);
    public List<Zone> getCurrentMonthFirstDayForZoneData(@Param("month")Integer month, @Param("year")Integer year);
    public List<Zone> geteveryDayFirstTimeEnergy(@Param("startTime")LocalDateTime startTime,@Param("endTime") LocalDateTime endTime);
    public List<Zone> geteveryDayFirstTimeEnergyOfFinall(@Param("startTime")LocalDateTime startTime,@Param("endTime") LocalDateTime endTime);


}
