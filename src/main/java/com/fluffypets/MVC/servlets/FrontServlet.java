package com.fluffypets.MVC.servlets;

import com.fluffypets.MVC.controller.Controller;
import com.fluffypets.factory.ControllersFactory;
import com.fluffypets.factory.ContextFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FrontServlet extends HttpServlet implements AutoCloseable {
    private static final Logger logger = LogManager.getLogger(FrontServlet.class.getName());

    private Map<Request, Controller> controllerMap = new HashMap<>();

    @Override
    public void init() {
//      pages GET links
        controllerMap.put(new Request("GET", "/root/home"), ControllersFactory.getHomeController());
        controllerMap.put(new Request("GET", "/root/login"), ControllersFactory.getLoginPageController());
        controllerMap.put(new Request("GET", "/root/logout"), ControllersFactory.getLogoutPageController());
        controllerMap.put(new Request("GET", "/root/profile"), ControllersFactory.getProfilePageController());
        controllerMap.put(new Request("GET", "/root/signup"), ControllersFactory.getRegistrationPageController());
        controllerMap.put(new Request("GET", "/root/forgot"), ControllersFactory.getForgotPassword());
        controllerMap.put(new Request("GET", "/root/recoverPassword"), ControllersFactory.getRecoverPassword());
        controllerMap.put(new Request("GET", "/root/admin/users"), ControllersFactory.getAdminPage());
        controllerMap.put(new Request("GET", "/root/admin/orders"), ControllersFactory.getAdminOrdersPage());
        controllerMap.put(new Request("GET", "/root/editProfile"), ControllersFactory.getEditUserProfileController());
        controllerMap.put(new Request("GET", "/root/admin/createProduct"), ControllersFactory.getProductController());
//      POST methods from pages
        controllerMap.put(new Request("POST", "/root/selectGoods"), ControllersFactory.getSelectGoodsController());
        controllerMap.put(new Request("POST", "/root/login"), ControllersFactory.getLoginCheckController());
        controllerMap.put(new Request("POST", "/root/admin/users"), ControllersFactory.getEditUserRole());
        controllerMap.put(new Request("POST", "/root/admin/orders"), ControllersFactory.getEditOrder());
        controllerMap.put(new Request("POST", "/root/signup"), ControllersFactory.getSignUpCheckController());
        controllerMap.put(new Request("POST", "/root/editProfile"), ControllersFactory.getUserDataController());
        controllerMap.put(new Request("POST", "/root/admin/createCategory"), ControllersFactory.getCreateCategoryController());
        controllerMap.put(new Request("POST", "/root/admin/createProduct"), ControllersFactory.getCreateProductController());
        controllerMap.put(new Request("POST", "/root/addToCart"), ControllersFactory.getAddProductToCartController());
        controllerMap.put(new Request("POST", "/root/takeFromCart"), ControllersFactory.getTakeProductFromCartController());
        controllerMap.put(new Request("POST", "/root/makeOder"), ControllersFactory.getMakeOrderController());
        controllerMap.put(new Request("POST", "/root/submitOder"), ControllersFactory.getSubmitOrderController());
        controllerMap.put(new Request("POST", "/root/forgot"), ControllersFactory.getSendForgotPassword());
        controllerMap.put(new Request("POST", "/root/recoverPassword"), ControllersFactory.getUpdatePassword());
        controllerMap.put(new Request("POST", "/root/admin/upload"), ControllersFactory.getImageUploadController());
        controllerMap.put(new Request("POST", "/root/internationalization"), ControllersFactory.getInternationalizationController());
    }

    @Override
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
            if (vm == null) {
                vm = new ViewModel();
                vm.setView("home");
            }

            vm.setAttribute("hostPort", ContextFactory.getIp() + ":" + httpRequest.getLocalPort());

            Controller controller = controllerMap.get(request);
            if (controller == null) {
                logger.error("Can't handle " + request.getUri());
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }

            ifMultipart(httpRequest, response, request);
            vm = controller.process(request, vm);

            forward(httpRequest, response, vm);
        } catch (Throwable e) {
            logger.error("Error in request handeling" + e);
        }
    }

    synchronized private void ifMultipart(HttpServletRequest httpRequest, HttpServletResponse response, Request request) throws IOException {
        if (ServletFileUpload.isMultipartContent(httpRequest)) {
            try {
                request.setItemsForUpload(new ServletFileUpload(new DiskFileItemFactory()).parseRequest(httpRequest));
            } catch (FileUploadException e) {
                logger.error("error with getting multipart content" + e);
                response.sendError(HttpServletResponse.SC_PARTIAL_CONTENT);
            }
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
