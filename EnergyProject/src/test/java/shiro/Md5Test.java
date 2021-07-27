package shiro;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.jupiter.api.Test;

public class Md5Test {

    @Test
    public void test()
    {
        String hashAlgorithmName="MD5";
        Object credentials="123456";
        Object salt= ByteSource.Util.bytes("UOG");
        int hashIterations=100;

        SimpleHash simpleHash = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
        System.out.println(simpleHash);


    }
}
