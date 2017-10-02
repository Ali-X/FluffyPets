package com.fluffypets.mvc.filtres;

import com.fluffypets.mvc.model.User;
import com.fluffypets.mvc.servlets.ViewModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class AdminFilter implements Filter {
    private static final Logger logger = LogManager.getLogger(AdminFilter.class.getName());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest httpRequest, ServletResponse httpResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) httpRequest;
        ViewModel viewModel = (ViewModel) httpServletRequest.getSession().getAttribute("vm");

        User user = null;
        if (viewModel != null) user = (User) viewModel.getAttribute("user");
        if (user == null || !user.getRoleString().equals("admin")) {
            if (user != null) {
                logger.error("Illegal access, by: " +user.getUserName() );
            }
            throw new RuntimeException("Illegal Access");
        }

        chain.doFilter(httpRequest, httpResponse);
    }

    @Override
    public void destroy() {
    }

}