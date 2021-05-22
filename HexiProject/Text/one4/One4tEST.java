package one4;

import com.pojo.Shiro_username;

import org.junit.jupiter.api.Test;

public class One4tEST {

    @Test
    public void test() throws InstantiationException, IllegalAccessException {
        String[] strings = new String[10];
        Myinst<Shiro_username> shiro_usernameMyinst = new Myinst<>();
        Object shiro_username = shiro_usernameMyinst.ooutT();

        System.out.println(shiro_username);


    }
}
