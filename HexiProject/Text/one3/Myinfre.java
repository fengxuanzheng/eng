package one3;

public interface Myinfre {

    public String get(int age);
    public void setmy(String name);
    public default void tests()
    {
        System.out.println("接口默认");
    }

}
