package com.EnergyProject.controller;

import com.EnergyProject.dao.ZoneDAO;
import com.EnergyProject.pojo.Zone;
import com.EnergyProject.server.SseEmitterService;
import com.EnergyProject.server.ZoneServer;
import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
@Slf4j
public class SseController
{
    @Autowired
    @Qualifier("poolTaskExecutor")
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;
    private static ConcurrentHashMap<String,SseEmitter> concurrentHashMap=new ConcurrentHashMap<>();
    private static ConcurrentHashMap<String,Integer>integerConcurrentHashMap=new ConcurrentHashMap<>();
    @Autowired
    private SseEmitterService sseEmitterService;

    @Autowired
    private ZoneServer zoneServer;
    @Autowired
    private ZoneDAO zoneDAO;
    @Autowired
    private DruidDataSource druidDataSource;
    private static LocalDateTime nextTime;
    private HashMap<String, Object> stringZoneHashMap = new HashMap<>();
   @RequestMapping(value = "/newsseemitter/{clientid}")
   public SseEmitter newgetSseEmitter(@PathVariable(value = "clientid",required = false) String id) throws IOException, InterruptedException, ExecutionException {
       Future<SseEmitter> submit = threadPoolTaskExecutor.submit(() -> {
           log.info("客户端开始连接");
           System.out.println(threadPoolTaskExecutor.getActiveCount());
           System.out.println(Thread.currentThread().getName());
           if (!concurrentHashMap.containsKey(id)) {
               log.info("新建连接对象");
               SseEmitter sseEmitter = new SseEmitter(0L);
               System.out.println(Thread.currentThread().getName());

               sseEmitter.onCompletion(() -> {
                   System.out.println("异步返回运行结束");
                   concurrentHashMap.remove(id);
               });
               sseEmitter.onError((throwable) -> {
                   System.out.println("发送错误异常:" + throwable);
               });
               sseEmitter.onTimeout(() -> {
                   System.out.println("连接超时");

                   concurrentHashMap.remove(id);
               });

               concurrentHashMap.put(id, sseEmitter);
               integerConcurrentHashMap.put(id,0);
               return sseEmitter;
           } else {
               return concurrentHashMap.get(id);
           }
       });
       return submit.get();

   }

    @RequestMapping("/send/{clientid}")
    public void sen(@PathVariable("clientid") String id)
    {
        System.out.println("开始"+Thread.currentThread().getName());
        sseEmitterService.sends(id,concurrentHashMap);
        System.out.println("结束"+Thread.currentThread().getName());
    }

    @RequestMapping ("/close/{clientid}")
    public void close(@PathVariable("clientid") String id)
    {
        log.warn("得到ID:"+id);
        System.out.println("调用关闭方法");
        SseEmitter sseEmitter = concurrentHashMap.get(id);
        sseEmitter.complete();
        concurrentHashMap.remove(id);
        integerConcurrentHashMap.remove(id);
    }

    @RequestMapping("/sqlserver")
    public void transferSqlserver(HttpServletResponse httpServletResponse)  {
       if (nextTime==null){
           sseEmitterService.sqlservertransferDao(concurrentHashMap,integerConcurrentHashMap,true);
           sendTotalZoneData();
           nextTime=LocalDateTime.now().plusHours(1);
       }
       else {
           LocalDateTime nowTime=LocalDateTime.now();
           if (nowTime.isAfter(nextTime))
           {
               sseEmitterService.sqlservertransferDao(concurrentHashMap,integerConcurrentHashMap,true);
               sendTotalZoneData();
               nextTime=nowTime.plusHours(1);
           }
       }
    }

    @GetMapping("/refreshExecute")
    public void everyRefreshExecute(HttpServletResponse httpServletResponse)  {

        sseEmitterService.sqlservertransferDao(concurrentHashMap,integerConcurrentHashMap,false);
        sendTotalZoneData();
    }


    @Scheduled(cron = "0 15 0 * * ?")
    //@GetMapping("/gettotaldata")
    public void timeAutoTask() {
        LocalDateTime now = LocalDateTime.now().plusDays(-1);
        Zone zone = zoneServer.getselectTotalZoneForDay(now.getDayOfMonth(), now.getMonthValue(), now.getYear(), 26);
        ArrayList<Zone> zones = new ArrayList<>();
        zones.add(zone);
        //Zone zone = zoneServer.getselectTotalZoneForDay(24, 5, 2021,26);
        Zone beforeYesterdayZone = zoneServer.getselectTotalZoneForDay(now.getDayOfMonth() - 1, now.getMonthValue(), now.getYear(), 26);
        Zone beforeYesterdayZone2 = zoneServer.getselectTotalZoneForDay(now.getDayOfMonth() - 2, now.getMonthValue(), now.getYear(), 26);
        int differenceData = 0;
        int differenceData2 = 0;
        String stringPercentage = null;
        if (zone != null && beforeYesterdayZone != null) {
            differenceData = zone.gettValue() - beforeYesterdayZone.gettValue();
        } else {
            differenceData = 0;
        }
        if (beforeYesterdayZone2 != null) {
            differenceData2 = beforeYesterdayZone.gettValue() - beforeYesterdayZone2.gettValue();
            Double percentage = (differenceData - differenceData2) / (differenceData2 * 1.0) * 100;
            NumberFormat compactNumberInstance = NumberFormat.getNumberInstance();
            compactNumberInstance.setMaximumFractionDigits(2);

             stringPercentage = compactNumberInstance.format(percentage) + "%";
            stringZoneHashMap.put("dd", zones);
            stringZoneHashMap.put("total", zone);
            stringZoneHashMap.put("diff", differenceData);
            stringZoneHashMap.put("percentage", stringPercentage);
            concurrentHashMap.forEach((key, value) -> {
                try {
                    value.send(SseEmitter.event().id(Integer.toString(integerConcurrentHashMap.get(key))).data(stringZoneHashMap));
                } catch (IOException e) {
                    e.printStackTrace();
                    value.complete();
                    concurrentHashMap.remove(key);
                    integerConcurrentHashMap.remove(key);
                }
            });
        }
    }

    

    private void sendTotalZoneData()
    {
        LocalDateTime now = LocalDateTime.now().plusDays(-1);
        Zone zone = zoneServer.getselectTotalZoneForDay(now.getDayOfMonth(), now.getMonthValue(), now.getYear(),26);
        //Zone zone = zoneServer.getselectTotalZoneForDay(27, 6, 2021,26);
        Zone beforeYesterdayZone = zoneServer.getselectTotalZoneForDay(now.getDayOfMonth()-1, now.getMonthValue(), now.getYear(),26);
        Zone beforeYesterdayZone2 = zoneServer.getselectTotalZoneForDay(now.getDayOfMonth()-2, now.getMonthValue(), now.getYear(),26);
        int differenceData=0;
        int differenceData2=0;
        String stringPercentage=null;
        if (zone!=null && beforeYesterdayZone!=null)
        {
             differenceData=zone.gettValue()-beforeYesterdayZone.gettValue();
        }
        else
        {
            differenceData=0;
        }
        if (beforeYesterdayZone2!=null)
        {
            differenceData2=beforeYesterdayZone.gettValue()-beforeYesterdayZone2.gettValue();
            Double percentage=(differenceData-differenceData2)/(differenceData2*1.0)*100;
            NumberFormat compactNumberInstance = NumberFormat.getNumberInstance();
            compactNumberInstance.setMaximumFractionDigits(2);

            stringPercentage= compactNumberInstance.format(percentage) + "%";
        }
        else
        {
            stringPercentage="0%";
        }

        stringZoneHashMap.put("total",zone);
        stringZoneHashMap.put("diff",differenceData);
        stringZoneHashMap.put("percentage",stringPercentage);
        concurrentHashMap.forEach((key,value)->{
            try {
                value.send(SseEmitter.event().id(Integer.toString(integerConcurrentHashMap.get(key))).data(stringZoneHashMap));
            } catch (IOException e) {
                e.printStackTrace();
                value.complete();
                concurrentHashMap.remove(key);
                integerConcurrentHashMap.remove(key);
            }
        });
    }

}

