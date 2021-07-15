package com.EnergyProject.controller;


import com.EnergyProject.pojo.ApplyReport;
import com.EnergyProject.server.APPLYREPORTervice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.Arrays;
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

    @PostMapping("/insterApplyReport")
    public Boolean insterApplyReport(@RequestBody(required = false)ApplyReport applyReport)
    {
        Integer integer = applyreporTervice.insterApplyReport(applyReport);
        return integer!=null;
    }

    @GetMapping("/selectStatus")
    public Map<String, Object> selectStatus(@RequestParam(value = "currentPage",defaultValue = "2")Integer page, @RequestParam(value = "currentSize",defaultValue = "2") Integer size)
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

}

