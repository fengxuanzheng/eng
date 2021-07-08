package zone;

import com.EnergyProject.dao.ZoneDAO;
import com.EnergyProject.pojo.Zone;
import com.EnergyProject.server.ZoneServer;
import com.alibaba.druid.pool.DruidDataSource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class ZoneTest {
    @Autowired
    private ZoneServer zoneServer;
    @Autowired
    private ZoneDAO zoneDAO;

    private List<Zone> searchZone=new ArrayList<>();

    @Autowired
    private DruidDataSource druidDataSource;
    @Test
    public void test() throws ParseException {
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("starttime",  LocalDateTime.of(2021,3,22,0,0,0));
        stringObjectHashMap.put("eid", 1);
        stringObjectHashMap.put("sizes", 1);
        stringObjectHashMap.put("addtime", 1);
        List<Zone> zoneList=new ArrayList<>();

            zoneList= zoneServer.getZoneList(stringObjectHashMap,30,2);
        System.out.println("数据库连接池连接情况:" + druidDataSource.getActiveCount());
        System.out.println(zoneList);
    }

    @Test
    public void test2() throws ParseException {
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("starttime",LocalDateTime.of(2021,3,22,0,0,0));
        stringObjectHashMap.put("eid", 1);
        stringObjectHashMap.put("sizes", 3);
        stringObjectHashMap.put("addtime", 1);
        List<Zone> zoneList = zoneServer.getZoneListForDay(stringObjectHashMap,3,1);
        System.out.println(zoneList);
    }

    @Test
    public void test3() throws ParseException {
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
       /* stringObjectHashMap.put("starttime", new Date());
        stringObjectHashMap.put("eid", 1);
        stringObjectHashMap.put("sizes", 1);
        stringObjectHashMap.put("addtime", 5);
        List<Zone> zoneList = zoneServer.getZoneListForMinute(stringObjectHashMap,1,2);
        System.out.println(zoneList);*/
    }

    //*****************************************************************************************

    @Test
    public void test4() throws ParseException {
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("starttime", LocalDateTime.of(2021,5,25,0,0,0));

        stringObjectHashMap.put("sizes", 1);
        stringObjectHashMap.put("addtime", 1);
        for (int i=1;i<=26;i++){
            stringObjectHashMap.put("eid",i);
            List<Zone> zoneList = zoneServer.getZoneList(stringObjectHashMap, 1, 2);

            searchZone.addAll(zoneList);
                stringObjectHashMap.put("starttime", LocalDateTime.of(2021,5,25,0,0,0));
                stringObjectHashMap.remove("nulltime");


        }
        System.out.println(searchZone);
    }
    @Test
    public void test6(){
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("starttime", LocalDateTime.of(2021,6,27,0,0,0));
        stringObjectHashMap.put("eid", 26);
       // stringObjectHashMap.put("endTime",LocalDateTime.of(2021,5,25,0,0,0));
        stringObjectHashMap.put("addtime", 1);
        stringObjectHashMap.put("sizes",1);
        /*List<Zone> lists = zoneDAO.getzonelistForDayForMangertest(stringObjectHashMap);
        System.out.println(lists);
        System.out.println(lists.get(0));
        System.out.println(stringObjectHashMap);*/
    }

    //*******************************************************************************************
    //以下为自由测试
    @Test
    public void test5()
    {
        LocalDateTime now = LocalDateTime.now().plusDays(-4);
        Month month = now.getMonth();
        System.out.println(month.maxLength());
        int year = now.getYear();
        int monthValue = now.getMonthValue();
        int dayOfMonth = now.getDayOfMonth();
        System.out.println(year);
        System.out.println(monthValue);
        System.out.println(dayOfMonth);
    }


}
