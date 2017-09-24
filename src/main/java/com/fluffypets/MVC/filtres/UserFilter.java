package com.fluffypets.MVC.filtres;

import com.fluffypets.DAO.user.UserDAO;
import com.fluffypets.MVC.model.User;
import com.fluffypets.MVC.servlets.ViewModel;
import com.fluffypets.factory.DaoFactory;
import exeptions.MVCexception;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class UserFilter implements Filter {
    private static final Logger logger = LogManager.getLogger(UserFilter.class.getName());

    private UserDAO userDao;
    private Set<String> allowedPages = new HashSet<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        userDao = DaoFactory.getUserDao();
        allowedPages.add("/root/home");
        allowedPages.add("/root/login");
        allowedPages.add("/root/signup");
        allowedPages.add("/root/forgot");
        allowedPages.add("/root/recoverPassword");
        allowedPages.add("/root/selectGoods");
        allowedPages.add("/root/addToCart");
        allowedPages.add("/root/takeFromCart");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        ViewModel viewModel = (ViewModel) httpRequest.getSession().getAttribute("vm");
        String uri = httpRequest.getRequestURI();
        User user = null;
        if (viewModel != null) user = (User) viewModel.getAttribute("user");

        if (!allowedPages.contains(uri)) {
            if (user == null || user.getRoleString().equals("blocked") || user.getRoleString().equals("Unknown")) {
                logger.error("Illegal access, RequestedSessionId: " + httpRequest.getRequestedSessionId());
                throw new MVCexception("Illegal access ");
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        try {
            userDao.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
