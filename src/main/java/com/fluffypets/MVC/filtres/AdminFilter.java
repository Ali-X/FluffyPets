package com.fluffypets.MVC.filtres;

import com.fluffypets.DAO.user.UserDAO;
import com.fluffypets.MVC.model.User;
import com.fluffypets.MVC.servlets.ViewModel;
import com.fluffypets.factory.Factory;
import exeptions.MVCexception;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AdminFilter implements Filter {
    private static final Logger logger = LogManager.getLogger(AdminFilter.class.getName());

    private UserDAO userDAO;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        userDAO = Factory.getUserDao();
    }

    @Override
    public void doFilter(ServletRequest httpRequest, ServletResponse httpResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) httpRequest;
        Cookie[] cookies = httpServletRequest.getCookies();
        String uri = httpServletRequest.getRequestURI();

        User user = (User) httpRequest.getAttribute("user");

        String token = null;
        for (Cookie cookie : cookies) {
            String name = cookie.getName();
            String TOKEN = "FluffyPets";
            if (TOKEN.equals(name)) {
                token = cookie.getValue();
                user = userDAO.findByToken(token);
                httpRequest.setAttribute("user", user);
                cookie = new Cookie("FluffyPets", null);
            }
        }
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
