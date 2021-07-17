package com.EnergyProject.server;

import com.EnergyProject.pojo.ApplyReport;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

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

    public Map<String, Object> selectStatus(Integer currentPage, Integer pageSize);

    public Integer updataApplyReportDataStatus(Integer[] selectId, String selectMode);
    public Integer singleApplyReportDataStatus(ApplyReport applyReport);

    IPage<ApplyReport> getAllApplyReport(Integer page, Integer size);

    IPage<ApplyReport> selectOfApplyUsername(Map<String,Object> accurateSelect,Integer page, Integer size);


    List<String> getAllUsername();
}
