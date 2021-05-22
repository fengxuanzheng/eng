import com.pojo.Shiro_username;

public class ChartTest {
    private char a='a';
    private Shiro_username shiro_username;

    public char getA() {
        return a;
    }

    public void setA(char a) {
        this.a = a;
    }

    public Shiro_username getShiro_username() {
        return shiro_username;
    }

    public void setShiro_username(Shiro_username shiro_username) {
        this.shiro_username = shiro_username;
    }



    @Override
    public String toString() {
        return "ChartTest{" +
                "a=" + a +
                ", shiro_username=" + shiro_username +
                '}';
    }
}
