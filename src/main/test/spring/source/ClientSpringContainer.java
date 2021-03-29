package spring.source;

import cc.mrbird.febs.others.entity.A;
import cc.mrbird.febs.others.entity.B;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 循环依赖断点验证
 * @author liuxiaokang
 * @date 2020/11/30
 */
public class ClientSpringContainer {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        A a = context.getBean("a", A.class);
        B b = context.getBean("b", B.class);
        
    }
}
