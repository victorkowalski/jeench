package com.victor.ko.jeench.service.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

//@Root(name = "responce", strict = false)
public class Responce {

    //@Element(name = "code")
    private String code;

    //@Element(name = "message")
    private List<Item> message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Item> getMessage() {
        return message;
    }

    public void setMessage(List<Item> message) {
        this.message = message;
    }
}
