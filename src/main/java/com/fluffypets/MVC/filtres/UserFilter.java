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
    private static final Logger logger = LogManager.getLogger(UserFilter.class.getName());


    private UserDAOImpl userDao;
    private List<String> protectedUrls = new ArrayList<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        userDao = Factory.getUserDao();
        protectedUrls.add("/root/profile");
        protectedUrls.add("/root/makeOder");
        protectedUrls.add("/root/submitOder");
        protectedUrls.add("/root/editProfile");
        protectedUrls.add("/root/selectGoods");
        protectedUrls.add("/root/admin/users");
        protectedUrls.add("/root/admin/orders");
        protectedUrls.add("/root/admin/createProduct");
        protectedUrls.add("/root/admin/createCategory");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        Cookie[] cookies = httpRequest.getCookies();
        String uri = httpRequest.getRequestURI();

        User user= (User) httpRequest.getAttribute("user");

        if (protectedUrls.contains(uri)) {
            String token = null;
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                String TOKEN = "FluffyPets";
                if (TOKEN.equals(name)) {
                    token = cookie.getValue();
                    user = userDao.findByToken(token);
                    request.setAttribute("user", user);
                }
            }
            if (user==null||user.getRoleString().equals("blocked")||user.getRoleString().equals("Unknown")) {
                logger.error("Illegal access, RequestedSessionId: "+httpRequest.getRequestedSessionId());
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
