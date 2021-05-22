package one2;

public class Animal implements Cloneable{
    private String name;

    public Animal() {
        super();
    }

    public Animal(String name) {
        super();
        this.name = name;
    }

    protected void mypro()
    {
        System.out.println("父类受保护方法");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "one2.Animal [name=" + name + "]";
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        // TODO Auto-generated method stub
        return super.clone();
    }

}

