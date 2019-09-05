package springframework.ioc.context;

import springframework.ioc.model.BeanDefinition;

import java.util.Map;

/**
 * @author Ocean
 * @date 2019/7/30 17:25
 */
public class ClassPathXmlApplicationContext extends ApplicationContext {

    //大致步骤就是，读取xml文件，解析标签，转换成BeanDefinition对象
    //
    public ClassPathXmlApplicationContext(String fileName) throws Exception {
        super(fileName);
        init();
        for(Map.Entry<String, BeanDefinition> en:beanDefinitionMap.entrySet())
        {
            String beanName = en.getKey();
            BeanDefinition bd = en.getValue();
            Object existBean = beanMap.get(beanName);

            if(existBean == null && bd.getScope().equals("singleton"))
            {
                Object objBean = createBean(bd);

                beanMap.put(beanName,objBean);

            }

        }


    }







    
}
