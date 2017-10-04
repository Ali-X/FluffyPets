package com.fluffypets.mvc.controller.post;

import com.fluffypets.mvc.controller.Controller;
import com.fluffypets.entities.Category;
import com.fluffypets.entities.User;
import com.fluffypets.mvc.servlets.Action;
import com.fluffypets.mvc.servlets.ViewModel;
import com.fluffypets.services.CategoryService;

import java.util.List;

public class CreateCategoryController implements Controller{

    private CategoryService categoryService;

    public CreateCategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public ViewModel process(Action action, ViewModel vm) {
        User user = (User) vm.getAttribute("user");
        if (user == null) {
            vm.setView("login");
        } else {
            String categoryName = action.getAttribute("categoryName");
            String categoryDescription = action.getAttribute("nameUa");
            Category category = new Category(categoryName, categoryDescription);
            categoryService.create(category);
            List<Category> categories = categoryService.getAll();
            vm.setAttribute("categories", categories);
            vm.setView("createProduct");
        }
        return vm;
    }
}