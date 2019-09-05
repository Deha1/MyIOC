package springframework.ioc.util;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @author Ocean
 * @date 2019/7/27 20:16
 */

//Bean的实例化
//使用cglib动态代理生成object
//创建代理，传入要代理的父类，其实没有类需要代理，只是动态代理可以创建我们需要的父类
public class BeanUtil {

    public static <T> T createObeject(Class<?> clazz, Constructor constructor, Object[] args)
    {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(NoOp.INSTANCE);

        if(constructor==null)
        {
            return (T) enhancer.create();
        }else{
            return (T) enhancer.create(constructor.getParameterTypes(),args);
        }

    }

}
