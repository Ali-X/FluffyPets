package com.fluffypets.filtres;

import com.fluffypets.entities.User;
import com.fluffypets.mvc.page_objects.FieldStatus;
import com.fluffypets.mvc.page_objects.ValidationMessage;
import com.fluffypets.mvc.servlets.ViewModel;
import com.fluffypets.services.UserService;
import com.fluffypets.services.impl.ServicesFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.rmi.AccessException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserFilter implements Filter {
    private static final Logger logger = LogManager.getLogger(UserFilter.class.getName());

    private Set<String> allowedPages = new HashSet<>();
    private UserService userService = ServicesFactory.getUserService();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        allowedPages.add("/root/home");
        allowedPages.add("/root/login");
        allowedPages.add("/root/signup");
        allowedPages.add("/root/forgot");
        allowedPages.add("/root/recoverPassword");
        allowedPages.add("/root/selectGoods");
        allowedPages.add("/root/addToCart");
        allowedPages.add("/root/takeFromCart");
        allowedPages.add("/root/internationalization");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        ViewModel viewModel = (ViewModel) httpRequest.getSession().getAttribute("vm");
        String uri = httpRequest.getRequestURI();
        User user = null;
        if (viewModel != null) user = (User) viewModel.getAttribute("user");
        if (!allowedPages.contains(uri)) {
            if (user != null && user.getUserName().equals("Unknown")) {
                viewModel.setView("login");
                List<FieldStatus> fieldStatuses=new ArrayList<>();
                fieldStatuses.add(new FieldStatus(true, "username"));
                fieldStatuses.add(new FieldStatus(true, "password"));
                ValidationMessage<User> validationMessage=new ValidationMessage<>(user,"Please login",fieldStatuses);
                viewModel.setAttribute("validationUser", validationMessage);
            }else {
                if (user != null) user = userService.getUser(user);
                if (user == null || user.getRoleString().equals("blocked")) {
                    logger.error("Illegal access, RequestedSessionId: " + httpRequest.getRequestedSessionId());
                    throw new AccessException("forbidden url");
                }
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
