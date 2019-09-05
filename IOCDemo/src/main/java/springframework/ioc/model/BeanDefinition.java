package springframework.ioc.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ocean
 * @date 2019/7/27 18:28
 */
public class BeanDefinition {

    private String beanName;
    private String className;
    private String interfaceName;
    private String scope="singleton";
    private List<ConstructorPO> constructorPOS = new ArrayList<>();
    private List<PropertyPO> propertyPOS = new ArrayList<>();


    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public BeanDefinition(String beanName, String className, String interfaceName, List<ConstructorPO> construtorPOS, List<PropertyPO> propertyPOS) {
        this.beanName = beanName;
        this.className = className;
        this.interfaceName = interfaceName;
        this.constructorPOS = construtorPOS;
        this.propertyPOS = propertyPOS;
    }

    public BeanDefinition() {
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public List<ConstructorPO> getConstrutorPOS() {
        return constructorPOS;
    }

    public void setConstrutorPOS(List<ConstructorPO> construtorPOS) {
        this.constructorPOS = construtorPOS;
    }

    public List<PropertyPO> getPropertyPOS() {
        return propertyPOS;
    }

    public void setPropertyPOS(List<PropertyPO> propertyPOS) {
        this.propertyPOS = propertyPOS;
    }
}
