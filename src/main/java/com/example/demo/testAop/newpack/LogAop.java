package com.example.demo.testAop.newpack;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @ClassName LogAop
 * @Author T480
 * @Date 2020/5/26 9:18
 * @Description TODO
 * @Version 1.0
 **/
@Aspect
@Component
public class LogAop {
    private static Logger logger = LoggerFactory.getLogger(LogAop.class);

    //execution(modifiers-pattern? ret-type-pattern declaring-type-pattern? name-pattern(param-pattern)throws-pattern?)
    //括号中各个pattern分别表示：
    //
    //修饰符匹配（modifier-pattern?）
    //返回值匹配（ret-type-pattern）可以为*表示任何返回值,全路径的类名等
    //类路径匹配（declaring-type-pattern?）
    //方法名匹配（name-pattern）可以指定方法名 或者 *代表所有, set* 代表以set开头的所有方法
    //参数匹配（(param-pattern)）可以指定具体的参数类型，多个参数间用“,”隔开，各个参数也可以用“*”来表示匹配任意类型的参数，如(String)表示匹配一个String参数的方法；(*,String) 表示匹配有两个参数的方法，第一个参数可以是任意类型，而第二个参数是String类型；可以用(..)表示零个或多个任意参数
    //异常类型匹配（throws-pattern?）
    //其中后面跟着“?”的是可选项
    
    @Pointcut("execution(public * com.example.demo.testAop.aopPack.*.*(..))")
    public void pointaopPack() {
    }
/*    @After("pointaopPack()")
    public void afterRunning(){
        System.out.println("方法执行完执行...afterRunning");


    }*/
/*
    @Around("pointaopPack()")
    @Order(1)
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {

        MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
        Method method = null;
        method = methodSignature.getMethod();
        //获取返回值类型
        Type t = method.getAnnotatedReturnType().getType();

        if (t.getTypeName().equals("com.example.demo.testAop.newpack.RtnBean")) {
            RtnBean proceed = (RtnBean) joinPoint.proceed();
            System.out.println(proceed.toString());
            int code = proceed.getCode();
            if (2 == code) {
                System.out.println("2");
                String msg = proceed.getMsg();
                logger.error("参数错误：" + msg);
            }
            if (1 == code) {
                System.out.println("1");
                String msg = proceed.getMsg();
                logger.error("参数正常：" + msg);
            }

        } else {
            joinPoint.proceed();
        }
    }*/

    @AfterReturning(returning = "rvt",pointcut="pointaopPack()")
    public Object AfterExec(JoinPoint joinPoint, Object rvt){
        Class<?> aClass = rvt.getClass();
        System.out.println(aClass);
        System.out.println(aClass.getName());
        System.out.println(aClass.getTypeName());
        if (aClass.getName().equals("com.example.demo.testAop.newpack.RtnBean")) {
            RtnBean proceed = (RtnBean) rvt;
            System.out.println(proceed.toString());
            int code = proceed.getCode();
            if (2 == code) {
                System.out.println("2");
                String msg = proceed.getMsg();
                logger.error("参数错误：" + msg);
            }
            if (1 == code) {
                System.out.println("1");
                String msg = proceed.getMsg();
                logger.error("参数正常：" + msg);
            }
        }
        return rvt;
    }
}
