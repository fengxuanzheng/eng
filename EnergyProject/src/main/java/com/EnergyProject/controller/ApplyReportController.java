package com.EnergyProject.controller;


import com.EnergyProject.pojo.ApplyReport;
import com.EnergyProject.server.APPLYREPORTervice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

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

}

