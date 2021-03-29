package cc.mrbird.febs.others.entity;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author liuxiaokang
 * @description
 * @date 2020/11/30
 */
@Slf4j
public class B {
    private A a;
    
    public A getA() {
        return a;
    }
    
    public void setA(A a) {
        log.debug("----> B 创建成功");
        this.a = a;
    }
}
