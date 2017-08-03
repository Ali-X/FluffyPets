package com.fluffypets.MVC.controller;

import com.fluffypets.MVC.modelView.ViewModel;
import com.fluffypets.MVC.servlets.Request;

public interface Controller {
    ViewModel process(Request request);
}
