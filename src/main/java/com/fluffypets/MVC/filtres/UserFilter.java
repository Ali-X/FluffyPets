package com.fluffypets.MVC.filtres;

import com.fluffypets.DAO.user.UserDAO;
import com.fluffypets.DAO.user.UserDataDAO;
import com.fluffypets.MVC.model.User;
import com.fluffypets.MVC.model.UserData;
import com.fluffypets.MVC.servlets.ViewModel;
import com.fluffypets.factory.Factory;
import exeptions.MVCexception;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;

public class UserFilter implements Filter {
    private static final Logger logger = LogManager.getLogger(UserFilter.class.getName());

    private UserDAO userDao;
    private UserDataDAO userDataDAO;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        userDao = Factory.getUserDao();
        userDataDAO = Factory.getUserDataDao();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        Cookie[] cookies = httpRequest.getCookies();
        String uri = httpRequest.getRequestURI();
        String TOKEN = "FluffyPets";

        User user = (User) httpRequest.getAttribute("user");

            String token;
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                cookie.setMaxAge(0);
                if (TOKEN.equals(name)) {
                    token = cookie.getValue();
                    user = userDao.findByToken(token);
                    request.setAttribute("user", user);
                    UserData userData = userDataDAO.getByUserId(user.getId());
                    request.setAttribute("userData", userData);
                    cookie = new Cookie("FluffyPets", null);
                }
            }
            if (user == null || user.getRoleString().equals("blocked") || user.getRoleString().equals("Unknown")) {
                ViewModel viewModel = (ViewModel) httpRequest.getSession().getAttribute("vm");
                if (viewModel != null) {
                    viewModel.setAttributes(new HashMap<>());
                }
                logger.error("Illegal access, RequestedSessionId: " + httpRequest.getRequestedSessionId());
                throw new MVCexception("Illegal access ");
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
