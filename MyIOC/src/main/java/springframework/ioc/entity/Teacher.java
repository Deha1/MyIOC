package springframework.ioc.entity;

/**
 * @author Ocean
 * @date 2019/9/2 12:09
 */
public class Teacher {
    private Student student;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

}
