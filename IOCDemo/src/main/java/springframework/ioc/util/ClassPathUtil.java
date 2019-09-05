package springframework.ioc.util;

/**
 * @author Ocean
 * @date 2019/7/27 20:16
 */
//类的加载
public class ClassPathUtil {

    public static ClassLoader getDefaultClassLoader()
    {

        ClassLoader cl = null;
        //使用线程上下文类加载器
        cl= Thread.currentThread().getContextClassLoader();
        if(cl==null)
        {
            //如果没有contextClassLoader就实例化此类的classloader
            cl= ClassPathUtil.class.getClassLoader();
            if(cl==null)
            {
                try{
                    cl= ClassLoader.getSystemClassLoader();
                }catch (Throwable exception){

                }
            }
        }
        return cl;
    }


    public static Class loadClass(String className)
    {
        try {
            return getDefaultClassLoader().loadClass(className);
        }catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        return null;
    }


}
