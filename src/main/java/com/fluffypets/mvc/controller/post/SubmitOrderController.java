package com.fluffypets.mvc.controller.post;

import com.fluffypets.mvc.controller.Controller;
import com.fluffypets.mvc.model.page_objects.Cart;
import com.fluffypets.mvc.model.Order;
import com.fluffypets.mvc.model.User;
import com.fluffypets.mvc.model.UserAdress;
import com.fluffypets.mvc.servlets.Command;
import com.fluffypets.mvc.servlets.ViewModel;
import com.fluffypets.servicies.order.OrderService;
import com.fluffypets.servicies.email.SendEmailService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SubmitOrderController implements Controller {
    private static final Logger logger = LogManager.getLogger(SubmitOrderController.class.getName());

    private final OrderService orderService;
    private final SendEmailService sendEmailService;

    public SubmitOrderController(OrderService orderService, SendEmailService sendEmailService) {
        this.orderService = orderService;
        this.sendEmailService = sendEmailService;
    }

    @Override
    public ViewModel process(Command command, ViewModel vm) {
        User user = (User) vm.getAttribute("user");
        Cart cart = (Cart) vm.getAttribute("cart");
        UserAdress userAdress;

        String comment = command.getAttribute("orderComment");
        String fullName = command.getAttribute("Fullname");
        String district = command.getAttribute("District");
        String area = command.getAttribute("Area");
        String street = command.getAttribute("Street");
        String app = command.getAttribute("App");
        String telephone = command.getAttribute("PhoneNumber");

        userAdress = orderService.updateAddress(user.getId(), fullName, district, area, street, app, telephone);
        Order order = orderService.makeOrder(user, cart, comment);

        String subject = "Your order №" + order.getOrderId() + " was acepted";
        String content = "Dear " + userAdress.getFullName() + ", we are glad that you are our customer!\n" +
                "Soon we will contact you and verify order data.\n" +
                "Total price of your order is " + cart.getTotalPrice() + "$";
        if (!sendEmailService.sendEmailTo(user.getEmail(), subject, content))
            logger.error("letter to userId=" + user.getId() + " was not sent");
        vm.setAttribute("cart", new Cart(user));
        vm.setAttribute("userAdress", userAdress);
        vm.setView("thankyou");
        return vm;
    }
}