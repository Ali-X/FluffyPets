package com.fluffypets.MVC.controller.post;

import com.fluffypets.MVC.controller.Controller;
import com.fluffypets.MVC.model.Cart;
import com.fluffypets.MVC.model.Order;
import com.fluffypets.MVC.model.User;
import com.fluffypets.MVC.model.UserData;
import com.fluffypets.MVC.servlets.Request;
import com.fluffypets.MVC.servlets.ViewModel;
import com.fluffypets.factory.Factory;
import com.fluffypets.servicies.OrderService;
import com.fluffypets.servicies.SendEmailService;
import com.fluffypets.servicies.UserDataService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SubmitOrderController implements Controller,AutoCloseable {
    private static final Logger logger = LogManager.getLogger(SubmitOrderController.class.getName());

    private final OrderService orderService;
    private final SendEmailService sendEmailService;

    public SubmitOrderController( OrderService orderService,SendEmailService sendEmailService) {
        this.orderService = orderService;
        this.sendEmailService=sendEmailService;
    }

    @Override
    public ViewModel process(Request request) {
        ViewModel vm = Factory.getViewModel();
        User user = (User) vm.getAttribute("user");
        Cart cart = (Cart) vm.getAttribute("cart");
        UserData userData = (UserData) vm.getAttribute("userData");

        String comment=request.getAttribute("orderComment");
        String fullName=request.getAttribute("Fullname");
        String district=request.getAttribute("District");
        String area=request.getAttribute("Area");
        String street=request.getAttribute("Street");
        String app=request.getAttribute("App");
        String telephone=request.getAttribute("PhoneNumber");

//        orderService.updateAddress(user.getId(),fullName,district,area,street,app,telephone);
        Order order=orderService.makeOrder(user,cart,comment);

        String subject="Your order №"+order.getOrderId()+" was acepted";
        String content="Dear "+userData.getFullName()+", we are glad that you are our customer!\n"+
                "Soon we will contact you and verify order data.\n"+
                "Total price of your order is "+cart.getTotalPrice()+"$";
        if (!sendEmailService.sendEmailTo(user.getEmail(),subject,content))
            logger.error("letter to userId="+user.getId()+" was not sent");
        vm.removeAttribute("cart");
        vm.removeAttribute("userData");
        vm.setView("thankyou");
        return vm;
    }

    @Override
    public void close() throws Exception {
        orderService.close();
    }
}