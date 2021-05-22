package one1;

import org.springframework.web.bind.annotation.PostMapping;

public class Student extends Person implements Myinp {

    private String studenname="学生";


    @Override
    public void personage() {
        System.out.println("父亲类年龄");
        super.personage();
    }
}
