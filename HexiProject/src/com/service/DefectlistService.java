package com.service;

import com.dao.DefectlistMapper;
import com.pojo.Defectlist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefectlistService {

    @Autowired
    private DefectlistMapper defectlistMapper;


    public List<Defectlist> getDefectlist()
    {
        List<Defectlist> defectlists = defectlistMapper.selectByExample(null);
        return defectlists;
    }


    public String insetdefectlist(Defectlist defectlist)
    {
        int i = defectlistMapper.insertSelective(defectlist);
        if (i!=0)
        {
            return "success";
        }
        return null;
    }

    public String deletedefectlist(Integer integer)
    {
        int i = defectlistMapper.deleteByPrimaryKey(integer);
        if (i!=0)
        {
            return "success";
        }
        return null;
    }




}
