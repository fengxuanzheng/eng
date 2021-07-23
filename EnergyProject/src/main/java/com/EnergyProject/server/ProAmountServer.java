package com.EnergyProject.server;

import com.EnergyProject.dao.ProAmountDAO;
import com.EnergyProject.pojo.ProAmount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProAmountServer {

    @Autowired
    private ProAmountDAO proAmountDAO;

    public List<ProAmount> getProAmountList(Map<String,Object> inputProgramer)
    {
        return proAmountDAO.getProAmountList(inputProgramer);
    }

    public List<ProAmount> getProAmountListForDay(Map<String,Object> inputProgramer)
    {
        return proAmountDAO.getProAmountListForDay(inputProgramer);
    }
    public ProAmount getSingleProAmount(Integer node)
    {
       return proAmountDAO.getSingleProAmount(node);
    }

    public List<Integer> getAllProAmountNode()
    {
        return proAmountDAO.getAllProAmountNode();
    }
}
