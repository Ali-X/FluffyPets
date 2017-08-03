package com.fluffypets.MVC.controller;

import com.fluffypets.MVC.model.Category;
import com.fluffypets.MVC.modelView.ViewModel;
import com.fluffypets.MVC.servlets.Request;
import com.fluffypets.servicies.CategoryService;

import java.util.List;

public class GetAllCategoriesController implements Controller {

  private final CategoryService service;

  public GetAllCategoriesController(CategoryService service) {
    this.service = service;
  }

  @Override
  public ViewModel process(Request request) {
    List<Category> categories = service.findAll();
    return new ViewModel("/categories.jsp")
        .withAttribute("categories", categories);
  }
}
