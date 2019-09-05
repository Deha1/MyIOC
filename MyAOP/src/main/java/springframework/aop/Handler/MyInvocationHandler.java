package springframework.aop.Handler;

import springframework.aop.annotation.*;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author Ocean
 * @date 2019/8/6 10:14
 */
public class MyInvocationHandler implements InvocationHandler {
    //被代理类
    Object objectProxy;
    Object invoke;

    //使用jdk动态代理：实现了环绕通知、前置通知、后置通知等通知。
    public void setObject(Object objectProxy) {
        this.objectProxy = objectProxy;
    }


    /**
     * 动态代理：实现了环绕通知、前置通知、后置通知等通知。
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        // 入参的类型的处理，返回被代理对象真正要执行的那个方法：
        Method declaredMethod = argsHandler(method);
        // 环绕通知
        Boolean bool = false;
        if (null != declaredMethod.getAnnotation(Around.class)) ;
        aroundInform(declaredMethod, bool, method, args);

        // 后置、返回、异常、前置通知
        try {
            if (null != declaredMethod.getAnnotation(Before.class)) {
                System.out.println(declaredMethod.getName() + " begings with : " + declaredMethod.getParameters());
            }

            //通过反射，真正执行被代理对象的方法：
            invoke = method.invoke(objectProxy, args);

            if (null != declaredMethod.getAnnotation(AfterReturning.class)) {
                System.out.println(declaredMethod.getName() + "  ends with : " + invoke);
            }
        } catch (Exception e) {
            if (null != declaredMethod.getAnnotation(AfterThrowing.class)) {
                System.out.println(declaredMethod.getName() + " occurs exception : " + e);
            }
        }finally {
            if (null != declaredMethod.getAnnotation(After.class)) {
                System.out.println(declaredMethod.getName() + " ends.");
            }
        }
        return invoke;

    }

    /**
     * 入参的类型的处理，这个方法很重要。
     * @return 被代理对象真正要执行的那个方法
     * @param method 被代理对象的接口中声明的被代理方法
     */
    public Method argsHandler(Method method) throws NoSuchMethodException, SecurityException {
        Class<?>[] parameterTypes = method.getParameterTypes();
        switch (parameterTypes.length) {
            case 1:
                System.out.println("parameterTypes.length = 1 : " + parameterTypes[0]);
                return objectProxy.getClass().getDeclaredMethod(method.getName(), parameterTypes[0]);
            case 2:
                System.out.println("parameterTypes.length = 2 : " + parameterTypes[0] + " ; " + parameterTypes[1]);
                return objectProxy.getClass().getDeclaredMethod(method.getName(), parameterTypes[0], parameterTypes[1]);
            case 3:
                System.out.println("parameterTypes.length = 3 : " + parameterTypes[0] + " ; " + parameterTypes[1] + " ; "
                        + parameterTypes[2]);
                return objectProxy.getClass().getDeclaredMethod(method.getName(), parameterTypes[0], parameterTypes[1],
                        parameterTypes[2]);
            default:
                System.out.println("parameterTypes.length = 0 : " + parameterTypes.length);
                return objectProxy.getClass().getDeclaredMethod(method.getName());
        }


    }


    private void aroundInform(Method declaredMethod, Boolean bool, Method method, Object[] args) {
        if (bool) {
            try {
                System.out.println(declaredMethod.getName() + " begings with : " + declaredMethod.getParameters());
                invoke = method.invoke(objectProxy, args);
                System.out.println(declaredMethod.getName() + "  ends with : " + invoke);
            } catch (Exception e) {
                System.out.println(declaredMethod.getName() + " occurs exception : " + e);
            } finally {
                System.out.println(declaredMethod.getName() + " ends.");
            }
        }
    }


}
