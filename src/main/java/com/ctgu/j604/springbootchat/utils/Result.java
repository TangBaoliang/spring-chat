package com.ctgu.j604.springbootchat.utils;

import java.util.Date;

public class Result {
    private boolean flag;
    private String message;
    public static final String WRONG_ACCOUNT_OR_PASSWORD = "用户名或密码错误";


    public Result(){

    }
    public Result(boolean flag, String message) {
        this.flag = flag;
        this.message = message;
    }

    public boolean isFlag(){
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
