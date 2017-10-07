package com.fluffypets.mvc.controller.pages;

import com.fluffypets.entities.Category;
import com.fluffypets.entities.Product;
import com.fluffypets.entities.User;
import com.fluffypets.mvc.controller.Controller;
import com.fluffypets.mvc.page_objects.Cart;
import com.fluffypets.mvc.page_objects.HomePagePref;
import com.fluffypets.mvc.servlets.Action;
import com.fluffypets.mvc.servlets.ViewModel;
import com.fluffypets.services.CategoryService;
import com.fluffypets.services.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.StringJoiner;

public class HomePageController implements Controller {
    private static final Logger logger = LogManager.getLogger(HomePageController.class.getName());
    private static final Integer defaultPaginationStep=6;

    private ProductService productService;
    private CategoryService categoryService;

    public HomePageController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @Override
    public ViewModel process(Action action, ViewModel vm) {

        HomePagePref homePagePref;

        if (vm.getAttribute("homePagePref") == null) {
            List<Category> categories = categoryService.getAll();
            StringJoiner categoryList = new StringJoiner(",");
            categories.forEach(category -> categoryList.add(category.getId().toString()));
            Integer paginationMax = productService.countSelected(categoryList.toString(), 0, Integer.MAX_VALUE, defaultPaginationStep);
            List<Product> products = productService.selectAndPagination(categoryList.toString(), 0, Integer.MAX_VALUE, "asc", defaultPaginationStep, 1);
            homePagePref = new HomePagePref(categories, "all", products, "asc", paginationMax, defaultPaginationStep, 1);
            vm.setAttribute("categories", categories);
            vm.setAttribute("homePagePref", homePagePref);
        }

        User user = (User) vm.getAttribute("user");
        if (user == null) {
            user = new User(0, "Unknown", "", "", "user");
        }

        Cart cart = (Cart) vm.getAttribute("cart");
        if (cart == null) {
            cart = new Cart(user);
        } else {
            cart.setUser(user);
        }
        vm.setAttribute("cart", cart);
        vm.setView("home");
        logger.info("home page selected");
        return vm;
    }


}
