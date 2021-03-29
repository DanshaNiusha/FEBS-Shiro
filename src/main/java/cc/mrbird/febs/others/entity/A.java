package cc.mrbird.febs.others.entity;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author liuxiaokang
 * @description
 * @date 2020/11/30
 */
@Slf4j
public class A {
    private B b;
    
    public B getB() {
        return b;
    }

    public void setB(B b) {
        log.debug("----> A 创建成功");
        this.b = b;
    }
}
