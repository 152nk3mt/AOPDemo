package com.example.demo.testAop.newpack;


public class RtnBean {
    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "RtnBean{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
