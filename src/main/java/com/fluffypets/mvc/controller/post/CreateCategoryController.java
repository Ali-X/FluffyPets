package com.fluffypets.mvc.controller.post;

import com.fluffypets.mvc.controller.Controller;
import com.fluffypets.mvc.model.Category;
import com.fluffypets.mvc.model.User;
import com.fluffypets.mvc.servlets.Command;
import com.fluffypets.mvc.servlets.ViewModel;
import com.fluffypets.servicies.product.CategoryService;

import java.util.List;

public class CreateCategoryController implements Controller{

    private CategoryService categoryService;

    public CreateCategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public ViewModel process(Command command, ViewModel vm) {
        User user = (User) vm.getAttribute("user");
        if (user == null) {
            vm.setView("login");
        } else {
            String categoryName = command.getAttribute("categoryName");
            String categoryDescription = command.getAttribute("categoryDescription");
            Category category = new Category(categoryName, categoryDescription);
            categoryService.create(category);
            List<Category> categories = categoryService.getAll();
            vm.setAttribute("categories", categories);
            vm.setView("createProduct");
        }
        return vm;
    }
}