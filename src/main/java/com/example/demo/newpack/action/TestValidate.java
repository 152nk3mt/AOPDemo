package com.example.demo.newpack.action;

import com.example.demo.newpack.service.TestNotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName TestValidate
 * @Author T480
 * @Date 2020/6/3 11:03
 * @Description TODO
 * @Version 1.0
 **/
@RestController
public class TestValidate {

    @Autowired
    private TestNotNull testNotNull;

    @GetMapping("notnullTest")
    public String getNotNull(String obj1, String obj2){
       return testNotNull.testNotNullAntion(obj1, obj2);
    }
}
