package springframework.ioc.model;

/**
 * @author Ocean
 * @date 2019/7/27 18:19
 */
public class ConstructorPO {

    private int index;
    private String ref;
    private String name;

    public ConstructorPO() {
    }

    public ConstructorPO(int index, String ref, String name) {
        this.index = index;
        this.ref = ref;
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public String getRef() {
        return ref;
    }

    public String getName() {
        return name;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public void setName(String name) {
        this.name = name;
    }
}
