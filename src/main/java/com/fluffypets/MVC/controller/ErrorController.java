package com.fluffypets.MVC.controller;

import com.fluffypets.MVC.modelView.ViewModel;
import com.fluffypets.MVC.servlets.Request;

public class ErrorController implements Controller{
  @Override
  public ViewModel process(Request request) {
    return new ViewModel("/error.jsp")
        .withAttribute("error", request.getParameter("error"));
  }
}
