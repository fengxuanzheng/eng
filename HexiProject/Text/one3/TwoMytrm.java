package one3;

public class TwoMytrm  implements Myinfre,Myinfre2 {
    @Override
    public String get(int age) {
        return "子类重写";
    }

    @Override
    public void setmy(String name) {

    }


    public void testss() {
        System.out.println("子类重写");
        //super.tests();
        tests();//总结如果一个类实行的两个接口其中有一个接口是另外一个接口的子接口,则优先选取最具体实现(选取子接口),如果实现类有父类则仍然遵循类优先原则
        //Myinfre.super.tests();//,特别说明语法:接口.super.xx(),如果实现类的所实现所以接口中存在同名接口则会:默认 super 调用中的类型限定符错误,因为实现类实现有多个同名接口此语法不能区分调那个同名接口
    }
}
