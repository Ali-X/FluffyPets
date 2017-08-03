package com.fluffypets.MVC.controller;

import com.fluffypets.MVC.modelView.ViewModel;
import com.fluffypets.MVC.servlets.Request;

public class ProfileController implements Controller {

  @Override
  public ViewModel process(Request request) {
    String userId = request.getParameter("userId");

    return new ViewModel("/profile.jsp");
  }
}
