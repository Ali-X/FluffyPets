package com.fluffypets.MVC.servlets;

import javax.servlet.http.Cookie;
import java.util.HashMap;
import java.util.Map;

public class ViewModel {

    private String view;
    private Map<String, Object> attributes = new HashMap<>();
    private Cookie cookie;

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

    public void setView(String view) {
        this.view = view;
    }

    public Cookie getCookie() {
        return cookie;
    }

    public void setCookie(Cookie cookie) {
        this.cookie = cookie;
    }

    public static ViewModel of() {
        return new ViewModel();
    }
}
