package com.fluffypets.MVC.servlets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.Cookie;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ViewModel {
    private static final Logger logger = LogManager.getLogger(ViewModel.class.getName());


    private String view;
    private Map<String, Object> attributes = new HashMap<>();
    private final Map<String, String> cookieS = new HashMap<>();
    private Locale currentLocale= new Locale("en","US");

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

    public Locale getCurrentLocale() {
        return currentLocale;
    }

    public void setCurrentLocale(Locale currentLocale) {
        this.currentLocale = currentLocale;
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

    public static String stringUTF8(String string){
        try {
            return new String( string.getBytes("ISO-8859-1"),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
