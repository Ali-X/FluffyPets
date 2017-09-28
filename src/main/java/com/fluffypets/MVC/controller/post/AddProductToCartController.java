package com.fluffypets.MVC.controller.post;

import com.fluffypets.MVC.controller.Controller;
import com.fluffypets.MVC.model.Cart;
import com.fluffypets.MVC.model.User;
import com.fluffypets.MVC.servlets.Request;
import com.fluffypets.MVC.servlets.ViewModel;
import com.fluffypets.servicies.product.ProductService;

public class AddProductToCartController implements Controller,AutoCloseable {

    private ProductService productService;

    public AddProductToCartController(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public ViewModel process(Request request,ViewModel vm) {
        Cart cart = (Cart) vm.getAttribute("cart");
        User user = (User) vm.getAttribute("user");
        if (cart == null) {
            cart=new Cart(user);
        } else {
            Integer productId = Integer.valueOf(request.getAttribute("productId"));
            cart.addGood(productService.getProductById(productId));
        }
        vm.setAttribute("cart",cart);
        return vm;
    }

    @Override
    public void close() throws Exception {
        productService.close();
    }
}