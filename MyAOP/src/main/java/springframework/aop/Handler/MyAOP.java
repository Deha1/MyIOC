package springframework.aop.Handler;

import springframework.aop.annotation.Aspect;
import springframework.aop.proxy.Student;

import java.util.Map;

/**
 * @author Ocean
 * @date 2019/8/8 17:43
 */
public class MyAOP {

    public static void main(String[] args) {
//        String completeClassName1 = "springframework.aop.proxy.Student";
//        Object bean = ClassPathXmlApplicationContext.getBean(completeClassName1);
//        Student student = (Student) bean;
//        student.add(2, 3);
//        student.divide(10, 5);



    }

//    static {
//        init();
//    }
//
//    public static void init() {
//        updateBeanFromBeanFactory();
//    }
//
//    /**
//     * 扫描BeanFactory，找出方法上有@Aspect注解的bean，为其创建代理类对象，并替代原bean。
//     */
//    public static void updateBeanFromBeanFactory() {
//        for (Map.Entry<String, Object> entry : MyIOC.getBeanFactory().entrySet()) {
//            if (null != entry.getValue().getClass().getDeclaredAnnotation(Aspect.class)) {
//                MyProxy.updateBean(entry.getKey(), entry.getValue());
//            }
//        }
//    }
}
