package com.fluffypets.MVC.controller.post;

import com.fluffypets.MVC.controller.Controller;
import com.fluffypets.MVC.model.User;
import com.fluffypets.MVC.servlets.Request;
import com.fluffypets.MVC.servlets.ViewModel;
import com.fluffypets.factory.ContextFactory;
import com.fluffypets.servicies.email.SendEmailService;
import com.fluffypets.servicies.user.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SendForgotPasword implements Controller, AutoCloseable {
    private static final Logger logger = LogManager.getLogger(SendForgotPasword.class.getName());

    private UserService userService;
    private SendEmailService sendEmailService;

    public SendForgotPasword(UserService userService, SendEmailService sendEmailService) {
        this.userService = userService;
        this.sendEmailService = sendEmailService;
    }

    @Override
    public ViewModel process(Request request, ViewModel vm) {
        String email = request.getAttribute("email");
        String hostPort = (String) vm.getAttribute("hostPort");
        User user = userService.findByEmail(email);
        String who = "who="+ ContextFactory.md5Custom(user.getId().toString(),logger);
        String verify = "verify="+ ContextFactory.md5Custom(user.getPassword()+user.getRoleString(),logger);
        String subject = "FluffyPets password recovering";
        String content = "Dear costumer, if you are trying to recover your password you should follow next link \n" +
                hostPort+"/root/recoverPassword"+"?"+who+"&"+verify+"&email="+email+"   \n" +
                "In opposite case ignore this letter.\n";
        if (!sendEmailService.sendEmailTo(email, subject, content))
            logger.error("letter was not sent");

        vm.setView("home");
        return vm;
    }

    @Override
    public void close() throws Exception {
        userService.close();
    }
}
