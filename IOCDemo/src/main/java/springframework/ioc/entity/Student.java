package springframework.ioc.entity;

/**
 * @author Ocean
 * @date 2019/8/8 12:09
 */
public class Student {
    private String name;
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
