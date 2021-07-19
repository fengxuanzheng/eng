package com.EnergyProject.controller;


import com.EnergyProject.pojo.ApplyReport;
import com.EnergyProject.server.APPLYREPORTervice;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

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
    public Boolean insterApplyReport(@RequestBody(required = false)ApplyReport applyReport)
    {
        Integer integer = applyreporTervice.insterApplyReport(applyReport);
        return integer!=null;
    }

    @GetMapping("/selectStatus")
    public Map<String, Object> selectStatus(@RequestParam(value = "currentPage",defaultValue = "2")Integer page, @RequestParam(value = "currentSize",defaultValue = "10") Integer size)
    {

        return applyreporTervice.selectStatus(page,size);
    }

    @PostMapping("/checkBoxAllData")
    public Boolean checkBoxAllData(@RequestBody(required = false) Integer[] selectId,@RequestParam("selectMode") String selectMode){

         Integer integer = applyreporTervice.updataApplyReportDataStatus(selectId, selectMode);
        return integer!=null;

    }

    @PostMapping("/singleApplyReportStatus")
    public Boolean singleApplyReportStatus(@RequestBody(required = false) ApplyReport applyReport)
    {
        Integer integer = applyreporTervice.singleApplyReportDataStatus(applyReport);
        return integer!=null;
    }

    @GetMapping("/getAllApplyReport")
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
    public Map<String,Object> selectOfApplyReportOfAccurate(@RequestBody(required = false) Map<String,Map<String,Object>> accurateSelect,@RequestParam(value = "currentPage",defaultValue = "1")Integer page, @RequestParam(value = "currentSize",defaultValue = "10") Integer size)
    {
        IPage<ApplyReport> applyReportIPage = applyreporTervice.selectOfApplyUsername(accurateSelect, page, size);
        outPutAllApplyReport.put("data",applyReportIPage.getRecords());
        outPutAllApplyReport.put("total",applyReportIPage.getTotal());
        return outPutAllApplyReport;
    }

    @GetMapping("/getAllUsername")
    public List<String> getAllUsername()
    {
        return applyreporTervice.getAllUsername();
    }

    @DeleteMapping("/deleteApplyReport")
    public Boolean deleteApplyReport(Integer id)
    {
        Integer integer = applyreporTervice.deleteApplyReport(id);
        return integer!=null;
    }
    @PostMapping("/deleteAllApplyReport")
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
    public Boolean updataApplyReportFinall(@RequestBody(required = false) ApplyReport applyReport)
    {
        Integer integer = applyreporTervice.updataApplyReportFinall(applyReport);
        return integer!=null;
    }

    @PostMapping("/sendRejectText")
    public Boolean sendRejectText(@RequestBody(required = false) Map<String,Object> rejectText,Integer id)
    {
        Integer integer = applyreporTervice.sendRejectText(rejectText, id);
        return integer!=null;
    }
}

