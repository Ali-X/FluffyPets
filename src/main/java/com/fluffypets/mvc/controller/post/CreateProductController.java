package com.fluffypets.mvc.controller.post;

import com.fluffypets.mvc.controller.Controller;
import com.fluffypets.mvc.model.Category;
import com.fluffypets.mvc.model.Product;
import com.fluffypets.mvc.model.User;
import com.fluffypets.mvc.servlets.Command;
import com.fluffypets.mvc.servlets.ViewModel;
import com.fluffypets.servicies.product.CategoryService;
import com.fluffypets.servicies.product.ProductService;

import java.math.BigDecimal;

public class CreateProductController implements Controller {


    private ProductService productService;
    private CategoryService categoryService;

    public CreateProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @Override
    public ViewModel process(Command command, ViewModel vm) {
        User user = (User) vm.getAttribute("user");
        if (user == null) {
            vm.setView("login");
        } else {
            String productName = command.getAttribute("productName");
            String producer = command.getAttribute("producer");
            String description = command.getAttribute("description");
            String pictureURL = command.getAttribute("pictureURL");
            BigDecimal price = new BigDecimal(command.getAttribute("price"));
            Integer categoryName = new Integer(command.getAttribute("categorySelId"));
            Category category = categoryService.get(categoryName);

            Product product = new Product(productName, producer, price, description, pictureURL, category);

            productService.create(product);
        }
        vm.setView("createProduct");
        return vm;
    }
}