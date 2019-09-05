//package springframework.ioc;
//
//import org.junit.Test;
//import org.springframework.core.io.ClassPathResource;
//import org.w3c.dom.*;
//import org.xml.sax.SAXException;
//
//
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.parsers.ParserConfigurationException;
//import java.io.IOException;
//import java.io.InputStream;
//
///**
// * @author Ocean
// * @date 2019/7/21 11:55
// */
//public class TestDocument {
//
//    @Test
//    public void test1() throws ParserConfigurationException, IOException, SAXException {
//
//        //解析xml
//        //获取输入流
//        InputStream inputStream = new ClassPathResource("testfile.xml").getInputStream();
//
//        //一步步获取document对象
//        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
//
//        DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
//
//        Document document = documentBuilder.parse(inputStream);
//
//        Element element = document.getDocumentElement();
//
//        NodeList childNodes = element.getChildNodes();
//
//        //遍历节点列表
//        for (int i = 0; i < childNodes.getLength(); i++) {
//
//            Node node = childNodes.item(i);
//            NamedNodeMap attributes = node.getAttributes();
////          System.out.println(node.getNodeName());
//            if(attributes!=null)
//            {
//                System.out.println(attributes.getNamedItem("university"));
//                System.out.println(attributes.getNamedItem("name"));
//            }
//
//
//        }
//
//
//
//
//
//
//
//
//
//    }
//}
