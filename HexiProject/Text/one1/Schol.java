package one1;

public class Schol extends Student {
    public String names="子类属性";
    @Override
    public void personage() {
        System.out.println("子类年龄");
        //super.personage();
    }

    @Override
    public void personfutername() {
        System.out.println("子类名字");
       // super.personfutername();
       // super.personage();
    }
}
