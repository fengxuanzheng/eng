package com.EnergyProject.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MySessionListener implements SessionListener {
    @Override
    public void onStart(Session session) {
      log.trace("当前创建会话是:"+session.getId());
    }

    @Override
    public void onStop(Session session) {
        log.warn("当前停止会话是:"+session.getId());

    }

    @Override
    public void onExpiration(Session session) {
        log.warn("当前会话已经过期:"+session.getId());
    }
}
