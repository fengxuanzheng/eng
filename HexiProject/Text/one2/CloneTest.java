package one2;

import one2.Animal;

public class CloneTest  {
    public static void main(String[] args) {
        Animal a1 = new Animal("花花");
        try {
            Animal a2 =  (Animal)a1.clone();
            System.out.println("原始对象：" + a1);
            a2.setName("毛毛");
            System.out.println("clone之后的对象：" + a2);
            System.out.println("修改之后a1：" + a1);
            System.out.println(a1==a2);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}


