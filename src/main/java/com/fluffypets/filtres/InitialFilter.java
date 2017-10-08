package com.fluffypets.filtres;

import com.fluffypets.mvc.servlets.ViewModel;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class InitialFilter implements Filter {

    private String encoding;

    @Override
    public void init(FilterConfig config) throws ServletException {
        encoding = config.getInitParameter("requestEncoding");
        if (encoding == null) encoding = "UTF-8";
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain next)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String uri = httpServletRequest.getRequestURI();

        ViewModel vm = (ViewModel) httpServletRequest.getSession().getAttribute("vm");
        if (vm == null) {
            vm = new ViewModel();
            httpServletRequest.getSession().setAttribute("vm", vm);
        }

        if (null == request.getCharacterEncoding()) {
            request.setCharacterEncoding(encoding);
        }

        if (!uri.startsWith("/resources") && !uri.startsWith("/root") && !uri.startsWith("/WEB-INF") && !uri.startsWith("/upload") && !uri.startsWith("/file")) {
            RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("/root/home");
            dispatcher.forward(httpServletRequest, response);
            return;
        }

        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        next.doFilter(httpServletRequest, response);
    }

    @Override
    public void destroy() {
    }
}