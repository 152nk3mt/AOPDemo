package com.example.demo.newpack.service;
import javax.validation.constraints.NotNull;

public interface TestNotNull {
    public String testNotNullAntion(@NotNull(message = "obj1为空") String obj1, @NotNull(message = "obj1为空") String obj2);
}
