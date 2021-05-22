package one3;

public interface Myinfre2 extends Myinfre{

    @Override
    default void tests() {
        System.out.println("第二个接口重写");
        Myinfre.super.tests();
    }
}
