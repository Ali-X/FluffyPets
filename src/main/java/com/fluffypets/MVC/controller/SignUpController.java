package com.fluffypets.MVC.controller;

import com.fluffypets.MVC.modelView.ViewModel;
import com.fluffypets.MVC.servlets.Request;

public class SignUpController implements Controller {
  @Override
  public ViewModel process(Request request) {
    return new ViewModel("/signup.jsp");
  }
}
