package com.EnergyProject.dao;

import com.EnergyProject.pojo.ApplyReport;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 魔法少女
 * @since 2021-07-14
 */
public interface ApplyReportMapper extends BaseMapper<ApplyReport> {

    List<String> getAllUsername();
    List<ApplyReport> getFirstThreeDaysOfApplyReportOfUnReviewed();
}
