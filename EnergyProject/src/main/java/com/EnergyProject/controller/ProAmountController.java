package com.EnergyProject.controller;

import com.EnergyProject.pojo.ProAmount;
import com.EnergyProject.server.ProAmountServer;
import org.apache.commons.lang3.math.IEEE754rUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ProAmountController {

    @Autowired
    private ProAmountServer proAmountServer;
    private HashMap<String,Object> outPutHashMap=new HashMap<>();
    @GetMapping("/getAllProAmountNode")
    public List<Integer> getAllProAmountNode()
    {
        return proAmountServer.getAllProAmountNode();
    }

    @PostMapping ("/getProAmountList")
    public Map<String,Object> getProAmountList(@RequestBody(required = false) Map<String,Object> stringObjectMap)
    {
        List<ProAmount> proAmountList=null ;
        stringObjectMap.put("addtime",1);
        switch ((String) stringObjectMap.get("selectMode"))
        {
            case "hours"->{
                proAmountList= proAmountServer.getProAmountList(stringObjectMap);
            }
            case "days"->{
               proAmountList= proAmountServer.getProAmountListForDay(stringObjectMap);
            }
        }
        switch ((String) stringObjectMap.get("selectType"))
        {
            case "total"->{
                outPutHashMap.put("main",proAmountList);
                return outPutHashMap;
            }
            default -> {
                ArrayList<ProAmount> proAmounts = new ArrayList<>();
                proAmountList.forEach(item->{
                    proAmounts.add(new ProAmount(item.getNode(),item.gettValue(),item.gettTime()));
                });
                for (int i=0;i<proAmountList.size()-1;i++)
                {
                    proAmounts.get(i).settValue(proAmountList.get(i+1).gettValue()-proAmountList.get(i).gettValue());
                }
                proAmounts.remove(proAmounts.size()-1);
                outPutHashMap.put("main",proAmounts);
                return outPutHashMap;
            }
        }



    }

}
