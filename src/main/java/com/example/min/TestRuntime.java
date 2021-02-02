package com.example.min;

/**
 * @ClassName TestRuntime
 * @Author cy
 * @Date 2020/8/13 20:02
 * @Description TODO
 * @Version 1.0
 **/
public class TestRuntime {
    public static void main(String[] args) throws Exception {
        String cmd = "cmd /k start d:" ;
        Runtime.getRuntime().exec(cmd);

        Runtime.getRuntime().exec("D:\\apache-tomcat-7.0.57\\bin/startup.bat");

        Thread.sleep(1500);

        Runtime.getRuntime().exec("D:\\apache-tomcat-7.0.57\\bin/shutdown.bat");

    }
}
