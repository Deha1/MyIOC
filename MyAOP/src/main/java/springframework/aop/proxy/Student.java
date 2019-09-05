package springframework.aop.proxy;

import springframework.aop.annotation.*;

/**
 * @author Ocean
 * @date 2019/8/6 0:12
 */


@Aspect//切面注解类，加了该注解就表示被注解的类的实例需要做动态代理。
//@MyComponent//自定义注解类，有该注解就表示被注解类交由自定义IOC容器管理了。
public class Student implements Person{

    @After
    @AfterReturning
    @Before
    @AfterThrowing
    @Override
    public int add(int a, int b) {
        System.out.println(" a + b = " + (a + b));
        return a + b;

    }

    @Around
    @Override
    public int divide(int a, int b) {
        System.out.println("a / b =" + (a/b));
        return a/b;
    }
}
