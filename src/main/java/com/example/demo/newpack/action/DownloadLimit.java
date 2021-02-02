package com.example.demo.newpack.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

@Controller
public class DownloadLimit {
    @RequestMapping(value = "/hellos")
    public String helloPag(String path){
        return "hello";
    }
   @RequestMapping(value = "/showImg")
    public void showImg(HttpServletResponse response) throws Exception {
//读取本地图片输入流
        FileInputStream inStream = new FileInputStream("C:/Users/cy/Pictures/sdf.png");

//byte数组用于存放图片字节数据
        byte[] buff = new byte[inStream.available()];

        inStream.read(buff);
        inStream.close();

//设置发送到客户端的响应内容类型
        response.setContentType("image/*");

        OutputStream outStream = response.getOutputStream();
        outStream.write(buff);
        outStream.close();
    }

    @RequestMapping(value = "/toDownload")
    public void toDownload(HttpServletRequest request, final HttpServletResponse response) throws Exception {
        //得到该文件  由于在重写run方法使用该文件  所以需要加final 还有方法的形参加final同理
        final File file = new File("D:\\百分浏览器下载目录\\TongLiao.rar");
        if (!file.exists()) {
            System.out.println("Have no such file!");
            return;//文件不存在就退出方法
        }
        //使用线程池工厂创建连接池对象
        ExecutorService es = Executors.newFixedThreadPool(3);
        //获取正在运行的线程数量
        int threadCount = ((ThreadPoolExecutor) es).getActiveCount();
        //判断小于三时,方可进入下载程序
        if (threadCount < 3) {
            //线程池对象调用线程方法   new  Runnable是重写该线程接口的run方法
            es.submit(new Runnable() {
                //以下为下载程序的代码
                @Override
                public void run() {
                    String fileName = file.getName();
                    //声明输入流
                    FileInputStream fileInputStream = null;
                    OutputStream outputStream = null;
                    //以下的所有的try...catch为系统提醒,直接点击生成的
                    try {
                        fileInputStream = new FileInputStream(file);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    //设置Http响应头告诉浏览器下载这个附件
                    try {
                        response.setHeader("Content-Disposition", "attachment;Filename=" + URLEncoder.encode(fileName, "UTF-8"));
                    } catch (UnsupportedEncodingException e1) {
                        e1.printStackTrace();
                    }

                    try {
                        outputStream = response.getOutputStream();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    byte[] bytes = new byte[2048];
                    int len = 0;
                    try {
                        while ((len = fileInputStream.read(bytes)) > 0) {
                            outputStream.write(bytes, 0, len);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        //finally是自己添的   下边的try还是系统提示的
                        try {
                            fileInputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            outputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        } else {
            //这是if判断自己写的
            throw new Exception("线路正忙请稍等");
        }
    }
}
