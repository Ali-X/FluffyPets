package com.fluffypets.MVC.controller.post;

import com.fluffypets.MVC.controller.Controller;
import com.fluffypets.MVC.model.Category;
import com.fluffypets.MVC.model.Product;
import com.fluffypets.MVC.model.User;
import com.fluffypets.MVC.servlets.Request;
import com.fluffypets.MVC.servlets.ViewModel;
import com.fluffypets.factory.Factory;
import com.fluffypets.servicies.CategoryService;
import com.fluffypets.servicies.ProductService;
import com.fluffypets.servicies.UserDataService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;

public class CreateProductController implements Controller {
    private static final Logger logger = LogManager.getLogger(CreateProductController.class.getName());


    private ProductService productService;
    private CategoryService categoryService;

    public CreateProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @Override
    public ViewModel process(Request request) {
        ViewModel vm = Factory.getViewModel();
        User user = (User) vm.getAttribute("user");
        if (user == null) {
            vm.setView("login");
        } else {
            String productName = request.getAttribute("productName");
            String producer = request.getAttribute("producer");
            String description = request.getAttribute("description");
            String pictureURL = request.getAttribute("pictureURL");
            BigDecimal price = new BigDecimal(request.getAttribute("price"));
            Integer categoryName = new Integer(request.getAttribute("categorySelId"));
            Category category=categoryService.get(categoryName);

            Product product=new Product(productName,producer, price,description,pictureURL,category);

            productService.create(product);
        }
        vm.setView("createProduct");
        return vm;
    }
}