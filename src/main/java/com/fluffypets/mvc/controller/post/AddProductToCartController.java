package com.fluffypets.mvc.controller.post;

import com.fluffypets.mvc.controller.Controller;
import com.fluffypets.mvc.model.Cart;
import com.fluffypets.mvc.model.User;
import com.fluffypets.mvc.servlets.Command;
import com.fluffypets.mvc.servlets.ViewModel;
import com.fluffypets.servicies.product.ProductService;

public class AddProductToCartController implements Controller, AutoCloseable {

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

    @Override
    public void close() throws Exception {
        productService.close();
    }
}