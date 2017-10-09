package com.fluffypets.mvc.controller.post;

import com.fluffypets.entities.User;
import com.fluffypets.factory.ContextFactory;
import com.fluffypets.mvc.page_objects.ValidationMessage;
import com.fluffypets.mvc.servlets.Action;
import com.fluffypets.mvc.servlets.ViewModel;
import com.fluffypets.services.UserDataService;
import com.fluffypets.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.mockito.AdditionalMatchers;
import org.mockito.Matchers;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

public class LoginCheckControllerTest {
    private static final Logger logger = spy(LogManager.getLogger(LoginCheckController.class.getName()));
    private UserService userService = mock(UserService.class);
    private UserDataService userDataService = mock(UserDataService.class);
    private Action action = new Action("POST", "/root/login", new HashMap<>());
    private ViewModel vm = new ViewModel();

    @Before
    public void setUp() {
        User user = new User(21, "StevenSigal", ContextFactory.md5Custom("somePassword", logger),
                "stev@gmail.com", "user");
        vm.setAttribute("user", null);
        vm.setAttribute("validationUser", null);
        vm.setView("login");

        when(userService.findUser(user.getUserName(), user.getPassword())).thenReturn(user).thenReturn(null);
        when(userDataService.get(user.getId())).thenReturn(null);

    }

    @Test
    public void testValidUser() {
        String[] userName = new String[]{"StevenSigal"};
        String[] pass = new String[]{"somePassword"};

        LoginCheckController loginCheckController = new LoginCheckController(userService, userDataService);
        action.setAttribute("userName", userName);
        action.setAttribute("password", pass);
        vm = loginCheckController.process(action, vm);

        User user = (User) vm.getAttribute("user");
        assertEquals(user.getUserName(), userName[0]);
        assertEquals(vm.getView(), "home");
    }

    @Test
    public void testInvalidPassword() {
        String[] userName = new String[]{"StevenSigal"};
        String[] pass = new String[]{"jopa"};

        LoginCheckController loginCheckController = new LoginCheckController(userService, userDataService);
        action.setAttribute("userName", userName);
        action.setAttribute("password", pass);
        vm = loginCheckController.process(action, vm);

        User user = (User) vm.getAttribute("user");
        ValidationMessage<User> userValidationMessage = (ValidationMessage<User>) vm.getAttribute("validationUser");
        assertNull(user);
        assertEquals(vm.getView(), "login");
        assertEquals(userValidationMessage.getValidationMessage(), "user&password pair is absent");
    }

}