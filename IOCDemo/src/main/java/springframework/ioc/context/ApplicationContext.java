package springframework.ioc.context;


import springframework.ioc.factory.XmlBeanFactory;

/**
 * @author Ocean
 * @date 2019/7/30 17:25
 */
public abstract class ApplicationContext extends XmlBeanFactory {

    public ApplicationContext(String fileName) {
        super(fileName);
    }
}
