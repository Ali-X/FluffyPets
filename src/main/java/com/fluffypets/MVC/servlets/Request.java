package com.fluffypets.MVC.servlets;

import java.util.HashMap;
import java.util.Map;

public class Request {


    private final String method;
    private final String url;
    private final Map<String, String> parameters = new HashMap<>();

    public Request(Map<String, String> parameters, String method, String url) {
        this.method = method.toUpperCase();
        this.url = url;
        if (parameters != null) {
            for (String param : parameters.keySet()) {
                Object value = parameters.get(param);

                if (value != null) {
                    if (value.getClass() == String.class) {
                        this.parameters.put(param, (String)value);
                    } else if(value.getClass() == String[].class) {
                        String[] valueArray = (String[]) value;
                        this.parameters.put(param, valueArray[0]);
                    }
                }
            }
        }
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
        if (!url.equals(request.url)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = method.hashCode();
        result = 31 * result + url.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return this.method + " " + this.url;
    }
}
