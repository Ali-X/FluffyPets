package com.fluffypets.MVC.controller.post;

import com.fluffypets.MVC.controller.Controller;
import com.fluffypets.MVC.model.Category;
import com.fluffypets.MVC.model.User;
import com.fluffypets.MVC.servlets.Request;
import com.fluffypets.MVC.servlets.ViewModel;
import com.fluffypets.servicies.CategoryService;

import java.util.List;

public class CreateCategoryController implements Controller,AutoCloseable {

    private CategoryService categoryService;

    public CreateCategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public ViewModel process(Request request, ViewModel vm) {
        User user = (User) vm.getAttribute("user");
        if (user == null) {
            vm.setView("login");
        } else {
            String categoryName = request.getAttribute("categoryName");
            String categoryDescription = request.getAttribute("categoryDescription");
            Category category = new Category(categoryName, categoryDescription);
            categoryService.create(category);
            List<Category> categories = categoryService.getAll();
            vm.setAttribute("categories", categories);
            vm.setView("createProduct");
        }
        return vm;
    }

    @Override
    public void close() throws Exception {
        categoryService.close();
    }
}