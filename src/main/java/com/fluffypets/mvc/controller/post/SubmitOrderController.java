package com.fluffypets.mvc.controller.post;

import com.fluffypets.mvc.controller.Controller;
import com.fluffypets.mvc.page_objects.Cart;
import com.fluffypets.entities.Order;
import com.fluffypets.entities.User;
import com.fluffypets.entities.UserAddress;
import com.fluffypets.mvc.page_objects.ValidationMessage;
import com.fluffypets.mvc.servlets.Action;
import com.fluffypets.mvc.servlets.ViewModel;
import com.fluffypets.services.OrderService;
import com.fluffypets.services.SendEmailService;
import com.fluffypets.validators.impl.AddressValidator;
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
    public ViewModel process(Action action, ViewModel vm) {
        User user = (User) vm.getAttribute("user");
        Cart cart = (Cart) vm.getAttribute("cart");

        String comment = action.getAttribute("orderComment");
        if (comment!=null && comment.length()>127)comment=comment.substring(0,127);
        String fullName = action.getAttribute("Fullname");
        String district = action.getAttribute("District");
        String area = action.getAttribute("Area");
        String street = action.getAttribute("Street");
        String app = action.getAttribute("App");
        String telephone = action.getAttribute("PhoneNumber");

        UserAddress userAddress=new UserAddress(0,fullName,district,area,street,app,telephone);
        AddressValidator addressValidator=new AddressValidator();
        ValidationMessage<UserAddress> addressVal= addressValidator.validate(userAddress);
        vm.setAttribute("addressVal", addressVal);

        if (!addressVal.getValidationMessage().equals("Ok")) {
            vm.setView("submitOrder");
            vm.setAttribute("addressVal", addressVal);
        } else {
        userAddress = orderService.updateAddress(user.getId(), userAddress);
        Order order = orderService.makeOrder(user, cart, comment);

        String subject = "Your order №" + order.getOrderId() + " was acepted";
        String content = "Dear " + userAddress.getFullName() + ", we are glad that you are our customer!\n" +
                "Soon we will contact you and verify order data.\n" +
                "Total price of your order is " + cart.getTotalPrice() + "$";
        if (!sendEmailService.sendEmailTo(user.getEmail(), subject, content))
            logger.error("letter to userId=" + user.getId() + " was not sent");
        vm.setAttribute("cart", new Cart(user));
        vm.setAttribute("userAddress", userAddress);
        vm.setView("thankyou");}
        return vm;
    }
}