package springframework.ioc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * @author Ocean
 * @date 2019/7/27 16:47
 */

// 在运行时执行,这种类型的Annotations将被JVM保留
// 所以他们能在运行时被JVM或其他使用反射机制的代码所读取和使用
@Retention(RetentionPolicy.RUNTIME)
// 注解适用地方(字段和方法)
@Target({ ElementType.FIELD, ElementType.METHOD })
public @interface Inject {

    public String name() default "";

}
