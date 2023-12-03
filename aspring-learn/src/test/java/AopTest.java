import com.example.spring.learn.AopBean;
import com.example.spring.learn.SpringConfig;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author jack.wen
 * @since 2023/12/2 22:19
 */
public class AopTest {
    @Test
    public void testAopProxyBuild() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        AopBean aopBean = (AopBean) applicationContext.getBean("aopBean");
        aopBean.test();
    }
}
