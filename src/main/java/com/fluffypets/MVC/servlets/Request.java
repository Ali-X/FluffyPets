package com.fluffypets.MVC.servlets;

import java.util.HashMap;
import java.util.Map;

public class Request {


    private final String method;
    private final String uri;
    private final Map<String, String> parameters = new HashMap<>();

    public Request(Map<String, String> parameters, String method, String uri) {
        this.method = method.toUpperCase();
        this.uri = uri;
        if (parameters != null) {
            for (String param : parameters.keySet()) {
                Object value = parameters.get(param);

                if (value != null) {
                    if (value.getClass() == String.class) {
                        this.parameters.put(param, (String)value);
                    } else if(value.getClass() == String[].class) {
                        String[] valueArray = (String[]) value;
                        for (int i=0;i<valueArray.length;i++)
                        this.parameters.put(param, valueArray[i]+" "+i);
                    }
                }
            }
        }
    }

    public Request(String method, String uri) {
        this.method = method;
        this.uri = uri;
    }

    public String getMethod() {
        return method;
    }

    public String getUri() {
        return uri;
    }

    public static Request create(String method, String url) {
        return new Request(null, method, url);
    }

    public String getParameter(String param) {
        return parameters.get(param);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Request request = (Request) o;

        if (!method.equals(request.method)) return false;
        if (!uri.equals(request.uri)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = method.hashCode();
        result = 31 * result + uri.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return this.method + " " + this.uri;
    }
}
