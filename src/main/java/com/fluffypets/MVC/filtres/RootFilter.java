package com.fluffypets.MVC.filtres;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class RootFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)  throws IOException, ServletException {
        String root="/root";
        HttpServletRequest req = (HttpServletRequest) request;
        if (req.getRequestURI().startsWith(root) || req.getRequestURI().contains(".")) {
            chain.doFilter(req, response);
        } else {
            if (request.getAttribute("uri") != null) {
                req.getRequestDispatcher(root+ request.getAttribute("uri")).forward(request, response);
            } else {
                req.getRequestDispatcher(root + req.getRequestURI()).forward(request, response);
            }
        }
    }

    @Override
    public void destroy() {

    }
}