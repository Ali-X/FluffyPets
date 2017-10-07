package com.fluffypets.filtres;

import com.fluffypets.entities.User;
import com.fluffypets.mvc.servlets.ViewModel;
import com.fluffypets.services.UserService;
import com.fluffypets.services.impl.ServicesFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.rmi.AccessException;

public class AdminFilter implements Filter {
    private static final Logger logger = LogManager.getLogger(AdminFilter.class.getName());
    private UserService userService = ServicesFactory.getUserService();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest httpRequest, ServletResponse httpResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) httpRequest;
        ViewModel viewModel = (ViewModel) httpServletRequest.getSession().getAttribute("vm");

        User user = null;
        if (viewModel != null) user = (User) viewModel.getAttribute("user");
        if (user != null) user = userService.getUser(user);
        if (user == null || !user.getRoleString().equals("admin")) {
            if (user != null) {
                viewModel.setAttribute("user",user);
                logger.error("Illegal access, by: " + user.getUserName());
            }
            throw new AccessException("forbidden url");
        }

        chain.doFilter(httpRequest, httpResponse);
    }

    @Override
    public void destroy() {
    }

}
