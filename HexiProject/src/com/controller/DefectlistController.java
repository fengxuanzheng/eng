package com.controller;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pojo.Defectlist;
import com.service.DefectlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class DefectlistController {
    @Autowired
    private DefectlistService defectlistService;



    @RequestMapping("/getDefectlist")
    @ResponseBody
    public Map<String, Object> getDefectlist(@RequestParam(value = "page",defaultValue = "1") Integer page, @RequestParam(value = "sizes",defaultValue = "5") Integer sizes)
    {
        Page<Object> objects = PageHelper.startPage(page, sizes);
        List<Defectlist> defectlist = defectlistService.getDefectlist();
        long total = objects.getTotal();
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("datas",defectlist);
        stringObjectHashMap.put("total",total);
        return stringObjectHashMap;
    }

    @RequestMapping("/getnewDefectlist")
    @ResponseBody
    public String insertdefectlist(@RequestBody(required = false)Defectlist defectlist)
    {
        String insetdefectlist = defectlistService.insetdefectlist(defectlist);
        return insetdefectlist;
    }

    @RequestMapping("/deleteefectlist")
    @ResponseBody
    public String deletedefectlist(Integer id)
    {
        return defectlistService.deletedefectlist(id);

    }


}
