package com.EnergyProject.server;

import com.EnergyProject.dao.ZoneDAO;
import com.EnergyProject.dao.ZoneDAOForEhcache;
import com.EnergyProject.dao.ZoneDAOForNoCallable;
import com.EnergyProject.pojo.Zone;

import com.EnergyProject.utils.SelectMode;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.ParseException;
import java.time.*;
import java.util.*;

@Service
public class ZoneServer {
    @Autowired
    private SqlSessionFactory sqlSessionFactory;
    @Autowired
    private ZoneDAO zoneDAO;
    @Autowired
    private ZoneDAOForNoCallable zoneDAOForNoCallable;
    @Autowired
    private ZoneDAOForEhcache zoneDAOForEhcache;

    public List<Zone> getZoneList(Map<String,Object> intoParament,Integer outloop,Integer interloop) throws ParseException
    {
        //SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
       // ZoneDAO mapper = sqlSession.getMapper(ZoneDAO.class);
        ArrayList<Zone> retrunzone = new ArrayList<>();
        for (int i = 0; i < outloop; i++)
        {
            for (int j = 0; j < interloop; j++) {
                List<List<Zone>> getzonelist = zoneDAO.getzonelist(intoParament);
                Zone zone=null;
                if (getzonelist.size()==1)
                {
                    zone =(Zone) getzonelist.get(0);

                    retrunzone.add(zone);
                }
                else if (getzonelist.size()!=0 ){
                    List<Zone> objects = getzonelist.get(getzonelist.size() - 1);
                    zone = objects.get(0);
                    getzonelist.forEach(value->{
                        retrunzone.addAll(value);
                    });
                }

                if (getzonelist!=null&&getzonelist.size()>1 )
                {
                    LocalDateTime localDateTime = zone.gettTime();
                    LocalDateTime newlocalDateTime = localDateTime.plusHours(1);
                    intoParament.put("starttime", newlocalDateTime);


                }
                else
                {

                    Date nulltime =(Date) intoParament.get("nulltime");
                    Instant instant = nulltime.toInstant();
                    LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
                    LocalDateTime newlocalDateTime = localDateTime.plusHours(1);
                    intoParament.put("starttime", newlocalDateTime);
                }


            }


        }
       // sqlSession.close();
        return retrunzone;
    }

    public List<Zone> getZoneListForDay(Map<String,Object> intoParament,Integer outloop,Integer interloop)
    {
        //SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
        //ZoneDAO mapper = sqlSession.getMapper(ZoneDAO.class);
        ArrayList<Zone> retrunzone = new ArrayList<>();
        for (int i = 0; i < outloop; i++)
        {
            for (int j = 0; j < interloop; j++) {
                List<List<Zone>> getzonelist = zoneDAO.getzonelistForDay(intoParament);
                Zone zone=null;
                if (getzonelist.size()==1)
                {
                    zone =(Zone) getzonelist.get(0);

                    retrunzone.add(zone);
                }
                else if (getzonelist.size()!=0){
                    List<Zone> objects = getzonelist.get(getzonelist.size() - 1);
                    zone = objects.get(0);
                    getzonelist.forEach(value->{
                        retrunzone.addAll(value);
                    });
                }

                if (getzonelist!=null&&getzonelist.size()>1)
                {
                    LocalDateTime localDateTime = zone.gettTime();
                    LocalDateTime newlocalDateTime = localDateTime.plusDays(1);
                    intoParament.put("starttime", newlocalDateTime);


                }
                else
                {

                    Date nulltime =(Date) intoParament.get("nulltime");
                    Instant instant = nulltime.toInstant();
                    LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
                    LocalDateTime newlocalDateTime = localDateTime.plusDays(1);
                    intoParament.put("starttime", newlocalDateTime);
                }


            }


        }
         //sqlSession.close();
        return retrunzone;
    }

    /*public List<Zone> getZoneListForMinute(Map<String,Object> intoParament,Integer outloop,Integer interloop) throws ParseException
    {
        //SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
        //ZoneDAO mapper = sqlSession.getMapper(ZoneDAO.class);
        ArrayList<Zone> retrunzone = new ArrayList<>();
        for (int i = 0; i < outloop; i++)
        {
            for (int j = 0; j < interloop; j++) {
                List<List<Zone>> getzonelist = zoneDAO.getzonelistForMinute(intoParament);
                Zone zone=null;
                if (getzonelist.size()==1)
                {
                    zone =(Zone) getzonelist.get(0);

                    retrunzone.add(zone);
                }
                else if (getzonelist.size()!=0){
                    List<Zone> objects = getzonelist.get(getzonelist.size() - 1);
                    zone = objects.get(0);
                    getzonelist.forEach(value->{
                        retrunzone.addAll(value);
                    });
                }

                if (getzonelist!=null&&getzonelist.size()>1)
                {
                    LocalDateTime localDateTime = zone.gettTime();
                    LocalDateTime newlocalDateTime = localDateTime.plusMinutes(5);
                    intoParament.put("starttime", newlocalDateTime);


                }
                else
                {

                    Date nulltime =(Date) intoParament.get("nulltime");
                    Instant instant = nulltime.toInstant();
                    LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
                    LocalDateTime newlocalDateTime = localDateTime.plusMinutes(5);
                    intoParament.put("starttime", newlocalDateTime);
                }


            }


        }
        //sqlSession.close();
        return retrunzone;
    }*/

    public List<Zone> getZoneTotal(Map<String,Object> intoParament)
    {

        ArrayList<Zone> retrunzone = new ArrayList<>();

            for (int j = 0; j < 2; j++) {
                List<List<Zone>> getzonelist = zoneDAO.getzonelist(intoParament);
                Zone zone=null;
                if (getzonelist.size()==1)
                {
                    zone =(Zone) getzonelist.get(0);

                    retrunzone.add(zone);
                }
                else if (getzonelist.size()!=0 && getzonelist.get(0).size()!=0){
                    List<Zone> objects = getzonelist.get(getzonelist.size() - 1);
                    zone = objects.get(0);
                    getzonelist.forEach(value->{
                        retrunzone.addAll(value);
                    });
                }

                if (getzonelist!=null && getzonelist.size()>1 && getzonelist.get(0).size()!=0 )
                {
                    LocalDateTime localDateTime = zone.gettTime();
                    LocalDateTime newlocalDateTime = localDateTime.plusHours(1);
                    intoParament.put("starttime", newlocalDateTime);


                }
                else
                {
                    if (j<1){
                        Date nulltime =(Date) intoParament.get("nulltime");
                        Instant instant = nulltime.toInstant();
                        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
                        LocalDateTime newlocalDateTime = localDateTime.plusHours(1);
                        intoParament.put("starttime", newlocalDateTime);
                    }

                }


            }




        return retrunzone;
    }

    @Transactional(readOnly = true )
    public List<Zone> getsqlserverSelectAllLastZone(){
        return zoneDAOForNoCallable.sqlserverSelectAllLastZone();
    }

    public Zone getselectTotalZoneForDay(Integer day,Integer month,Integer year,Integer eid){
        return zoneDAOForEhcache.selectTotalZoneForDay(day, month, year,eid);

    }

    public List<Integer> getZoneAllNode(){
       return zoneDAOForEhcache.getZoneAllNode();
    }

    public List<Zone> getNodeZoneTotalData(Map<String,Object> intoParament,String selectMode){
            ArrayList<Zone> retrunzone = new ArrayList<>();
            for (int i=0;i<24;i++)
            {

                List<List<Zone>> getzonelist = SelectMode.selectModeForZoneServer(selectMode, zoneDAO, intoParament);
                Zone zone = null;
                if (getzonelist.size() == 1) {
                    zone = (Zone) getzonelist.get(0);

                    retrunzone.add(zone);
                } else if (getzonelist.size() > 1) {
                    List<Zone> objects = getzonelist.get(getzonelist.size() - 1);
                    zone = objects.get(0);
                    getzonelist.forEach(value -> {
                        retrunzone.addAll(value);
                    });
                }
                if (getzonelist != null && getzonelist.size() > 1) {
                    LocalDateTime localDateTime = zone.gettTime();
                    LocalDateTime newlocalDateTime=null;
                    switch (selectMode)
                    {
                        case "hours":
                             newlocalDateTime = localDateTime.plusHours(1);
                            break;
                        case "days":
                           newlocalDateTime = localDateTime.plusDays(1);
                            break;
                        default:
                             newlocalDateTime = localDateTime.plusSeconds(299);
                            break;
                    }
                    intoParament.put("starttime", newlocalDateTime);


                } else {
                    Date nulltime = (Date) intoParament.get("nulltime");
                    if (nulltime!=null) {
                        Instant instant = nulltime.toInstant();
                        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
                        LocalDateTime newlocalDateTime=null;
                        switch (selectMode)
                        {
                            case "hours":
                                newlocalDateTime = localDateTime.plusHours(1);
                                break;
                            case "days":
                                newlocalDateTime = localDateTime.plusDays(1);
                                break;
                            default:
                                newlocalDateTime = localDateTime.plusSeconds(299);
                                break;
                        }
                        intoParament.put("starttime", newlocalDateTime);
                    }
                }
                LocalDateTime starttime = (LocalDateTime) intoParament.get("starttime");
                LocalDateTime endTime1 = (LocalDateTime) intoParament.get("endTime");

                if (starttime.isAfter(endTime1))
                {
                    break;
                }
            }
        return retrunzone;
    }

}
