package com.fluffypets.MVC.controller;

import com.fluffypets.MVC.filtres.AdminFilter;
import com.fluffypets.MVC.servlets.Request;
import com.fluffypets.MVC.servlets.ViewModel;
import com.fluffypets.factory.Factory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

public class ErrorController implements Controller {
    private static final Logger logger = LogManager.getLogger(ErrorController.class.getName());

    @Override
    public ViewModel process(Request request) {
        ViewModel vm = Factory.getViewModel();
        vm.setView("error");
        vm.setAttribute("error", request.getAttribute("error"));
        return vm;
    }
}
