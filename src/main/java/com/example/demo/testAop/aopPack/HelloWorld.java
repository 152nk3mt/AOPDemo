package com.example.demo.testAop.aopPack;

import com.example.demo.testAop.newpack.RtnBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {
    @RequestMapping("hello")
    public RtnBean hello(int hello) {
        RtnBean rtnBean = new RtnBean();
        rtnBean.setCode(hello);
        rtnBean.setMsg("hello");
        return rtnBean;
    }

}
