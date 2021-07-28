package com.EnergyProject.utils;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
public class MyMetaObjectHandler  implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject,"updateTime",LocalDateTime::now,LocalDateTime.class);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
      /*  Object checkTime = getFieldValByName("checkTime", metaObject);
        if (checkTime==null)
        {
            log.info(checkTime+"为空开始插入操作");
            MetaObjectHandler email1 = setFieldValByName("checkTime", "fengxuanzheng@sina.com", metaObject);
        }*/
        log.warn("为空开始插入操作");
        this.strictUpdateFill(metaObject, "checkTime", LocalDateTime::now, LocalDateTime.class);
        this.strictInsertFill(metaObject,"updateTime",LocalDateTime::now,LocalDateTime.class);

    }
}