package com.fluffypets.MVC.filtres;

import com.fluffypets.DAO.user.UserDAOImpl;
import com.fluffypets.MVC.model.User;
import com.fluffypets.factory.Factory;
import exeptions.MVCexception;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserFilter implements Filter {
    private static final Logger logger = LogManager.getLogger(AdminFilter.class.getName());


    private UserDAOImpl userDao;
    private List<String> protectedUrl = new ArrayList<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        userDao = Factory.getUserDao();
        protectedUrl.add("/root/profile");
        protectedUrl.add("/root/makeOder");
        protectedUrl.add("/root/submitOder");
        protectedUrl.add("/root/editProfile");
        protectedUrl.add("/root/selectGoods");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        Cookie[] cookies = httpRequest.getCookies();
        String uri = httpRequest.getRequestURI();

        User user= (User) httpRequest.getAttribute("user");

        if (protectedUrl.contains(uri)) {
            String token = null;
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                String TOKEN = "token";
                if (TOKEN.equals(name)) {
                    token = cookie.getValue();
                    user = userDao.findByToken(token);
                    request.setAttribute("user", user);
                }
            }
//            user= (User) request.getAttribute("user");
            if (user==null||user.getRoleString().equals("blocked")) {
//                request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
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
