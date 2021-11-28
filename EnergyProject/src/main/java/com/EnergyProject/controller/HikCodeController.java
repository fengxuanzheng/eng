package com.EnergyProject.controller;

import com.EnergyProject.pojo.HikCode;
import com.EnergyProject.pojo.HikCodeString;
import com.EnergyProject.server.HikCodeServer;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@Slf4j
public class HikCodeController {
    @Autowired
    private HikCodeServer hikCodeServer;

    @GetMapping("/selectHikCodes")
    public List<HikCodeString> selectHikCodes(@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startTime, @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endTime)
    {
        Assert.notNull(startTime,"开始时间不能为空");
        Assert.notNull(endTime,"结束时间不能为空");
        return hikCodeServer.selectHikCodes(startTime,endTime);
    }
    @GetMapping("/selectHikCodeUseString")
    public List<HikCodeString> selectHikCodeUseString(String code)
    {
        ArrayList<HikCodeString> hikCodeStrings = new ArrayList<>();
        HikCode hikCode = hikCodeServer.selectHikCodeUseString(code);
        List<HikCodeString> hikCodeStringsTarget = HikCodeServer.hikCodeToString(Collections.singletonList(hikCode), hikCodeStrings);
        return hikCodeStringsTarget;
    }
}
