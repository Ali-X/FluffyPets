package com.fluffypets.mvc.controller.post;

import com.fluffypets.mvc.controller.Controller;
import com.fluffypets.entities.Category;
import com.fluffypets.entities.Product;
import com.fluffypets.entities.User;
import com.fluffypets.mvc.servlets.Action;
import com.fluffypets.mvc.servlets.ViewModel;
import com.fluffypets.services.CategoryService;
import com.fluffypets.services.ProductService;

import java.math.BigDecimal;

public class CreateProductController implements Controller {


    private ProductService productService;
    private CategoryService categoryService;

    public CreateProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @Override
    public ViewModel process(Action action, ViewModel vm) {
        User user = (User) vm.getAttribute("user");
        if (user == null) {
            vm.setView("login");
        } else {
            String productName = action.getAttribute("productName");
            String producer = action.getAttribute("producer");
            String description = action.getAttribute("description");
            String pictureURL = action.getAttribute("pictureURL");
            BigDecimal price = new BigDecimal(action.getAttribute("price"));
            Integer categoryName = new Integer(action.getAttribute("categorySelId"));
            Category category = categoryService.get(categoryName);
            vm.setAttribute("currentCategory",category);

            Product product = new Product(productName, producer, price, description, pictureURL, category);

            productService.create(product);
        }
        vm.setView("createProduct");
        return vm;
    }
}