package com.fluffypets.mvc.controller.pages;

import com.fluffypets.mvc.controller.Controller;
import com.fluffypets.entities.Category;
import com.fluffypets.mvc.servlets.Action;
import com.fluffypets.mvc.servlets.ViewModel;
import com.fluffypets.services.CategoryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ProductController implements Controller {
    private static final Logger logger = LogManager.getLogger(ProductController.class.getName());

    private CategoryService categoryService;

    public ProductController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public ViewModel process(Action action, ViewModel vm) {
        List<Category> categories=categoryService.getAll();
        vm.setAttribute("categories",categories);
        vm.setView("createProduct");
        logger.info("createProduct page selected");
        return vm;
    }
}
