package springframework.ioc.entity;

/**
 * @author Ocean
 * @date 2019/7/24 12:09
 */
public class Person {
    private Student student;
    private Teacher teacher;

    public Student getStudent() {
        return student;
    }
    public void setStudent(Student student) {
        this.student = student;
    }
    public Teacher getTeacher() {
        return teacher;
    }
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
