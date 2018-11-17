package com.victor.ko.jeench.service.model;

import java.util.List;

public class Responce {

    private String code;

    private List<Shop> message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Shop> getMessage() {
        return message;
    }

    public void setMessage(List<Shop> message) {
        this.message = message;
    }
}
