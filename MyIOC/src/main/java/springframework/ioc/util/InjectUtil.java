package springframework.ioc.util;

import java.lang.reflect.Field;

/**
 * @author Ocean
 * @date 2019/7/20 20:16
 */

//完成属性的填充和注入
public class InjectUtil {

    public static void injectField(Field field,Object object,Object value) throws IllegalAccessException
    {
        if(field!=null)
        {
            field.setAccessible(true);
            field.set(object,value);
        }

    }

}
