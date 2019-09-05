package springframework.aop.Handler;

import java.lang.reflect.Proxy;

/**
 * @author Ocean
 * @date 2019/8/6 17:14
 */
public class MyProxy {
    public static Object getProxyInstance(Object obj) {
        MyInvocationHandler handler = new MyInvocationHandler();
        handler.setObject(obj);

        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), handler);
    }
    /**
     * 对于有@InOutLog注解的，用代理类的bean来替代BeanFactory中的被代理类的bean。
     * 这一步很重要，因为当执行到bean.method()，执行的就一定是bean对应的method()方法，
     * 如果此时没有用代理类对象去替换，那么执行的就是没有InOutLog的原来的那个方法。
     */
//    public static void updateBean(String completeClassName, Object object) {
//        MyIOC.updateBeanFromBeanFactory(completeClassName, getProxyInstance(object));// (全类名，代理类的bean)
//    }

}
