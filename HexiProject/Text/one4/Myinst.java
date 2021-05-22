package one4;

import com.pojo.Shiro_username;



import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public  class  Myinst<T> implements Scholls<Shiro_username> {


    public Object ooutT() throws IllegalAccessException, InstantiationException {
        Type[] genericSuperclass = Myinst.class.getGenericInterfaces();
        System.out.println(genericSuperclass[0].getTypeName());
        var genericSuperclass1 = genericSuperclass[0];
        System.out.println(genericSuperclass1.getTypeName());
        genericSuperclass1.toString();
        Type[] actualTypeArguments = ((ParameterizedType) genericSuperclass1).getActualTypeArguments();
        Object o = ((Class) actualTypeArguments[0]).newInstance();



        return  o;
    }

    @Override
    public Shiro_username get(Shiro_username name) {
        return null;
    }

    @Override
    public String awls(Shiro_username age) {
        return null;
    }
}
