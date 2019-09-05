package springframework.ioc.factory;




import springframework.ioc.model.BeanDefinition;
import springframework.ioc.model.ConstrutorPO;
import springframework.ioc.util.BeanUtil;
import springframework.ioc.util.ClassPathUtil;
import springframework.ioc.util.InjectUtil;
import springframework.ioc.util.StringUtil;

import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @author Ocean
 * @date 2019/7/21 20:08
 */
public class DefaultListableBeanFactory implements BeanFactory{
    //缓存所有的bean实例
    protected static Map<String, Object> beanMap = new ConcurrentHashMap<String, Object>();
    //缓存已注册的beanDefinition的实例
    protected static Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();
    //缓存所有已注册的bean的beanName
    private static final Set<String> beanNameSet = Collections.synchronizedSet(new HashSet<String>());

    @Override
    public Object getBean(String name) throws Exception {

        //查看对象有没有被实例化过，如果有则直接返回
        // 其实应该是都已经实例化过了, 早在BeanDefinition的载入和解析之后就应该实例化了(lazyinit模式)
        Object bean = beanMap.get(name);
        BeanDefinition definition = new BeanDefinition();
        if(bean!=null)
        {
            return bean;
        }
        //如果这个bean没被实例化过就调用createBean方法进行实例化
        bean = createBean(beanDefinitionMap.get(name));

        return bean;
    }

    //注册bean
    protected void registerBean(String name,BeanDefinition beanDefinition) throws Exception
    {
        BeanDefinition existingDefinition = this.beanDefinitionMap.get(name);
        if(existingDefinition==null)
        {
            beanDefinitionMap.put(name,beanDefinition);
            beanNameSet.add(name);
        }else{
            throw new Exception("cannot registerbean for twice");
        }

    }

    // 将BeanDefinition结构转化成一个Object的对象(使用反射机制)
    protected Object createBean(BeanDefinition beanDefinition) throws Exception
    {
        String beanName = beanDefinition.getClassName();
        Class clz = ClassPathUtil.loadClass(beanName);
        if(clz==null)
        {
            throw new Exception("cannot find class by classname");
        }
        List<ConstrutorPO> construtorArgs = beanDefinition.getConstrutorPOS();
        if(construtorArgs!=null&&!construtorArgs.isEmpty())
        {
           List<Object> objectList = new ArrayList<Object>();
           for(ConstrutorPO construtorArg:construtorArgs)
           {
               objectList.add(getBean(construtorArg.getRef()));
           }

           return BeanUtil.createObeject(clz,clz.getConstructor(),objectList.toArray());

//           Object objBean = BeanUtil.createObeject(clz,clz.getConstructor(),objectList.toArray());
//           beanMap.put(beanName,objBean);
//           return objBean;

        }else {

            return BeanUtil.createObeject(clz,null,null);
//            Object objBean = BeanUtil.createObeject(clz,null,null);
//            beanMap.put(beanName,objBean);
//            return objBean;
        }

    }

    //填充bean属性
    // 对该Bean的Field一一进行填充, 如果有依赖其它Bean的也一并完成依赖注入
    private void pupulateBean(Object bean) throws Exception
    {
        Field []fields = bean.getClass().getSuperclass().getDeclaredFields();
        if(fields!=null&&fields.length>0)
        {
            for(Field field:fields)
            {
                String beanName = field.getName();
                beanName = StringUtil.decapitalize(beanName);
                if(beanNameSet.contains(beanName))
                {
                    Object fieldBean = getBean(beanName);
                    if(fieldBean!=null)
                    {
                        InjectUtil.injectField(field,bean,fieldBean);
                    }
                }

            }


        }



    }


}
