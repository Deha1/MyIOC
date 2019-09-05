package springframework.ioc.model;

/**
 * @author Ocean
 * @date 2019/7/20 18:27
 */
public class PropertyPO {

    private String name;
    private String ref;
    private String value;
//    private String typeClassName;

    public PropertyPO() { }

    public PropertyPO(String name, String ref, String value) {
        this.name = name;
        this.ref = ref;
        this.value = value;
//        this.typeClassName = typeClassName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }



//    public String getTypeClassName() {
//        return typeClassName;
//    }
//
//    public void setTypeClassName(String typeClassName) {
//        this.typeClassName = typeClassName;
//    }

}
