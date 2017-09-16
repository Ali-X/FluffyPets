package com.fluffypets.MVC.controller.pages;

import com.fluffypets.MVC.controller.Controller;
import com.fluffypets.MVC.model.Cart;
import com.fluffypets.MVC.model.Category;
import com.fluffypets.MVC.model.Product;
import com.fluffypets.MVC.model.User;
import com.fluffypets.MVC.model.enumes.Prices;
import com.fluffypets.MVC.servlets.Request;
import com.fluffypets.MVC.servlets.ViewModel;
import com.fluffypets.factory.Factory;
import com.fluffypets.servicies.CategoryService;
import com.fluffypets.servicies.ProductService;
import com.fluffypets.servicies.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.List;

public class HomePageController implements Controller {
    private static final Logger logger = LogManager.getLogger(HomePageController.class.getName());

    private ProductService productService;
    private CategoryService categoryService;
    private UserService userService;

    public HomePageController(ProductService productService,CategoryService categoryService,UserService userService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.userService=userService;
    }

    @Override
    public ViewModel process(Request request) {
//        File dir = new File(System.getProperty("catalina.base"), "uploads");
//        System.out.println(dir);
        List<Product> products=productService.getAll();
        List<Category> categories=categoryService.getAll();
        ViewModel vm = Factory.getViewModel();
        vm.setAttribute("products",products);
        vm.setAttribute("categories",categories);
        vm.setAttribute("prices", Prices.values());

        User user=(User) vm.getAttribute("user");
        if (user==null){
        user=new User(0,"Unknown","","","","user");}

        Cart cart=(Cart) vm.getAttribute("cart");
        if (cart==null){
        cart = new Cart(user);}
        else {cart.setUser(user);}
        vm.setAttribute("cart", cart);
        vm.setView("home");
        logger.info("home page selected");
        return vm;
    }
}
