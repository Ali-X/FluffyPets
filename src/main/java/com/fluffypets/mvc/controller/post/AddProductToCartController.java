package com.fluffypets.mvc.controller.post;

import com.fluffypets.mvc.controller.Controller;
import com.fluffypets.mvc.page_objects.Cart;
import com.fluffypets.entities.User;
import com.fluffypets.mvc.servlets.Command;
import com.fluffypets.mvc.servlets.ViewModel;
import com.fluffypets.services.ProductService;

public class AddProductToCartController implements Controller{

    private ProductService productService;

    public AddProductToCartController(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public ViewModel process(Command command, ViewModel vm) {
        Cart cart = (Cart) vm.getAttribute("cart");
        User user = (User) vm.getAttribute("user");
        if (cart == null) {
            cart = new Cart(user);
        } else {
            Integer productId = Integer.valueOf(command.getAttribute("productId"));
            cart.addGood(productService.getProductById(productId));
        }
        vm.setAttribute("cart", cart);
        return vm;
    }
}