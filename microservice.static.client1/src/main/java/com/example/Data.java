package com.example;

public class Data {

    private String msg;

    public Data() {}

    public Data(String msg) {
        this.msg=msg;
    }

    public String getMsg() {
        return this.msg;
    }

    public Data setMsg(String msg) {
        this.msg = msg;
        return this;
    }
}
