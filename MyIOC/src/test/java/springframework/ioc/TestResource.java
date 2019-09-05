//package springframework.ioc;
//
//
//import org.junit.Test;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.core.io.DefaultResourceLoader;
//import org.springframework.core.io.Resource;
//
//
//import java.io.IOException;
//
///**
// * @author Ocean
// * @date 2019/7/23 10:12
// */
//public class TestResource {
//
//    @Test
//    public void test1() {
//        // 从资源文件夹下加载
//        Resource resource =  new ClassPathResource("lib/testfile.xml");
//        print(resource);
//
//    }
//
//    @Test
//    public void test2() {
//        // 使用类信息加载
//        Resource resource = new ClassPathResource("testfile.xml",TestResource.class);
//        print(resource);
//
//    }
//
//    @Test
//    public void test3() {
//        // 使用类加载器从资源文件夹下加载
//        Resource resource = new ClassPathResource("lib/testfile.xml", TestResource.class.getClassLoader());
//        print(resource);
//    }
//
//    @Test
//    public void test4() {
//        // 使用DefaultResourceLoader加载
//        Resource resource = new DefaultResourceLoader().getResource("lib/testfile.xml");
//        print(resource);
//    }
//
//    // 打印资源文件内容
//    public void print(Resource resource) {
//        byte[] read = new byte[10000];
//        try {
//            resource.getInputStream().read(read, 0, read.length);
//            System.out.println(new String(read));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//}
