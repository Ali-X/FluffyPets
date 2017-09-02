package com.fluffypets.MVC.controller.pages;

import com.fluffypets.MVC.controller.Controller;
import com.fluffypets.MVC.model.Category;
import com.fluffypets.MVC.model.Product;
import com.fluffypets.MVC.servlets.Request;
import com.fluffypets.MVC.servlets.ViewModel;
import com.fluffypets.factory.Factory;
import com.fluffypets.servicies.CategoryService;
import com.fluffypets.servicies.ProductService;

import java.util.List;

public class HomePageController implements Controller {
    private ProductService productService;
    private CategoryService categoryService;

    public HomePageController(ProductService productService,CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @Override
    public ViewModel process(Request request) {
        List<Product> products=productService.getAll();
        List<Category> categories=categoryService.getAll();
        ViewModel vm = Factory.getViewModel();
        vm.setAttribute("products",products);
        vm.setAttribute("categories",categories);
        vm.setView("home");
        return vm;
    }
}
