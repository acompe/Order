package com.acompe.vo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.HashMap;
import java.util.Map;

public class Message {

    Map map;

    public Message(){
        this.map = new HashMap();
    }

    public void setTrue(){
        this.map.put("status","success");
    }

    public void setTrue(Object obj){
        this.setTrue();
        this.map.put("value",obj);
    }

    public void setFalse(){
        this.map.put("status","error");
    }

    public void setFalse(String message){
        this.setFalse();
        this.map.put("message",message);
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this.map, SerializerFeature.WriteMapNullValue);
    }
}