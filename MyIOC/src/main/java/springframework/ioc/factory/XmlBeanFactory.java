package springframework.ioc.factory;

import java.io.InputStream;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.XPath;
import org.dom4j.io.SAXReader;

import springframework.ioc.model.BeanDefinition;
import springframework.ioc.model.PropertyPO;
import springframework.ioc.util.ClassPathUtil;

/**
 * @author Ocean
 * @date 2019/7/21 21:58
 */
//解析xml资源文件，转换成resource，再解析为document对象
//将<bean>标签的内容转换成beanDefinition
public class XmlBeanFactory extends DefaultListableBeanFactory{
    private String fileName;

    public XmlBeanFactory(String fileName) {
        this.fileName = fileName;
    }

    public void init() throws Exception {
        loadFile(fileName);
    }


    private void loadFile(String path) throws Exception {
        //加载配置文件,得到document对象
        InputStream is = new ClassPathUtil().getDefaultClassLoader().getResourceAsStream(path);
        SAXReader reader=new SAXReader();
        Document document =null;
        try {
            document = reader.read(is);
        } catch (DocumentException e) {
            e.printStackTrace();
            throw new RuntimeException("请检查xml配置是否正确");
        }
        // 定义xpath表达式,取出所有Bean元素
        String xpath="//bean";
//        XPath xpath = XPath.newInstance("//bean");

        //对Bean元素继续遍历
        List<Element> nodeList = document.selectNodes(xpath);
        if(nodeList!=null){
            //将Bean元素的name/class属性封装到bean类属性中
            for (Element beanEle : nodeList) {
                BeanDefinition beanDefinition=new BeanDefinition();
                String beanName=beanEle.attributeValue("id");
                String className=beanEle.attributeValue("class");
                String scope=beanEle.attributeValue("scope");
                beanDefinition.setBeanName(beanName);
                beanDefinition.setClassName(className);
                if(scope!=null){
                    beanDefinition.setScope(scope);
                }

                //  获得bean下的所有property子元素
                List<Element> propertyList = beanEle.elements("property");
                // 将属性name/value/ref分装到类Property类中
                if(propertyList!=null){
                    for (Element proEle : propertyList) {
                        PropertyPO prop=new PropertyPO();
                        String pName=proEle.attributeValue("name");
                        String pValue=proEle.attributeValue("value");
                        String pRef=proEle.attributeValue("ref");
                        prop.setName(pName);
                        prop.setRef(pRef);
                        prop.setValue(pValue);
                        // 5.将property对象封装到bean对象中
                        beanDefinition.getPropertyPOS().add(prop);
                    }
                }
                //注册beanDefinition，把对象放入beanDefinitionMap中
                registerBean(beanName,beanDefinition);
            }
        }

    }


}
