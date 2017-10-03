package com.fluffypets.mvc.controller.post;

import com.fluffypets.mvc.controller.Controller;
import com.fluffypets.mvc.page_objects.Cart;
import com.fluffypets.entities.Order;
import com.fluffypets.entities.User;
import com.fluffypets.entities.UserAddress;
import com.fluffypets.mvc.servlets.Command;
import com.fluffypets.mvc.servlets.ViewModel;
import com.fluffypets.services.OrderService;
import com.fluffypets.services.SendEmailService;
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
        UserAddress userAddress;

        String comment = command.getAttribute("orderComment");
        String fullName = command.getAttribute("Fullname");
        String district = command.getAttribute("District");
        String area = command.getAttribute("Area");
        String street = command.getAttribute("Street");
        String app = command.getAttribute("App");
        String telephone = command.getAttribute("PhoneNumber");

        userAddress = orderService.updateAddress(user.getId(), fullName, district, area, street, app, telephone);
        Order order = orderService.makeOrder(user, cart, comment);

        String subject = "Your order №" + order.getOrderId() + " was acepted";
        String content = "Dear " + userAddress.getFullName() + ", we are glad that you are our customer!\n" +
                "Soon we will contact you and verify order data.\n" +
                "Total price of your order is " + cart.getTotalPrice() + "$";
        if (!sendEmailService.sendEmailTo(user.getEmail(), subject, content))
            logger.error("letter to userId=" + user.getId() + " was not sent");
        vm.setAttribute("cart", new Cart(user));
        vm.setAttribute("userAddress", userAddress);
        vm.setView("thankyou");
        return vm;
    }
}