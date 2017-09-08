package com.fluffypets.MVC.servlets;

import com.fluffypets.MVC.controller.Controller;
import com.fluffypets.factory.Factory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FrontServlet extends HttpServlet {

    private Map<Request, Controller> controllerMap = new HashMap<>();
    private static final int MAX_MEMORY_SIZE = 1024 * 1024 * 2;
    private static final int MAX_REQUEST_SIZE = 1024 * 1024;

    public void init() {
//      pages GET links
        controllerMap.put(new Request("GET", "/root/home"), Factory.getHomeController());
        controllerMap.put(new Request("GET", "/root/login"), Factory.getLoginPageController());
        controllerMap.put(new Request("GET", "/root/logout"), Factory.getLogoutPageController());
        controllerMap.put(new Request("GET", "/root/profile"), Factory.getProfilePageController());
        controllerMap.put(new Request("GET", "/root/signup"), Factory.getRegistrationPageController());
        controllerMap.put(new Request("GET", "/root/forgot"), Factory.getForgotPassword());
        controllerMap.put(new Request("GET", "/root/editProfile"), Factory.getEditUserProfileController());
        controllerMap.put(new Request("GET", "/root/createProduct"), Factory.getProductController());
//      POST methods from pages
        controllerMap.put(new Request("POST", "/root/selectGoods"), Factory.getSelectGoodsController());
        controllerMap.put(new Request("POST", "/root/login"), Factory.getLoginCheckController());
        controllerMap.put(new Request("POST", "/root/signup"), Factory.getSignUpCheckController());
        controllerMap.put(new Request("POST", "/root/editProfile"), Factory.getUserDataController());
        controllerMap.put(new Request("POST", "/root/createCategory"), Factory.getCreateCategoryController());
        controllerMap.put(new Request("POST", "/root/createProduct"), Factory.getCreateProductController());
        controllerMap.put(new Request("POST", "/root/forgot"), Factory.getSendForgotPassword());
        controllerMap.put(new Request("POST", "/root/createProduct/imageupload"), Factory.getImageUploadController());
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Request request1 = new Request(request.getMethod(), request.getRequestURI(),request.getParameterMap());
//        request1.setAttribute("file-upload", new String[]{getServletContext().getInitParameter("file-upload")});

        try {
            Controller controller = controllerMap.get(request1);
            if (controller == null) {
                throw new RuntimeException("Can't handle " + request1.getUri());
            }

//            if (ServletFileUpload.isMultipartContent(request)) {
//                DiskFileItemFactory factory = new DiskFileItemFactory();
//                factory.setSizeThreshold(MAX_MEMORY_SIZE);
//                factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
//                ServletFileUpload upload = new ServletFileUpload(factory);
//                upload.setSizeMax(MAX_REQUEST_SIZE);
//                request1.setItemsForUpload(upload.parseRequest(request));
//            }

            ViewModel vm = controller.process(request1);
            if (vm.hasCookie("token")) {
                Map<String, String> newCookie = vm.getCookie();
                newCookie.keySet().forEach(c -> {
                    Cookie cookie = new Cookie(c, newCookie.get(c));
                    cookie.setMaxAge(3600 * 24 * 30);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                });
            }
            setAttributes(request, vm);
            forward(request, response, vm);
        } catch (Throwable e) {
            throw new RuntimeException("The error is " + e);
        }
    }

    private void forward(HttpServletRequest request, HttpServletResponse resp, ViewModel vm) throws ServletException, IOException {
        setAttributes(request, vm);
        RequestDispatcher dispatcher = request.getRequestDispatcher(getView(vm));
        dispatcher.forward(request, resp);
    }

    private void setAttributes(HttpServletRequest request, ViewModel vm) {
        vm.getAttributes().keySet().forEach(key -> {
            request.setAttribute(key, vm.getAttribute(key));
        });
    }

    private String getView(ViewModel vm) {

        String prefix = "/WEB-INF/views/";
        String suffix = ".jsp";

        return prefix + vm.getView() + suffix;

    }
}
