package com.fluffypets.MVC.servlets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.Cookie;
import java.util.HashMap;
import java.util.Map;

public class ViewModel {
    private static final Logger logger = LogManager.getLogger(ViewModel.class.getName());


    private String view;
    private Map<String, Object> attributes = new HashMap<>();
    private final Map<String, String> cookieS = new HashMap<>();

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttribute(String attrName, Object value) {
        attributes.put(attrName, value);
    }

    public Object getAttribute(String attrName) {
        Object value = attributes.get(attrName);
        return value;
    }

    public String getView() {
        return view;
    }

    public void removeAttribute(String attribute){
        attributes.remove(attribute);
    }

    public void removeCookie(String cookie){
        cookieS.remove(cookie);
    }

    public void setView(String view) {
        this.view = view;
    }

    public ViewModel addCookie(String name, String value) {
        cookieS.put(name, value);
        return this;
    }

    public boolean hasCookie(String theCookie) {
        return cookieS.containsKey(theCookie);
    }

    public Map<String, String> getCookie() {
        return cookieS;
    }

    public static ViewModel of() {
        return new ViewModel();
    }
}
