import com.pojo.Shiro_username;

public class PublicTest {

    private Shiro_username shiro_username;
    private String strings;
    int aa;

    public PublicTest() {
        this.shiro_username=new Shiro_username();
        this.strings = "这是第一个";
        this.aa=20;
    }

    public Shiro_username getShiro_username() {
        return shiro_username;
    }

    public void setShiro_username(Shiro_username shiro_username) {
        this.shiro_username = shiro_username;
    }

    public String getStrings() {
        return strings;
    }

    public void setStrings(String strings) {
        this.strings = strings;
    }

    public int getAa() {
        return aa;
    }

    public void setAa(int aa) {
        this.aa = aa;
    }

    @Override
    public String toString() {
        return "PublicTest{" +
                "shiro_username=" + shiro_username +
                ", strings='" + strings + '\'' +
                ", aa=" + aa +
                '}';
    }
}

class Puclic2{
    private Shiro_username shiro_username2;
    private String strings2;
    int aa2;

    public Puclic2() {
    }

    public Shiro_username getShiro_username2() {
        return shiro_username2;
    }

    public void setShiro_username2(Shiro_username shiro_username2) {
        this.shiro_username2 = shiro_username2;
    }

    public String getStrings2() {
        return strings2;
    }

    public void setStrings2(String strings2) {
        this.strings2 = strings2;
    }

    public int getAa2() {
        return aa2;
    }

    public void setAa2(int aa2) {
        this.aa2 = aa2;
    }

    @Override
    public String toString() {
        return "Puclic2{" +
                "shiro_username2=" + shiro_username2 +
                ", strings2='" + strings2 + '\'' +
                ", aa2=" + aa2 +
                '}';
    }
}
class MainTest{
    public static void main(String[] args) {
        PublicTest publicTest = new PublicTest();
        Shiro_username shiro_username = publicTest.getShiro_username();
        shiro_username.setUserid(10);
        System.out.println("修改前"+publicTest);
        Puclic2 puclic2 = new Puclic2();
        puclic2.setShiro_username2(shiro_username);
        Shiro_username shiro_username2 = puclic2.getShiro_username2();
        shiro_username2.setUserid(20);
        System.out.println("修改后"+publicTest);
        String strings = publicTest.getStrings();
        puclic2.setStrings2(strings);
        String strings2 = puclic2.getStrings2();
        String a="第一个";
        String b=a;
        System.out.println(b);
        b="第二个";
        System.out.println(b);
        System.out.println(a);


    }
}