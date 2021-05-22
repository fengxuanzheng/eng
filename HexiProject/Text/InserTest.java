public class InserTest {

    private String name;
    public void out()
    {
        System.out.println("输出测试");

    }
    public static void staticout()
    {
        System.out.println("静态测试");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public class Persion{
        private String id;
        private Integer xx;
       

        public String getId() {
            out();
            return id;
        }


        public void setId(String id) {
            this.id = id;
        }

        public Integer getXx() {
            return xx;
        }

        public void setXx(Integer xx) {
            this.xx = xx;
        }
    }

    public static class Statper{
        private static String myname="test";
        private Integer myid=20;

        public static String getMyname() {

            return myname;
        }

        public static void setMyname(String myname) {

            Statper.myname = myname;
        }

        public Integer getMyid() {

            myname="ff";
            return myid;
        }

        public void setMyid(Integer myid) {
            this.myid = myid;

        }
    }

    public static void main(String[] args) {
        InserTest inserTest = new InserTest();


    }
}
