package com.EnergyProject.service.impl;

import com.EnergyProject.pojo.EnergyUsername;
import com.EnergyProject.dao.EnergyUsernameMapper;
import com.EnergyProject.server.ENERGYUSERNAMEService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

    @Override
    public Integer rejectEnergyUsername(EnergyUsername energyUsername) {

       return energyUsernameMapper.insert(energyUsername);
    }

    @Override
    public Page<EnergyUsername> getEnergyUsernameAllData(Integer page,Integer size) {
        return energyUsernameMapper.selectPage(new Page<EnergyUsername>().setCurrent(page).setSize(size),new QueryWrapper<EnergyUsername>().orderByAsc("userId"));
    }

    @Override
    public Integer updataStatus(Integer id,String status) {
        EnergyUsername energyUsername = new EnergyUsername();
        energyUsername.setUserId(id);
        energyUsername.setStatus(status);
        return energyUsernameMapper.updateById(energyUsername);
    }

    @Override
    public Integer delectEnergyUsername(Integer id) {
        return energyUsernameMapper.deleteById(id);
    }
}
