package com.EnergyProject.service.impl;

import com.EnergyProject.pojo.ApplyReport;
import com.EnergyProject.dao.ApplyReportMapper;
import com.EnergyProject.server.APPLYREPORTervice;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Integer insterApplyReport(ApplyReport applyReport)
    {
       return applyReportMapper.insert(applyReport);
    }

}
