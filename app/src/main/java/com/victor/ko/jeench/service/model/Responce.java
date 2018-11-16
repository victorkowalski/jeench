package com.victor.ko.jeench.service.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "responce", strict = false)
public class Responce {

    @Element(name = "code")
    private String code;

    @Element(name = "message")
    public Message message;
}
