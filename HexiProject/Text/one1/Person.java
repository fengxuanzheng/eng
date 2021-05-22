package one1;

public class Person {

    private String name="Person";

    public void getpersonname()
    {
        System.out.println(this.name);
        System.out.println(this);
        this.mypriba();
    }
    private void mypriba()
    {
        System.out.println("父类私有方法");
    }


    public void personage()
    {
        System.out.println("祖先类年龄");
    }
    public void personfutername()
    {
        System.out.println("祖先类名字");
    }
}
