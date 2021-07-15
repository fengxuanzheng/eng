package com.EnergyProject.service.impl;

import com.EnergyProject.pojo.ApplyReport;
import com.EnergyProject.dao.ApplyReportMapper;
import com.EnergyProject.server.APPLYREPORTervice;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 魔法少女
 * @since 2021-07-14
 */
@Service
public class ApplyReportServiceImpl extends ServiceImpl<ApplyReportMapper, ApplyReport> implements APPLYREPORTervice {

    @Autowired
    private ApplyReportMapper applyReportMapper;

    private Map<String,Object> outPutPageData=new HashMap<>();

    public Integer insterApplyReport(ApplyReport applyReport)
    {
       return applyReportMapper.insert(applyReport);
    }

    @Override
    public Map<String, Object> selectStatus(Integer currentPage, Integer pageSize) {
        QueryWrapper<ApplyReport> applyReportQueryWrapper = new QueryWrapper<ApplyReport>();
        Page<ApplyReport> applyReportPage = new Page<>();
        applyReportPage.setCurrent(currentPage).setSize(pageSize);
        Page<ApplyReport> applyReportPageList = applyReportMapper.selectPage(applyReportPage, new QueryWrapper<ApplyReport>().in("status", Arrays.asList("未审核", "拒绝")).orderByAsc("id"));
        long total = applyReportPageList.getTotal();
        System.out.println(total);
        List<ApplyReport> records = applyReportPageList.getRecords();
        outPutPageData.put("total",total);
        outPutPageData.put("data",records);
        return outPutPageData;
    }

    @Override
    public Integer updataApplyReportDataStatus(Integer[] selectId, String selectMode) {
        ApplyReport applyReport = new ApplyReport();
        applyReport.setStatus(selectMode);
       return applyReportMapper.update(applyReport,new QueryWrapper<ApplyReport>().in("id",Arrays.asList(selectId)));
    }
    @Override
    public Integer singleApplyReportDataStatus(ApplyReport applyReport)
    {
       return applyReportMapper.updateById(applyReport);
    }

}
