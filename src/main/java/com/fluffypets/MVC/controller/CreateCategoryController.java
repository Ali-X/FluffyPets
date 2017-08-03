package com.fluffypets.MVC.controller;


import com.fluffypets.MVC.model.Category;
import com.fluffypets.MVC.modelView.ViewModel;
import com.fluffypets.MVC.servlets.Request;
import com.fluffypets.servicies.CategoryService;

import java.util.List;

public class CreateCategoryController implements Controller {

  private final CategoryService service;

  public CreateCategoryController(CategoryService service) {
    this.service = service;
  }

  @Override
  public ViewModel process(Request request) {
    ViewModel vm = new ViewModel("/categories.jsp");

    String categoryName = request.getParameter("categoryName");
    if (categoryName == null) {
      throw new RuntimeException("Category Name is Null");
    }
    service.create(new Category(categoryName));

    if (request.getParameter("userId") != null) {
      vm.withAttribute("isLoggedIn", "true");
      vm.withAttribute("username", "Vova");
    }

    List<Category> categories = service.findAll();
    return vm
        .withAttribute("categories", categories);
  }
}