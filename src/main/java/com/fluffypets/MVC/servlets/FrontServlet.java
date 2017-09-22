package com.fluffypets.MVC.servlets;

import com.fluffypets.MVC.controller.Controller;
import com.fluffypets.factory.Factory;
import exeptions.MVCexception;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FrontServlet extends HttpServlet implements AutoCloseable {
    private static final Logger logger = LogManager.getLogger(FrontServlet.class.getName());

    private Map<Request, Controller> controllerMap = new HashMap<>();

    public void init() {
//      pages GET links
        controllerMap.put(new Request("GET", "/root/home"), Factory.getHomeController());
        controllerMap.put(new Request("GET", "/root/login"), Factory.getLoginPageController());
        controllerMap.put(new Request("GET", "/root/logout"), Factory.getLogoutPageController());
        controllerMap.put(new Request("GET", "/root/profile"), Factory.getProfilePageController());
        controllerMap.put(new Request("GET", "/root/signup"), Factory.getRegistrationPageController());
        controllerMap.put(new Request("GET", "/root/forgot"), Factory.getForgotPassword());
        controllerMap.put(new Request("GET", "/root/admin/users"), Factory.getAdminPage());
        controllerMap.put(new Request("GET", "/root/admin/orders"), Factory.getAdminOrdersPage());
        controllerMap.put(new Request("GET", "/root/editProfile"), Factory.getEditUserProfileController());
        controllerMap.put(new Request("GET", "/root/admin/createProduct"), Factory.getProductController());
//      POST methods from pages
        controllerMap.put(new Request("POST", "/root/selectGoods"), Factory.getSelectGoodsController());
        controllerMap.put(new Request("POST", "/root/login"), Factory.getLoginCheckController());
        controllerMap.put(new Request("POST", "/root/admin/users"), Factory.getEditUserRole());
        controllerMap.put(new Request("POST", "/root/admin/orders"), Factory.getEditOrder());
        controllerMap.put(new Request("POST", "/root/signup"), Factory.getSignUpCheckController());
        controllerMap.put(new Request("POST", "/root/editProfile"), Factory.getUserDataController());
        controllerMap.put(new Request("POST", "/root/admin/createCategory"), Factory.getCreateCategoryController());
        controllerMap.put(new Request("POST", "/root/admin/createProduct"), Factory.getCreateProductController());
        controllerMap.put(new Request("POST", "/root/addToCart"), Factory.getAddProductToCartController());
        controllerMap.put(new Request("POST", "/root/takeFromCart"), Factory.getTakeProductFromCartController());
        controllerMap.put(new Request("POST", "/root/makeOder"), Factory.getMakeOrderController());
        controllerMap.put(new Request("POST", "/root/submitOder"), Factory.getSubmitOrderController());
        controllerMap.put(new Request("POST", "/root/forgot"), Factory.getSendForgotPassword());
        controllerMap.put(new Request("POST", "/root/upload"), Factory.getImageUploadController());
        controllerMap.put(new Request("POST", "/root/internationalization"), Factory.getInternationalizationController());
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest httpRequest, HttpServletResponse response) throws ServletException, IOException {
        processRequest(httpRequest, response);
    }

    private void processRequest(HttpServletRequest httpRequest, HttpServletResponse response) throws ServletException, IOException {

        Request request = new Request(httpRequest.getMethod(), httpRequest.getRequestURI(), httpRequest.getParameterMap());
        try {
            ViewModel vm = (ViewModel) httpRequest.getSession().getAttribute("vm");
            if (vm == null) vm = new ViewModel();

            Controller controller = controllerMap.get(request);
            if (controller == null) {
                logger.error("Can't handle " + request.getUri());
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                throw new MVCexception("Can't handle " + request.getUri());
            }

            if (ServletFileUpload.isMultipartContent(httpRequest)) {
                try {
                    request.setItemsForUpload(new ServletFileUpload(new DiskFileItemFactory()).parseRequest(httpRequest));
                } catch (FileUploadException e) {
                    logger.error("error with getting multipart content" + e);
                    response.sendError(HttpServletResponse.SC_PARTIAL_CONTENT);
                    throw new MVCexception("error with getting multipart content " + request.getUri());
                }
            }
            try {
                vm = controller.process(request, vm);
            } catch (RuntimeException e) {
                logger.error("error in controller " + e);
                throw new MVCexception("error in controller " + e);
            }

            if (vm.hasCookie("FluffyPets")) {
                Map<String, String> newCookie = vm.getCookie();
                newCookie.keySet().forEach(c -> {
                    Cookie cookie = new Cookie(c, newCookie.get(c));
                    cookie.setMaxAge(3600 * 24 * 1);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                });
            }

            forward(httpRequest, response, vm);
        } catch (Throwable e) {
            logger.error("Error in request handeling" + e);
        }
    }

    synchronized private void forward(HttpServletRequest request, HttpServletResponse resp, ViewModel vm) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(getView(vm));
        request.getSession().setAttribute("vm", vm);
        setAttributes(request, vm);
        dispatcher.forward(request, resp);
    }

    synchronized private void setAttributes(HttpServletRequest request, ViewModel vm) {
        vm.getAttributes().keySet().forEach(key -> request.setAttribute(key, vm.getAttribute(key)));
    }

    synchronized private String getView(ViewModel vm) {
        String prefix = "/WEB-INF/views/";
        String suffix = ".jsp";
        return prefix + vm.getView() + suffix;
    }

    @Override
    public void close() throws Exception {
        controllerMap.forEach((key, value) -> {
            try {
                value.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        controllerMap = null;
    }
}
