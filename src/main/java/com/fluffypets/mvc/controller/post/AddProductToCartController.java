package com.fluffypets.mvc.controller.post;

import com.fluffypets.mvc.controller.Controller;
import com.fluffypets.mvc.page_objects.Cart;
import com.fluffypets.entities.User;
import com.fluffypets.mvc.servlets.Action;
import com.fluffypets.mvc.servlets.ViewModel;
import com.fluffypets.services.ProductService;

public class AddProductToCartController implements Controller {

    private ProductService productService;

    public AddProductToCartController(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public ViewModel process(Action action, ViewModel vm) {
        Cart cart = (Cart) vm.getAttribute("cart");
        User user = (User) vm.getAttribute("user");
        Integer number = Integer.valueOf(action.getAttribute("number"));

        if (number != null && number >= 0) {
            if (cart == null) {
                cart = new Cart(user);
            } else {
                Integer productId = Integer.valueOf(action.getAttribute("productId"));
                cart.addGood(number, productService.getProductById(productId));
            }
            vm.setAttribute("cart", cart);
        }
        return vm;
    }
}