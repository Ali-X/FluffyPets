package com.fluffypets.mvc.servlets;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ViewModel implements Serializable {

    private String view;
    private Map<String, Object> attributes = new HashMap<>();
    private Locale currentLocale= new Locale("en","US");

    Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttribute(String attrName, Object value) {
        attributes.put(attrName, value);
    }

    public Object getAttribute(String attrName) {
        return attributes.get(attrName);
    }

    public Locale getCurrentLocale() {
        return currentLocale;
    }

    public Boolean isUkrLocale(){
        return this.currentLocale.equals(new Locale("uk", "UA"));
    }

    public void setCurrentLocale(Locale currentLocale) {
        this.currentLocale = currentLocale;
    }

    String getView() {
        return view;
    }

    public void removeAttribute(String attribute){
        attributes.remove(attribute);
    }

    public void setView(String view) {
        this.view = view;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
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
