import org.junit.jupiter.api.Test;

import java.util.HashSet;

public class JavaeeTest {

    @Test
    public void test()
    {
        int a=10;
        int c;
        if (a>11)
        {

             c=20;
        }
         c=24;
        a=30;

        String test="ffsefsese" +
                "fsefesfse" +
                "fsefsese";
        System.out.println(test);
        boolean bb=true;
        String s = String.valueOf(bb);
        System.out.println(s);
    }

    @Test
    public void test2()
    {
        HashSet<Integer> integers = new HashSet<>();    
        integers.add(1);
        integers.add(2);
        integers.add(3);
        integers.add(4);
        integers.add(4);
        integers.add(5);
        System.out.println(integers);

    }

     @Test
    public void test3()
     {
         int a=0;
         for (  a=0;a<20;a++)
         {
             System.out.println(a);
             Integer integer = Integer.valueOf(a);

             System.out.println(integer.hashCode());
         }
         System.out.println("循环结束" + a);
         System.out.println("循环结束" + a);
     }


}
