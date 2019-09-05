package springframework.ioc;

import org.junit.Test;
import springframework.ioc.context.ApplicationContext;
import springframework.ioc.context.ClassPathXmlApplicationContext;
import springframework.ioc.entity.Person;
import springframework.ioc.entity.Student;
import springframework.ioc.entity.Teacher;

/**
 * @author Ocean
 * @date 2019/8/7 16:06
 */
public class TestIoc {


    @Test
    public void iocTest1()throws Exception {
        ApplicationContext context =  new ClassPathXmlApplicationContext("lib/applicationContext.xml");
            Person p=(Person)context.getBean("person");
            Person p1=(Person)context.getBean("person");
            //测试单例
            System.out.println(p==p1);
            System.out.println(p);
            System.out.println(p1);


            Student student1 = (Student)context.getBean("student");
            String stuname = student1.getName();
            int stuage = student1.getAge();
            System.out.println(stuname);
            System.out.println(stuage);








    }

}
