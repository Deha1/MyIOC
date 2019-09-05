//package springframework.ioc.factory;
//
//
//import net.sf.cglib.beans.BeanMap;
//import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//import org.w3c.dom.Node;
//import org.w3c.dom.NodeList;
//
//import springframework.ioc.model.BeanDefinition;
//import springframework.ioc.model.PropertyPO;
//import springframework.ioc.util.ClassPathUtil;
//import springframework.ioc.util.InjectUtil;
//
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.parsers.ParserConfigurationException;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.lang.reflect.Field;
//import java.util.List;
//
//
///**
// * @author Ocean
// * @date 2019/7/21 20:14
// */
//
//    //解析xml资源文件，转换成resource，再解析为document对象
//    //将<bean>标签的内容转换成beanDefinition
//public class OldXmlBeanFactory extends DefaultListableBeanFactory {
//
//    private String fileName;
//
//    public OldXmlBeanFactory(String fileName) {
//        this.fileName = fileName;
//    }
//
//    public void init() throws Exception {
//        loadFile(fileName);
//    }
//
//    //读取文件并解析bean标签
//    private void loadFile(String path) throws Exception {
//        InputStream inputStream = new ClassPathUtil().getDefaultClassLoader().getResourceAsStream(path);
//        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//        DocumentBuilder builder = factory.newDocumentBuilder();
//        Document document = builder.parse(inputStream);
//        Element root = document.getDocumentElement();
////        String s = root.getTagName();
////        System.out.println(s);
//        NodeList nodes = root.getChildNodes();
//        BeanDefinition beanDefinition = new BeanDefinition();
//
//        for (int i = 0; i < nodes.getLength(); i++) {
//            Node node = nodes.item(i);
//            if (node instanceof Element) {
//                Element ele = (Element) node;
//                String id = ele.getAttribute("id");
//                String className = ele.getAttribute("class");
//                String scope = ele.getAttribute("scope");
//                beanDefinition.setBeanName(id);
//                beanDefinition.setClassName(className);
//                if(scope!=null)
//                {
//                    beanDefinition.setScope(scope);
//                }
//
//                //创建bean
//                Class beanClass = null;
//                try {
//                    beanClass = Class.forName(className);
//                } catch (ClassNotFoundException e) {
//                    e.printStackTrace();
//                    return;
//                }
//
//                String interfaceName = beanClass.getSuperclass().getName();
//                beanDefinition.setInterfaceName(interfaceName);
//
//                //创建Bean，遍历<property>
//                //得到的是bean节点列表
//                NodeList beanList = ele.getChildNodes();
//                PropertyPO pro = new PropertyPO();
//                for (int j = 0; j < beanList.getLength(); j++) {
//                    Node beanNode = nodes.item(i);
//                    Element beanEle = (Element) beanNode;
//                    //得到了property节点列表
//                    NodeList propertyList = beanEle.getChildNodes();
//                    for (int k = 0; k < propertyList.getLength(); k++)
//                    {
//                        Node propertyNode = propertyList.item(i);
//                        Element proEle = (Element)propertyNode;
//                        String propertyName = proEle.getAttribute("name");
//                        String propertyRef = proEle.getAttribute("ref");
//                        String propertyValue = proEle.getAttribute("value");
//                        if (propertyName == null) {
//                            throw new Exception("property must has name!!!");
//                        }
//                        //为BeanDefinition填充property
//                        pro.setName(propertyName);
//                        if(propertyRef!=null)
//                        {
//                            pro.setRef(propertyRef);
//                        }
//                        if(propertyValue!=null)
//                        {
//                            pro.setValue(propertyValue);
//                        }
//
//                        beanDefinition.getPropertyPOS().add(pro);
////                        List<PropertyPO> pros = beanDefinition.getPropertyPOS();
////                        for(PropertyPO p: pros)
////                        {
////                            System.out.println(p.getName());
////                        }
////                    Field field = beanClass.getDeclaredField("propertyPOS");
////                    InjectUtil.injectField(field, beanClass, pro);
////
////                    beanMap.put(className,beanClass);
//
//                    }
//
//                }
//                registerBean(id,beanDefinition);
//
//            }
//
//        }
//    }
//}
