package com.fluffypets.mvc.servlets;

import com.fluffypets.exeptions.ServiciesException;
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

    private static Map<Action, Controller> controllerMap = new HashMap<>();

    @Override
    public void init() {
        ContextFactory.migrate();
//      pages GET links
        controllerMap.put(new Action("GET", "/root/home"), ControllersFactory.getHomeController());
        controllerMap.put(new Action("GET", "/root/login"), ControllersFactory.getLoginPageController());
        controllerMap.put(new Action("GET", "/root/logout"), ControllersFactory.getLogoutPageController());
        controllerMap.put(new Action("GET", "/root/profile"), ControllersFactory.getProfilePageController());
        controllerMap.put(new Action("GET", "/root/signup"), ControllersFactory.getRegistrationPageController());
        controllerMap.put(new Action("GET", "/root/forgot"), ControllersFactory.getForgotPassword());
        controllerMap.put(new Action("GET", "/root/recoverPassword"), ControllersFactory.getRecoverPassword());
        controllerMap.put(new Action("GET", "/root/admin/users"), ControllersFactory.getAdminPage());
        controllerMap.put(new Action("GET", "/root/admin/orders"), ControllersFactory.getAdminOrdersPage());
        controllerMap.put(new Action("GET", "/root/admin/storage"), ControllersFactory.getAdminStorage());
        controllerMap.put(new Action("GET", "/root/editProfile"), ControllersFactory.getEditUserProfileController());
        controllerMap.put(new Action("GET", "/root/admin/createProduct"), ControllersFactory.getProductController());
//      POST methods from pages
        controllerMap.put(new Action("POST", "/root/selectGoods"), ControllersFactory.getSelectGoodsController());
        controllerMap.put(new Action("POST", "/root/login"), ControllersFactory.getLoginCheckController());
        controllerMap.put(new Action("POST", "/root/admin/users"), ControllersFactory.getEditUserRole());
        controllerMap.put(new Action("POST", "/root/admin/orders"), ControllersFactory.getEditOrder());
        controllerMap.put(new Action("POST", "/root/admin/storage"), ControllersFactory.getStorageEdit());
        controllerMap.put(new Action("POST", "/root/signup"), ControllersFactory.getSignUpCheckController());
        controllerMap.put(new Action("POST", "/root/editProfile"), ControllersFactory.getUserDataController());
        controllerMap.put(new Action("POST", "/root/admin/createCategory"), ControllersFactory.getCreateCategoryController());
        controllerMap.put(new Action("POST", "/root/admin/createProduct"), ControllersFactory.getCreateProductController());
        controllerMap.put(new Action("POST", "/root/addToCart"), ControllersFactory.getAddProductToCartController());
        controllerMap.put(new Action("POST", "/root/takeFromCart"), ControllersFactory.getTakeProductFromCartController());
        controllerMap.put(new Action("POST", "/root/makeOder"), ControllersFactory.getMakeOrderController());
        controllerMap.put(new Action("POST", "/root/submitOder"), ControllersFactory.getSubmitOrderController());
        controllerMap.put(new Action("POST", "/root/forgot"), ControllersFactory.getSendForgotPassword());
        controllerMap.put(new Action("POST", "/root/recoverPassword"), ControllersFactory.getUpdatePassword());
        controllerMap.put(new Action("POST", "/root/admin/upload"), ControllersFactory.getImageUploadController());
        controllerMap.put(new Action("POST", "/root/internationalization"), ControllersFactory.getInternationalizationController());
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

        Action action = new Action(httpRequest.getMethod(), httpRequest.getRequestURI(), httpRequest.getParameterMap());


        ViewModel vm = (ViewModel) httpRequest.getSession().getAttribute("vm");

        vm.setAttribute("hostPort", ContextFactory.getIp() + ":" + httpRequest.getLocalPort());

        Controller controller = controllerMap.get(action);
        if (controller == null) {
            logger.error("Can't handle " + action.getUri());
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            throw new ServiciesException("Can't handle " + action.getUri());
        }

        ifMultipart(httpRequest, response, action, vm);
        vm = controller.process(action, vm);

        forward(httpRequest, response, vm);

    }

    private synchronized void ifMultipart(HttpServletRequest httpRequest, HttpServletResponse response, Action action, ViewModel vm) throws IOException {
        if (ServletFileUpload.isMultipartContent(httpRequest)) {
            try {
                action.setItemsForUpload(new ServletFileUpload(new DiskFileItemFactory()).parseRequest(httpRequest));
                vm.setView("createProduct");
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
