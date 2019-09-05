package springframework.ioc.factory;




import org.apache.commons.beanutils.BeanUtils;
import springframework.ioc.model.BeanDefinition;
import springframework.ioc.model.ConstructorPO;
import springframework.ioc.model.PropertyPO;
import springframework.ioc.util.BeanUtil;
import springframework.ioc.util.ClassPathUtil;
import springframework.ioc.util.InjectUtil;
import springframework.ioc.util.StringUtil;

import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @author Ocean
 * @date 2019/7/25 20:08
 */
public class DefaultListableBeanFactory implements BeanFactory {
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
        if (bean != null) {
            return bean;
        }
        //如果这个bean没被实例化过就调用createBean方法进行实例化
        bean = createBean(beanDefinitionMap.get(name));

        return bean;
    }

    //注册bean
    protected void registerBean(String name, BeanDefinition beanDefinition) throws Exception {
        BeanDefinition existingDefinition = this.beanDefinitionMap.get(name);
        if (existingDefinition == null) {
            beanDefinitionMap.put(name, beanDefinition);
            beanNameSet.add(name);
        } else {
            throw new Exception("cannot registerbean for twice");
        }

    }

    // 将BeanDefinition结构转化成一个Object的对象(使用反射机制)
    protected Object createBean(BeanDefinition beanDefinition) throws Exception {
        String beanName = beanDefinition.getClassName();
        Class clz = ClassPathUtil.loadClass(beanName);
        if (clz == null) {
            throw new Exception("cannot find class by classname");
        }
        Object objBean = null;
        try {
            objBean = clz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("cannot find constructor with no fields");
        }

        List<ConstructorPO> construtorArgs = beanDefinition.getConstrutorPOS();
        List<PropertyPO> propertyPOS = beanDefinition.getPropertyPOS();
        if (construtorArgs != null && !construtorArgs.isEmpty()) {
            List<Object> objectList = new ArrayList<Object>();
            for (ConstructorPO constructorArg : construtorArgs) {
                objectList.add(getBean(constructorArg.getRef()));
            }
//            System.out.println(clz.getConstructor());
//            System.out.println(objectList.toArray());
            Object beanObj = BeanUtil.createObeject(clz, clz.getConstructor(), objectList.toArray());
            populateBean(beanObj);
            return beanObj;


        } else if (propertyPOS != null && !propertyPOS.isEmpty()) {
            for (PropertyPO po : propertyPOS) {
                String proName = po.getName();
                String proValue = po.getValue();
                String proRef = po.getRef();
                // 使用BeanUtils工具类完成属性注入,可以自动完成类型转换
                // 如果value不为null,说明有
                if (proValue != null) {
                    Map<String, String[]> parmMap = new HashMap<>();
                    parmMap.put(proName, new String[]{proValue});
                    try {
                        BeanUtils.populate(objBean, parmMap);
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new RuntimeException("please check your" + proName + "field");
                    }
                }
                else if (proRef != null) {

                    // 看看当前IOC容器中是否已存在该bean,有的话直接设置没有的话使用递归,创建该bean对象
                    Object existBean = beanMap.get(po.getRef());
                    if (existBean == null) {
                        // 递归的创建一个bean
                        existBean = createBean(beanDefinitionMap.get(po.getRef()));
                        // 放置到beanMap容器中
                        // 只有当scope="singleton"时才往容器中放
                        if (beanDefinitionMap.get(po.getRef()).getScope().equals("singleton")) {
                            beanMap.put(po.getRef(), existBean);
                        }
                    }
                    try {
                        //setMethod.invoke(beanObj, existBean);
                        //通过BeanUtils为objBean设置属性
                        BeanUtils.setProperty(objBean, proName, existBean);
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new RuntimeException("Your bean object" + proName
                                + "didnot has any setter");
                    }
                }
            }
            return objBean;
        }
                Object beanObj=BeanUtil.createObeject(clz, null, null);
                populateBean(beanObj);
                return beanObj;
                //            Object objBean = BeanUtil.createObeject(clz,null,null);
                //            beanMap.put(beanName,objBean);
                //            return objBean;

        }



        //填充bean属性
        // 对该Bean的Field一一进行填充, 如果有依赖其它Bean的也一并完成依赖注入
        protected void populateBean (Object bean) throws Exception
        {
            Field[] fields = bean.getClass().getSuperclass().getDeclaredFields();
            if (fields != null && fields.length > 0) {
                for (Field field : fields) {
                    String beanName = field.getName();
                    beanName = StringUtil.decapitalize(beanName);
                    if (beanNameSet.contains(beanName)) {
                        Object fieldBean = getBean(beanName);
                        if (fieldBean != null) {
                            InjectUtil.injectField(field, bean, fieldBean);
                        }
                    }

                }


            }


        }


    }

