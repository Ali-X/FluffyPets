package com.fluffypets.mvc.controller;

import com.fluffypets.mvc.servlets.Command;
import com.fluffypets.mvc.servlets.ViewModel;

public interface Controller {
    ViewModel process(Command command, ViewModel vm);
}
