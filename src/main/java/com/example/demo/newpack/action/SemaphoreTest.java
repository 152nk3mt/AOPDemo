package com.example.demo.newpack.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.concurrent.Semaphore;

/**
 * @ClassName SemaphoreTest
 * @Author cy
 * @Date 2020/8/13 11:30
 * @Description TODO
 * @Version 1.0
 **/
@Controller
public class SemaphoreTest {
    private final static Semaphore sp = new Semaphore(3);

    @RequestMapping(value = "/downTest")
    public void downloadTest(HttpServletResponse resp){
        if (!sp.tryAcquire()){
            System.out.println("线程已满！");
            return;
        }
        try {
            doDownload(resp,"D:\\百分浏览器下载目录\\TongLiao.rar");
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            sp.release();
        }
        //DownloadThreadClass downloadThreadClass = new DownloadThreadClass("D:\\百分浏览器下载目录\\TongLiao.rar", resp, sp);
        // downloadThreadClass.start();
    }
    protected void doDownload(HttpServletResponse resp, String downLoadPath) throws ServletException, IOException {
        //得到要下载的文件
        File file = new File(downLoadPath);
        //如果文件不存在
        if(!file.exists()){
            System.out.println("下载文件：-----------您要下载的资源已被删除！");
            return;
        }
        try {
            //处理文件名
            String realname = file.getName();
            //设置响应头，控制浏览器下载该文件
            resp.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(realname, "UTF-8"));
            //读取要下载的文件，保存到文件输入流
            FileInputStream in = new FileInputStream(file);
            //创建输出流
            OutputStream out = resp.getOutputStream();
            //创建缓冲区
            byte buffer[] = new byte[1024];
            int len = 0;
            //循环将输入流中的内容读取到缓冲区当中
            while((len=in.read(buffer))>0){
                //输出缓冲区的内容到浏览器，实现文件下载
                Thread.sleep(1000);
                out.write(buffer, 0, len);
            }
            //关闭文件输入流
            in.close();
            //关闭输出流
            out.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}
