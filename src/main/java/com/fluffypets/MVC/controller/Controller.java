package com.fluffypets.MVC.controller;

import com.fluffypets.MVC.servlets.Request;
import com.fluffypets.MVC.servlets.ViewModel;

public interface Controller extends AutoCloseable {
    ViewModel process(Request request);
}
