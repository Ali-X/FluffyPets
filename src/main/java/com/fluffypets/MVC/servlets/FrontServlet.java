package com.fluffypets.MVC.servlets;

import com.fluffypets.MVC.controller.*;
import com.fluffypets.MVC.modelView.ViewModel;
import com.fluffypets.factory.Factory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FrontServlet extends HttpServlet {

    Map<Request, Controller> controllerMap = new HashMap<>();

    @Override
    public void init() throws ServletException {
        super.init();

        Controller getAllCategoriesCtrl =Factory.createCategoryController(GetAllCategoriesController.class);
        Controller createCategoryCtrl =Factory.createCategoryController(GetAllCategoriesController.class);
        Controller createUserCtrl =Factory.createUserController(CreateUserController.class);

        controllerMap.put(Request.create("GET", "/servlet/categories"), getAllCategoriesCtrl);
        controllerMap.put(Request.create("GET", "/servlet/"), getAllCategoriesCtrl);
        controllerMap.put(Request.create("POST", "/servlet/categories"), createCategoryCtrl);
        controllerMap.put(Request.create("GET", "/servlet/signup"), new SignUpController());
        controllerMap.put(Request.create("POST", "/servlet/signup"), createUserCtrl);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handle(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handle(req, resp);
    }

    private void handle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Request request = new Request(req.getParameterMap(), req.getMethod(), req.getRequestURI());
        try {

            Controller controller = controllerMap.get(request);
            if (controller == null) {
                throw new RuntimeException("Can not handle " + request);
            }
            ViewModel vm = controller.process(request);

            if (vm.hasCookies()) {
                Map<String, String> newCookies = vm.getNewCookies();
                for (String cookieName : newCookies.keySet()) {
                    resp.addCookie(new Cookie(cookieName, newCookies.get(cookieName)));
                }
            }

            forward(req, resp, vm);
        } catch (Throwable t) {
            ViewModel vm = new ErrorController().process(request);
            vm.withAttribute("error", t.getClass() + " " + t.getMessage());
            forward(req, resp, vm);
        }
    }

    private void forward(HttpServletRequest req, HttpServletResponse resp, ViewModel vm) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher(getView(req, vm));
        setAttributes(req, vm);
        dispatcher.forward(req, resp);
    }

    private void setAttributes(HttpServletRequest req, ViewModel vm) {
        for (String attr : vm.getAttributes().keySet()) {
            req.setAttribute(attr, vm.getAttribute(attr));
        }
    }

    private String getView(HttpServletRequest req, ViewModel vm) {
        return req.getContextPath() + vm.getView();
    }
}
