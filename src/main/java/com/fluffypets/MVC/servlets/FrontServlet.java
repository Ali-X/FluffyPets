package com.fluffypets.MVC.servlets;

import com.fluffypets.MVC.controller.*;
import com.fluffypets.factory.Factory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class FrontServlet extends HttpServlet {

    private Map<Request, Controller> controllerMap = new HashMap<>();

    public void init() {
        controllerMap.put(new Request("GET", "/root/home"), Factory.getHomeController());
        controllerMap.put(new Request("GET", "/root/login"), Factory.getLoginPageController());
        controllerMap.put(new Request("GET", "/root/profile"), Factory.getProfilePageController());
        controllerMap.put(new Request("GET", "/root/signup"), Factory.getRegistrationPageController());
        controllerMap.put(new Request("GET", "/root/forgot"), Factory.getForgotPassword());
        controllerMap.put(new Request("GET", "/root/editProfile"), Factory.getEditUserProfileController());
        controllerMap.put(new Request("GET", "/root/profile"), Factory.getUserProfileController());
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //  getAttributes(request);
        Request request1 = new Request(request.getParameterMap(),request.getMethod(), request.getRequestURI());
        try {
            Controller controller = controllerMap.get(request1);
            if (controller == null) {
                throw new RuntimeException("Can't handle " + request1.getUri());
            }
            ViewModel vm = controller.process(request1);
            Cookie cookie = vm.getCookie();
            if (cookie != null) {
                response.addCookie(cookie);
            }
            setAttributes(request, vm);
            forward(request, response, vm);
        } catch (Throwable e) {
            throw new RuntimeException("The error is " + e);
        }
    }

    private void forward(HttpServletRequest request, HttpServletResponse response, ViewModel vm) throws ServletException, IOException {

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(getView(vm));
        requestDispatcher.forward(request, response);

    }

    private String getView(ViewModel vm) {

        String prefix = "/WEB-INF/views/";
        String suffix = ".jsp";

        return prefix + vm.getView() + suffix;

    }

    private void getAttributes(HttpServletRequest request) {
        Enumeration<String> e = request.getParameterNames();
        while (e.hasMoreElements()) {
            String name = (String) e.nextElement();
            String[] value = request.getParameterValues(name);
            Factory.getViewModel().setAttribute(name, value[0]);
        }
    }

    private void setAttributes(HttpServletRequest request, ViewModel vm) {
        Map<String, Object> model = vm.getAttributes();
        for (Map.Entry<String, Object> entry : model.entrySet()) {
            request.setAttribute(entry.getKey(), entry.getValue());
        }
    }
}
