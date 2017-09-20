package com.fluffypets.MVC.servlets;

import org.apache.commons.fileupload.FileItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Request {
    private static final Logger logger = LogManager.getLogger(Request.class.getName());


    private final String method;
    private final String uri;
    private Map<String, String[]> attributes;
    private List<FileItem> itemsForUpload = new ArrayList<>();

    public Request(String requestMethod, String uri, Map<String, String[]> attributes) {
        this.uri = uri;
        this.method = requestMethod;
        this.attributes = attributes;
    }

    public Request(String requestMethod, String uri) {
        this.uri = uri;
        this.method = requestMethod;
    }

    public String getUri() {
        return uri;
    }

    public void removeAtribute(String attribute){
        attributes.remove(attribute);
    }

    public boolean containsAtribute(String attribute){return attributes.containsKey(attribute);}

    public List<FileItem> getItemsForUpload() {
        return itemsForUpload;
    }

    public void setItemsForUpload(List<FileItem> itemsForUpload) {
        this.itemsForUpload = itemsForUpload;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Request request = (Request) o;

        if (uri != null ? !uri.equals(request.uri) : request.uri != null) return false;
        return method != null ? method.equals(request.method) : request.method == null;
    }

    @Override
    public int hashCode() {
        int result = uri != null ? uri.hashCode() : 0;
        result = 31 * result + (method != null ? method.hashCode() : 0);
        return result;
    }

    public String getRequestMethod() {
        return method;
    }

    public void setAttribute(String name, String[] value){
        attributes.put(name, value);
    }

    public String getAttribute(String name){
        return attributes.get(name)[0];
    }

    public String[] getParametersArray(String param) {
        return attributes.get(param);
    }
}

