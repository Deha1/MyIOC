package springframework.ioc;

import org.junit.Test;
import springframework.ioc.context.ApplicationContext;
import springframework.ioc.context.ClassPathXmlApplicationContext;
import springframework.ioc.entity.Person;

/**
 * @author Ocean
 * @date 2019/7/23 16:06
 */
public class TestIoc {


    @Test
    public void iocTest1()throws Exception{
        ApplicationContext context =  new ClassPathXmlApplicationContext("lib/applicationContext.xml");
            Person p=(Person)context.getBean("person");


    }

}
