package cc.mrbird.febs.others.controller;

import cc.mrbird.febs.common.utils.FebsUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liuxiaokang
 * @description
 * @date 2020/11/3
 */
@RestController
@RequestMapping("dansha")
public class TestController {
    @RequestMapping("testSync")
    public String testSync() throws InterruptedException {
        synchronized (TestController.class) {
            Thread.sleep(5000);
        }
        return "complate!";
    }
    
}
