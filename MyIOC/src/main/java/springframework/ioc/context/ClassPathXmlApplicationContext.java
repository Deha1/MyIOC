package springframework.ioc.context;

import springframework.ioc.model.BeanDefinition;

import java.util.Map;

/**
 * @author Ocean
 * @date 2019/7/24 17:25
 */
public class ClassPathXmlApplicationContext extends ApplicationContext{

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
