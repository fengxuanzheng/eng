package com.EnergyProject.server;

import com.EnergyProject.pojo.EnergyUsername;
import com.baomidou.mybatisplus.extension.service.IService;

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
}
