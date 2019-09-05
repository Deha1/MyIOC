package springframework.lookup_method;

/**
 * @author Ocean
 * @date 2019/7/22 11:42
 */
public abstract class GetBeanTest {
    public void who()
    {
        this.getBean().who();
    }

    public abstract Player getBean();
}
