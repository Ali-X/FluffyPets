package com.fluffypets.mvc.servlets;

import com.fluffypets.factory.ContextFactory;
import com.fluffypets.factory.ControllersFactory;
import com.fluffypets.mvc.controller.Controller;
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

public class FrontServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(FrontServlet.class.getName());

    private static Map<Command, Controller> controllerMap = new HashMap<>();

    @Override
    public void init() {
//      pages GET links
        controllerMap.put(new Command("GET", "/root/home"), ControllersFactory.getHomeController());
        controllerMap.put(new Command("GET", "/root/login"), ControllersFactory.getLoginPageController());
        controllerMap.put(new Command("GET", "/root/logout"), ControllersFactory.getLogoutPageController());
        controllerMap.put(new Command("GET", "/root/profile"), ControllersFactory.getProfilePageController());
        controllerMap.put(new Command("GET", "/root/signup"), ControllersFactory.getRegistrationPageController());
        controllerMap.put(new Command("GET", "/root/forgot"), ControllersFactory.getForgotPassword());
        controllerMap.put(new Command("GET", "/root/recoverPassword"), ControllersFactory.getRecoverPassword());
        controllerMap.put(new Command("GET", "/root/admin/users"), ControllersFactory.getAdminPage());
        controllerMap.put(new Command("GET", "/root/admin/orders"), ControllersFactory.getAdminOrdersPage());
        controllerMap.put(new Command("GET", "/root/editProfile"), ControllersFactory.getEditUserProfileController());
        controllerMap.put(new Command("GET", "/root/admin/createProduct"), ControllersFactory.getProductController());
//      POST methods from pages
        controllerMap.put(new Command("POST", "/root/selectGoods"), ControllersFactory.getSelectGoodsController());
        controllerMap.put(new Command("POST", "/root/login"), ControllersFactory.getLoginCheckController());
        controllerMap.put(new Command("POST", "/root/admin/users"), ControllersFactory.getEditUserRole());
        controllerMap.put(new Command("POST", "/root/admin/orders"), ControllersFactory.getEditOrder());
        controllerMap.put(new Command("POST", "/root/signup"), ControllersFactory.getSignUpCheckController());
        controllerMap.put(new Command("POST", "/root/editProfile"), ControllersFactory.getUserDataController());
        controllerMap.put(new Command("POST", "/root/admin/createCategory"), ControllersFactory.getCreateCategoryController());
        controllerMap.put(new Command("POST", "/root/admin/createProduct"), ControllersFactory.getCreateProductController());
        controllerMap.put(new Command("POST", "/root/addToCart"), ControllersFactory.getAddProductToCartController());
        controllerMap.put(new Command("POST", "/root/takeFromCart"), ControllersFactory.getTakeProductFromCartController());
        controllerMap.put(new Command("POST", "/root/makeOder"), ControllersFactory.getMakeOrderController());
        controllerMap.put(new Command("POST", "/root/submitOder"), ControllersFactory.getSubmitOrderController());
        controllerMap.put(new Command("POST", "/root/forgot"), ControllersFactory.getSendForgotPassword());
        controllerMap.put(new Command("POST", "/root/recoverPassword"), ControllersFactory.getUpdatePassword());
        controllerMap.put(new Command("POST", "/root/admin/upload"), ControllersFactory.getImageUploadController());
        controllerMap.put(new Command("POST", "/root/internationalization"), ControllersFactory.getInternationalizationController());
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

        Command command = new Command(httpRequest.getMethod(), httpRequest.getRequestURI(), httpRequest.getParameterMap());

        try {
            ViewModel vm = (ViewModel) httpRequest.getSession().getAttribute("vm");
            if (vm == null) {
                vm = new ViewModel();
                vm.setView("home");
            }

            vm.setAttribute("hostPort", ContextFactory.getIp() + ":" + httpRequest.getLocalPort());

            Controller controller = controllerMap.get(command);
            if (controller == null) {
                logger.error("Can't handle " + command.getUri());
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }

            ifMultipart(httpRequest, response, command);
            vm = controller.process(command, vm);

            forward(httpRequest, response, vm);
        } catch (Exception e) {
            logger.error("Error in command handeling" + e);
        }
    }

    private synchronized void ifMultipart(HttpServletRequest httpRequest, HttpServletResponse response, Command command) throws IOException {
        if (ServletFileUpload.isMultipartContent(httpRequest)) {
            try {
                command.setItemsForUpload(new ServletFileUpload(new DiskFileItemFactory()).parseRequest(httpRequest));
            } catch (FileUploadException e) {
                logger.error("error with getting multipart content" + e);
                response.sendError(HttpServletResponse.SC_PARTIAL_CONTENT);
            }
        }
    }

    private synchronized void forward(HttpServletRequest request, HttpServletResponse resp, ViewModel vm) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(getView(vm));
        request.getSession().setAttribute("vm", vm);
        setAttributes(request, vm);
        dispatcher.forward(request, resp);
    }

    private synchronized void setAttributes(HttpServletRequest request, ViewModel vm) {
        vm.getAttributes().keySet().forEach(key -> request.setAttribute(key, vm.getAttribute(key)));
    }

    private synchronized String getView(ViewModel vm) {
        String prefix = "/WEB-INF/views/";
        String suffix = ".jsp";
        return prefix + vm.getView() + suffix;
    }
}
