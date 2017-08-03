package com.fluffypets.MVC.controller;

import com.fluffypets.MVC.model.User;
import com.fluffypets.MVC.modelView.ViewModel;
import com.fluffypets.MVC.servlets.Request;
import com.fluffypets.servicies.UserService;

public class CreateUserController implements Controller {

  private final UserService service;

  public CreateUserController(UserService service) {
    this.service = service;
  }

  @Override
  public ViewModel process(Request request) {

    String userName = request.getParameter("userName");
    String password = request.getParameter("password");
    String token = userName + System.nanoTime();

    service.create(User.from(userName, password, token));
    return new ViewModel("/profile.jsp")
        .addCookie("token", token);
  }
}
