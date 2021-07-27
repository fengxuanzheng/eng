package com.EnergyProject.service.impl;

import com.EnergyProject.pojo.EnergyUsername;
import com.EnergyProject.dao.EnergyUsernameMapper;
import com.EnergyProject.server.ENERGYUSERNAMEService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 魔法少女
 * @since 2021-07-27
 */
@Service
public class EnergyUsernameServiceImpl extends ServiceImpl<EnergyUsernameMapper, EnergyUsername> implements ENERGYUSERNAMEService {

    @Autowired
    private EnergyUsernameMapper energyUsernameMapper;
    @Override
    public EnergyUsername selectUsernameByUser(String username) {
        return energyUsernameMapper.selectOne(new QueryWrapper<EnergyUsername>().eq("username",username));
    }
}
