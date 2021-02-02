package com.example.demo.newpack.service.impl;

import com.example.demo.newpack.service.TestNotNull;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

/**
 * @ClassName TestNotNullImpl
 * @Author T480
 * @Date 2020/6/3 11:01
 * @Description TODO
 * @Version 1.0
 **/
@Service
public class TestNotNullImpl implements TestNotNull {
    @Override
    public String testNotNullAntion(@NotNull(message = "obj1为空") String obj1, @NotNull(message = "obj1为空") String obj2) {
        return obj1 + obj2;
    }
}
