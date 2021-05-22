
import one2.MyAnimal;
import org.junit.jupiter.api.Test;
import org.springframework.format.datetime.DateFormatter;


import javax.swing.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;


public class NewTest {






    @Test
    public void test() throws ParseException {
        Date date = new Date();
        DateFormatter dateFormatter = new DateFormatter();
        dateFormatter.setPattern("yyyy/MM/dd HH:mm:ss");
        Locale locale = new Locale("zh", "CN");
        Date zh_cn = dateFormatter.parse("2020/10/20 20:14:22", locale);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        simpleDateFormat.applyPattern("yyyy/MM/dd HH:mm:ss");
        System.out.println(simpleDateFormat.toPattern());
        String format = simpleDateFormat.format(date);
        System.out.println(format);

        System.out.println(date);
        System.out.println(zh_cn);
    }

    @Test
    public void test2()
    {
        InserTest inserTest = new InserTest();
        InserTest.Statper statper = new InserTest.Statper();
        Integer myid = statper.getMyid();
        InserTest.Persion persion = new InserTest().new Persion();
        persion.getId();

        statper.setMyname("ff");
        InserTest.Statper.setMyname("ffd");
        String myname = InserTest.Statper.getMyname();
        System.out.println(myname);
    }

    @Test
    public void test3()
    {
        ChartTest chartTest = new ChartTest();
        System.out.println(chartTest);
        char a = chartTest.getA();
        int c=(int)a;
        System.out.println("测试"+c);
        System.out.println(chartTest.getShiro_username());

    }
    @Test
    public void test4() {
      /* String dd="123";
        System.out.println ("5+5="+5+5);
        boolean aa=false;
        System.out.println(100+"15");*/

        int ad=10;
        ad+=15;
        ad*=2;
        System.out.println(ad);
        int i1 = 10;
        int i2 = 20;
        int i = i1++;
        System.out.print("i="+i);
        System.out.println("i1="+i1);
        i = ++i1;
        System.out.print("i="+i);
        System.out.println("i1="+i1);
        i = i2--;
        System.out.print("i="+i);
        System.out.println("i2="+i2);
        i = --i2;
        System.out.print("i="+i);
        System.out.println("i2="+i2);
    }


    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.print("年：");
        int year = input.nextInt();

        System.out.print("月：");
        int month = input.nextInt();

        System.out.print("日：");
        int day = input.nextInt();

        int days = day;

        //加前面几个月的满月天数
        switch(month){
            case 12:
                //前面11个月的总天数
                //days += 第11月的天数;
                days += 30;
            case 11:
                //前面10个月的总天数
                //days += 第10月的天数;
                days += 31;
            case 10:
                days += 30;//九月
            case 9:
                days += 31;//八月
            case 8:
                days += 31;//七月
            case 7:
                days += 30;//六月
            case 6:
                days += 31;//五月
            case 5:
                days += 30;//四月
            case 4:
                days += 31;//三月
            case 3:
                days += 28;//二月
				/*if(闰年){
					days++;
				}
				*/
                if(year % 4 ==0 && year % 100 != 0 || year%400==0){
                    days++;
                }
            case 2:
                days += 31;//一月
        }

        System.out.println(year + "年" + month + "月" + day + "日是这一年的第" + days + "天");
        Scanner inputs = new Scanner(System.in);

        System.out.print("年：");
        int years = inputs.nextInt();

        System.out.print("月：");
        int months = inputs.nextInt();

        System.out.print("日：");
        int dayss = inputs.nextInt();

        int daysss = day;

        //加前面几个月的满月天数
        switch(month){
            case 12:
                //前面11个月的总天数
                //days += 第11月的天数;
                days += 30;
            case 11:
                //前面10个月的总天数
                //days += 第10月的天数;
                days += 31;
            case 10:
                days += 30;//九月
            case 9:
                days += 31;//八月
            case 8:
                days += 31;//七月
            case 7:
                days += 30;//六月
            case 6:
                days += 31;//五月
            case 5:
                days += 30;//四月
            case 4:
                days += 31;//三月
            case 3:
                days += 28;//二月
				/*if(闰年){
					days++;
				}
				*/
                if(year % 4 ==0 && year % 100 != 0 || year%400==0){
                    days++;
                }
            case 2:
                days += 31;//一月
        }

        System.out.println(year + "年" + month + "月" + day + "日是这一年的第" + days + "天");

    }

    @Test
    public void test6()
    {
        int aa=106;
        boolean ta=true;
        switch (aa)
        {
            case 12:
        }

        if (100==100L)
        {

        }

        for (int i=0;i<=100;i++)
        {
            System.out.println(i);
            for (int j=0;j<=i;j++)
            {
                if (j==20)
                {

                    break;
                }
                System.out.println("j="+j);


            }
        }

    }


    @Test
    public void test7()
    {
        MyAnimal myAnimal = new MyAnimal();


    }



   
}
