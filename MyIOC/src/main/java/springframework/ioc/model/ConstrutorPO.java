package springframework.ioc.model;

/**
 * @author Ocean
 * @date 2019/7/20 18:19
 */
public class ConstrutorPO {

    private int index;
    private String ref;
    private String name;

    public ConstrutorPO() {
    }

    public ConstrutorPO(int index, String ref, String name) {
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
