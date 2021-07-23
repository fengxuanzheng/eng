package com.EnergyProject.dao;

import com.EnergyProject.pojo.ProAmount;

import java.util.List;
import java.util.Map;

public  interface ProAmountDAO {

    public  List<ProAmount> getProAmountList(Map<String, Object> inputParameter);
    public List<ProAmount> getProAmountListForDay(Map<String,Object> inputParameter);
    public ProAmount getSingleProAmount(Integer eid);
    public List<Integer> getAllProAmountNode();

}
