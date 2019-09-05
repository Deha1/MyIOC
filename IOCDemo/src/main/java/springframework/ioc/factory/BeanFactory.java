package springframework.ioc.factory;

/**
 * @author Ocean
 * @date 2019/7/27 17:17
 */

//Ioc容器中的顶级父类
public interface BeanFactory {

    Object getBean(String name) throws Exception;

}
