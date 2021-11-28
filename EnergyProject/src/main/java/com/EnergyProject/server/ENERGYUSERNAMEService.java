package com.EnergyProject.server;

import com.EnergyProject.pojo.EnergyUsername;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 魔法少女
 * @since 2021-07-27
 */
public interface ENERGYUSERNAMEService extends IService<EnergyUsername> {

    public EnergyUsername selectUsernameByUser(String username);
    public Integer rejectEnergyUsername(EnergyUsername energyUsername);
    public Page<EnergyUsername> getEnergyUsernameAllData(Integer page, Integer size);
    public Integer updataStatus(Integer id,String status);
    public Integer delectEnergyUsername(Integer id);

    Boolean checkIsUser(String username);
}
