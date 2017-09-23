package com.fluffypets.MVC.filtres;

import com.fluffypets.DAO.user.UserDAO;
import com.fluffypets.MVC.model.User;
import com.fluffypets.MVC.servlets.ViewModel;
import com.fluffypets.factory.DaoFactory;
import com.fluffypets.factory.Factory;
import exeptions.MVCexception;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class AdminFilter implements Filter {
    private static final Logger logger = LogManager.getLogger(AdminFilter.class.getName());

    private UserDAO userDAO;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        userDAO = DaoFactory.getUserDao();
    }

    @Override
    public void doFilter(ServletRequest httpRequest, ServletResponse httpResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) httpRequest;
        ViewModel viewModel = (ViewModel) httpServletRequest.getSession().getAttribute("vm");

        User user = null;
        if (viewModel != null) user = (User) viewModel.getAttribute("user");
        if (user == null || !user.getRoleString().equals("admin")) {
            logger.error("Illegal access, RequestedSessionId: " + httpServletRequest.getRequestedSessionId());
            throw new MVCexception("Illegal access ");
        }

        chain.doFilter(httpRequest, httpResponse);
    }

    @Override
    public void destroy() {
        try {
            userDAO.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
