package com.EnergyProject.server;

import com.EnergyProject.pojo.ApplyReport;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 魔法少女
 * @since 2021-07-14
 */
public interface APPLYREPORTervice extends IService<ApplyReport> {
    public Integer insterApplyReport(ApplyReport applyReport);

}
