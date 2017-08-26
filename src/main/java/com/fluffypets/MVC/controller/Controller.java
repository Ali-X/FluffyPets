package com.fluffypets.MVC.controller;

import com.fluffypets.MVC.servlets.Request;
import com.fluffypets.MVC.servlets.ViewModel;

public interface Controller {
    ViewModel process(Request request);
}
