package com.victor.ko.jeench.service.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "message", strict = false)
public class Message {

    @ElementList(inline = true)
    public List<Item> items;

}
