package com.fluffypets.MVC.controller.pages;

import com.fluffypets.MVC.controller.Controller;
import com.fluffypets.MVC.model.Category;
import com.fluffypets.MVC.servlets.Request;
import com.fluffypets.MVC.servlets.ViewModel;
import com.fluffypets.factory.Factory;
import com.fluffypets.servicies.CategoryService;
import com.fluffypets.servicies.ProductService;
import com.fluffypets.servicies.UserDataService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ProductController implements Controller,AutoCloseable {
    private static final Logger logger = LogManager.getLogger(ProductController.class.getName());

    private CategoryService categoryService;

    public ProductController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public ViewModel process(Request request) {
        ViewModel vm = Factory.getViewModel();
        List<Category> categories=categoryService.getAll();
        vm.setAttribute("categories",categories);
        vm.setView("createProduct");
        logger.info("createProduct page selected");
        return vm;
    }

    @Override
    public void close() throws Exception {
        categoryService.close();
    }
}
