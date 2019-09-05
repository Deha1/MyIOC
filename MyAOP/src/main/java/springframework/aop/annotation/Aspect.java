package springframework.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Ocean
 * @date 2019/8/6 0:13
 *
 * 扫描BeanFactory，找出方法上有@Aspect注解的bean，为其创建代理类对象，并替代原bean。
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {
}
