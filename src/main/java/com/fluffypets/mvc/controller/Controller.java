package com.fluffypets.mvc.controller;

import com.fluffypets.mvc.servlets.Action;
import com.fluffypets.mvc.servlets.ViewModel;

public interface Controller {
    ViewModel process(Action action, ViewModel vm);
}
