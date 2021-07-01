package one1;

import com.pojo.Shiro_username;
import org.junit.jupiter.api.Test;

import java.util.*;

public class One1Test {
    /*@Test
    public void test()
    {
        one1.OneTest oneTest = new OneTest();
        oneTest.shiros(new HashMap<String,String>());
    }*/

    @Test
    public void test2()
    {
        String aa="156468ff45";
        //String[] split = aa.split("[A-Za-z]");
        String s = aa.replaceAll("[A-z]", "");
        //System.out.println(split.length);
       // List<String> strings = Arrays.asList(split);
        System.out.println(s);
        String cc="转移\"特殊\"";
        System.out.println(cc);

        int[] ad=new int[]{4,5,54,21,65};
        for (int ds:ad)
        {
            System.out.println(ds);
        }
        int ff=10;
        boolean b = ff > 9;
        int cf=20;
        String bf="";
        int dd=0;
       int max=(ff>cf)? ff:cf;
       int feg=20-11;
        System.out.println("最大"+max);
        if (b)
        {
             System.out.println("成功");
        }
    }

    @Test
    public void test3()
    {
        String s="fdss";
        s=s+"dsf";
        s+="cfdsf";
        System.out.println(s);
        Shiro_username ss=new Shiro_username();
        String df=ss+"cds";
        System.out.println(df);
        System.out.println("自定义数据:"+ss);
    }

    @Test
    public void test4()
    {
        /*Student student = new Student();
        student.getpersonname();
        //student.mypriba();
        //student.mystyat();
        student.mydefer2();
        Myinp.mystyat();*/
    }

    @Test
    public void test5()
    {
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        Class aClass = stringStringHashMap.getClass();
        Class<ArrayList> arrayListClass = ArrayList.class;
        Class<?>[] classes = arrayListClass.getClasses();
        System.out.println(Arrays.asList(classes));
        Class<? super ArrayList> superclass = arrayListClass.getSuperclass();
        System.out.println(superclass.getName());


    }


    @Test
    public void test6()
    {
       /* Person schol = new Schol();
        //schol.personage();
        //schol.personfutername();
        Schol schol1 = new Schol();
        //schol1.personfutername();
        String names = schol1.names;
        System.out.println(names);
*/
    }

    @Test
    public void test7()
    {
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        Class<? extends HashMap> aClass = stringStringHashMap.getClass();
        Class<HashMap> hashMapClass = HashMap.class;
        Class<?>[] interfaces = aClass.getInterfaces();
        System.out.println(Arrays.asList(interfaces));
        System.out.println("*********************");
        Class<?>[] interfaces1 = hashMapClass.getInterfaces();
        System.out.println(Arrays.asList(interfaces1));
        System.out.println(aClass == hashMapClass);
    }
}
