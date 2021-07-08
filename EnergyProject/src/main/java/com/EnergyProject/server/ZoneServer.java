package com.EnergyProject.server;

import com.EnergyProject.dao.ZoneDAO;
import com.EnergyProject.dao.ZoneDAOForEhcache;
import com.EnergyProject.dao.ZoneDAOForNoCallable;
import com.EnergyProject.pojo.ProAmount;
import com.EnergyProject.pojo.Zone;

import com.EnergyProject.utils.SelectMode;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
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
    @Autowired
    private ProAmountServer proAmountServer;
    @Autowired
    private SelectMode selectMode;

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
          /*  ArrayList<Zone> retrunzone = new ArrayList<>();
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
        return retrunzone;*/
        return null;
    }

    public List<Zone> getzonelistForCUROS(Map<String,Object> intoParament)
    {
                return zoneDAO.getzonelistForCUROS(intoParament);
    }
    public List<Zone> getzonelistForCUROSForDay(Map<String,Object> intoParament)
    {
        return zoneDAO.getzonelistForCUROSForDay(intoParament);
    }
    public void hoursAmount(Map<String,Object>sendData,Integer year,Integer monthValue,Integer dayOfMonth,Boolean isSelectOfHours, Integer node)
    {
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        ArrayList<ProAmount> proAmounts = new ArrayList<>();
        List<ProAmount> proAmountList=null;
        stringObjectHashMap.put("starttime", LocalDateTime.of(year,6,27,0,0,0));
        stringObjectHashMap.put("node", 5);
        stringObjectHashMap.put("endTime", LocalDateTime.of(year,6,28,0,30,0));
        stringObjectHashMap.put("addtime", 1);
        if (isSelectOfHours)
        {
            proAmountList = proAmountServer.getProAmountList(stringObjectHashMap);
        }
        else
        {
            proAmountList = proAmountServer.getProAmountListForDay(stringObjectHashMap);
        }

        proAmountList.forEach(iteam->{
            proAmounts.add(new ProAmount(iteam.getNode(),iteam.gettValue(),iteam.gettTime()));
        });
        for (int i=0;i<proAmounts.size()-1;i++)
        {
            proAmounts.get(i).settValue(proAmountList.get(i+1).gettValue()-proAmountList.get(i).gettValue());
        }
        proAmountList.remove(proAmountList.size()-1);
        sendData.put("ha",proAmounts);
    }

    public void handlerDateOfAmount(Map<String,Object>sendData,Integer year,Integer monthValue,@Nullable Integer dayOfMonth,Boolean isTotal,String selectMode,@Nullable Integer eid)
    {
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        ArrayList<Zone> selectData = new ArrayList<>();
        if (isTotal)
        {
            List<Zone> zoneList = null;
            List<Zone> zoneListNext=null;
            try {
                if ("hours".equals(selectMode))
                {
                    stringObjectHashMap.put("starttime", LocalDateTime.of(year,monthValue,dayOfMonth,0,0,0));
                    stringObjectHashMap.put("eid", 26);
                    stringObjectHashMap.put("endTime", LocalDateTime.of(year,monthValue,dayOfMonth+1,0,30,0));
                    stringObjectHashMap.put("addtime", 1);
                    zoneList = this.selectMode.selectModeForZoneServer(selectMode,stringObjectHashMap);
                    stringObjectHashMap.put("eid", 27);
                    zoneListNext = this.selectMode.selectModeForZoneServer(selectMode,stringObjectHashMap);
                    if (zoneList.size()!=zoneListNext.size())
                    {
                        throw new RuntimeException("两个总表查询个数不相等");
                    }
                }
                else
                {
                    stringObjectHashMap.put("starttime", LocalDateTime.of(year,monthValue,1,0,0,0));
                    stringObjectHashMap.put("eid", 26);
                    stringObjectHashMap.put("endTime", LocalDateTime.of(year,monthValue+1,1,0,30,0));
                    stringObjectHashMap.put("addtime", 1);
                    zoneList = this.selectMode.selectModeForZoneServer(selectMode,stringObjectHashMap);
                    stringObjectHashMap.put("eid", 27);
                    zoneListNext = this.selectMode.selectModeForZoneServer(selectMode,stringObjectHashMap);
                    if (zoneList.size()!=zoneListNext.size())
                    {
                        throw new RuntimeException("两个总表查询个数不相等");
                    }
                }


            } catch (RuntimeException e) {
                e.printStackTrace();
            }
            finally {
                ArrayList<Zone> zones = new ArrayList<>();
                for (int i=0;i<zoneList.size();i++)
                {
                    Zone zone = zoneList.get(i);
                    Zone zoneNext = zoneListNext.get(i);
                    selectData.add(new Zone(zone.getEid(),zone.gettValue()+zoneNext.gettValue(),zone.gettTime()));
                }
            }

        }
        else
        {
            if ("hours".equals(selectMode))
            {
                stringObjectHashMap.put("starttime", LocalDateTime.of(year,monthValue,dayOfMonth,0,0,0));
                stringObjectHashMap.put("eid", eid);
                stringObjectHashMap.put("endTime", LocalDateTime.of(year,monthValue,dayOfMonth+1,0,30,0));
                stringObjectHashMap.put("addtime", 1);
                List<Zone> zones = this.selectMode.selectModeForZoneServer(selectMode, stringObjectHashMap);
                selectData.addAll(zones);
            }
            else if ("days".equals(selectMode))
            {
                stringObjectHashMap.put("starttime", LocalDateTime.of(year,monthValue,1,0,0,0));
                stringObjectHashMap.put("eid", eid);
                stringObjectHashMap.put("endTime", LocalDateTime.of(year,monthValue+1,1,0,30,0));
                stringObjectHashMap.put("addtime", 1);
                List<Zone> zones = this.selectMode.selectModeForZoneServer(selectMode, stringObjectHashMap);
                selectData.addAll(zones);
            }

        }

        List<Zone> zones = new LinkedList<>();
        selectData.forEach(iteam->{
            zones.add(new Zone(iteam.getEid(),iteam.gettValue(),iteam.gettTime()));
        });
        for (int i=0;i<zones.size()-1;i++)
        {
           zones.get(i).settValue(selectData.get(i+1).gettValue()-selectData.get(i).gettValue());
        }
        zones.remove(zones.size()-1);
        sendData.put("main",zones);
    }

}
