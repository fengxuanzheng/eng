package com.EnergyProject.dao;

import com.EnergyProject.pojo.HikCode;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface HikCodeDAO extends BaseMapper<HikCode> {

    public List<HikCode> selectHikCodes(@Param("startTime") LocalDateTime startTime,@Param("endTime") LocalDateTime endTime);
}
