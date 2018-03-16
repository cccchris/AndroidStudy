package com.example.yh.wyproject.net.rxbus;

/**
 * Created by jingbin on 16/5/17.
 */
public class RxBusBaseMessage {
    private int code;
    private Object object;

    public RxBusBaseMessage(int code, Object object){
        this.code=code;
        this.object=object;
    }

    public RxBusBaseMessage(int code){
        this.code=code;
    }

    public RxBusBaseMessage(){}

    public int getCode() {
        return code;
    }

    public Object getObject() {
        return object;
    }
}
