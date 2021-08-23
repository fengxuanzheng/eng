package com.EnergyProject.controller;


import com.EnergyProject.pojo.ApplyReport;
import com.EnergyProject.pojo.ProAmount;
import com.EnergyProject.server.APPLYREPORTervice;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 魔法少女
 * @since 2021-07-14
 */
@RestController
@RequestMapping("/applyReport")
public class ApplyReportController {

    @Autowired
    private APPLYREPORTervice applyreporTervice;

    private Map<String,Object>outPutAllApplyReport=new HashMap<>();

    @PostMapping("/insterApplyReport")
    @RequiresRoles(value = {"管理员","普通"})
    public Boolean insterApplyReport(@RequestBody(required = false)ApplyReport applyReport)
    {
        Integer integer = applyreporTervice.insterApplyReport(applyReport);
        return integer!=null;
    }

    @GetMapping("/selectStatus")
    @RequiresRoles(value = {"管理员"})
    public Map<String, Object> selectStatus(@RequestParam(value = "currentPage",defaultValue = "2")Integer page, @RequestParam(value = "currentSize",defaultValue = "10") Integer size)
    {

        return applyreporTervice.selectStatus(page,size);
    }

    @PostMapping("/checkBoxAllData")
    @RequiresRoles(value = {"管理员"})
    public Boolean checkBoxAllData(@RequestBody(required = false) Integer[] selectId,@RequestParam("selectMode") String selectMode){

         Integer integer = applyreporTervice.updataApplyReportDataStatus(selectId, selectMode);
        return integer!=null;

    }

    @PostMapping("/singleApplyReportStatus")
    @RequiresRoles(value = {"管理员"})
    public Boolean singleApplyReportStatus(@RequestBody(required = false) ApplyReport applyReport)
    {
        Integer integer = applyreporTervice.singleApplyReportDataStatus(applyReport);
        return integer!=null;
    }

    @GetMapping("/getAllApplyReport")
    @RequiresRoles(value = {"管理员"})
    public Map<String,Object> getAllApplyReport(@RequestParam(value = "currentPage",defaultValue = "1")Integer page, @RequestParam(value = "currentSize",defaultValue = "10") Integer size,@RequestParam(value = "isReject",defaultValue = "false") Boolean isReject)
    {
        if (!isReject)
        {
            IPage<ApplyReport> allApplyReport = applyreporTervice.getAllApplyReport(page, size);
            outPutAllApplyReport.put("data",allApplyReport.getRecords());
            outPutAllApplyReport.put("total",allApplyReport.getTotal());
            return outPutAllApplyReport;
        }
        else
        {
            IPage<ApplyReport> allApplyReportForReject = applyreporTervice.getAllApplyReportForReject(page, size);
            outPutAllApplyReport.put("data",allApplyReportForReject.getRecords());
            outPutAllApplyReport.put("total",allApplyReportForReject.getTotal());
            return outPutAllApplyReport;
        }

    }

    @PostMapping("/selectOfApplyReportOfAccurate")
    @RequiresRoles(value = {"管理员"})
    public Map<String,Object> selectOfApplyReportOfAccurate(@RequestBody(required = false) Map<String,Map<String,Object>> accurateSelect,@RequestParam(value = "currentPage",defaultValue = "1")Integer page, @RequestParam(value = "currentSize",defaultValue = "10") Integer size)
    {
        IPage<ApplyReport> applyReportIPage = applyreporTervice.selectOfApplyUsername(accurateSelect, page, size);
        outPutAllApplyReport.put("data",applyReportIPage.getRecords());
        outPutAllApplyReport.put("total",applyReportIPage.getTotal());
        return outPutAllApplyReport;
    }

    @GetMapping("/getAllUsername")
    @RequiresRoles(value = {"管理员"})
    public List<String> getAllUsername()
    {
        return applyreporTervice.getAllUsername();
    }

    @DeleteMapping("/deleteApplyReport")
    @RequiresRoles(value = {"管理员"})
    public Boolean deleteApplyReport(Integer id)
    {
        Integer integer = applyreporTervice.deleteApplyReport(id);
        return integer!=null;
    }
    @PostMapping("/deleteAllApplyReport")
    @RequiresRoles(value = {"管理员"})
    public Boolean deleteAllApplyReport(@RequestBody(required = false) List<Integer> ids)
    {
        Integer integer = applyreporTervice.deleteAllApplyReport(ids);
        return integer!=null;
    }
    @GetMapping("/updataApplyReport")
    public ApplyReport updataApplyReport(Integer id)
    {
      return   applyreporTervice.updataApplyReport(id);
    }
    @PostMapping("/updataApplyReportFinall")
    @RequiresRoles(value = {"管理员"})
    public Boolean updataApplyReportFinall(@RequestBody(required = false) ApplyReport applyReport)
    {
        Integer integer = applyreporTervice.updataApplyReportFinall(applyReport);
        return integer!=null;
    }

    @PostMapping("/sendRejectText")
    @RequiresRoles(value = {"管理员"})
    public Boolean sendRejectText(@RequestBody(required = false) Map<String,Object> rejectText,Integer id)
    {
        Integer integer = applyreporTervice.sendRejectText(rejectText, id);
        return integer!=null;
    }

    @GetMapping("/agreeRejectApplyReportForComment")
    @RequiresRoles(value = {"管理员"})
    public Boolean agreeRejectApplyReportForComment(Integer id)
    {
        Integer integer = applyreporTervice.agreeRejectApplyReportForComment(id);
        return integer!=null;
    }

    @GetMapping("/getRejectModeData")
    @RequiresRoles(value = {"管理员","普通"})
    public Map<String,Object> getRejectModeData(@RequestParam(value = "page",defaultValue = "1") Integer page,@RequestParam(value = "size",defaultValue = "10") Integer size)
    {
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        Page<ApplyReport> rejectModeData = applyreporTervice.getRejectModeData(page, size);
        long total = rejectModeData.getTotal();
        List<ApplyReport> records = rejectModeData.getRecords();
        stringObjectHashMap.put("data",records);
        stringObjectHashMap.put("total",total);
        return stringObjectHashMap;

    }

    @GetMapping("/getApplyReportOfAmountData")
    public List<ApplyReport> getApplyReportOfAmountData(@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startTime, @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")LocalDateTime endTime)
    {
       return applyreporTervice.getApplyReportOfAmountData(startTime,endTime);
    }
    @GetMapping("/getNowApplyReportOfSingle")
    public List<ApplyReport> getNowApplyReportOfSingle(@RequestParam(value = "selectMode",defaultValue = "hours") String selectMode)
    {
        LocalDateTime now = LocalDateTime.now();
        if ("hours".equals(selectMode))
        {
            return applyreporTervice.getApplyReportOfAmountData(LocalDateTime.of(now.getYear(),now.getMonthValue(),now.getDayOfMonth(),0,0,0),LocalDateTime.of(now.getYear(),now.getMonthValue(),now.getDayOfMonth()+1,0,0,0));
        }
        else
        {
            return applyreporTervice.getApplyReportOfAmountData(LocalDateTime.of(now.getYear(),now.getMonthValue(),1,0,0,0),LocalDateTime.of(now.getYear(),now.getMonthValue()+1,28,0,0,0));
        }


    }

    @GetMapping("/totalDataApplyReport")
    public List<ApplyReport> totalDataApplyReport()
    {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startTime = LocalDateTime.of(now.getYear(), now.getMonthValue(), now.getDayOfMonth(), 0, 0, 0);
        return applyreporTervice.getApplyReportOfAmountData(startTime, startTime.plusDays(1));
    }
    @GetMapping("/getFirstThreeDaysOfApplyReport")
    public List<ApplyReport> getFirstThreeDaysOfApplyReport()
    {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime lastThreeDayStartTime = now.plusDays(-7);
        return applyreporTervice.getApplyReportOfAmountData(lastThreeDayStartTime,now);
    }

    @GetMapping("/getFirstThreeDaysOfApplyReportOfUnReviewed")
    public List<ApplyReport> getFirstThreeDaysOfApplyReportOfUnReviewed()
    {
        return applyreporTervice.getFirstThreeDaysOfApplyReportOfUnReviewed();
    }
}

