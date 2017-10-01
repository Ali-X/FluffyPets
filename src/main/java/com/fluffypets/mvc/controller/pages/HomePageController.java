package com.fluffypets.mvc.controller.pages;

import com.fluffypets.mvc.controller.Controller;
import com.fluffypets.mvc.model.*;
import com.fluffypets.mvc.model.enumes.Prices;
import com.fluffypets.mvc.model.page_objects.Cart;
import com.fluffypets.mvc.model.page_objects.HomePagePref;
import com.fluffypets.mvc.servlets.Command;
import com.fluffypets.mvc.servlets.ViewModel;
import com.fluffypets.servicies.product.CategoryService;
import com.fluffypets.servicies.product.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.ResourceBundle;
import java.util.StringJoiner;

public class HomePageController implements Controller {
    private static final Logger logger = LogManager.getLogger(HomePageController.class.getName());

    private ProductService productService;
    private CategoryService categoryService;

    public HomePageController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @Override
    public ViewModel process(Command command, ViewModel vm) {

        HomePagePref homePagePref;

        if (vm.getAttribute("homePagePref") == null) {
            List<Category> categories = categoryService.getAll();
            StringJoiner categoryList = new StringJoiner(",");
            categories.forEach(category -> categoryList.add(category.getId().toString()));
            int defaultPaginationStep=8;
            Integer paginationMax = productService.countSelected(categoryList.toString(), 0, Integer.MAX_VALUE,defaultPaginationStep);
            List<Product> products=productService.selectAndPagination(categoryList.toString(),0,Integer.MAX_VALUE,"asc",defaultPaginationStep,1);
            homePagePref = new HomePagePref(categories, "all", products, "asc", paginationMax,defaultPaginationStep, 1);
            vm.setAttribute("categories", categories);
            vm.setAttribute("homePagePref", homePagePref);
        }


        vm.setAttribute("prices", Prices.values());

//        -------------         localization for page home     ----------------------

        ResourceBundle resourceBundle = ResourceBundle.getBundle("language", vm.getCurrentLocale());
        vm.setAttribute("Add_to_cart", ViewModel.stringUTF8(resourceBundle.getString("Add_to_cart")));
        vm.setAttribute("Admin_page", ViewModel.stringUTF8(resourceBundle.getString("Admin_page")));
        vm.setAttribute("Confirm_your_order", ViewModel.stringUTF8(resourceBundle.getString("Confirm_your_order")));
        vm.setAttribute("Create_product", ViewModel.stringUTF8(resourceBundle.getString("Create_product")));
        vm.setAttribute("Logout", ViewModel.stringUTF8(resourceBundle.getString("Logout")));
        vm.setAttribute("My_cart", ViewModel.stringUTF8(resourceBundle.getString("My_cart")));
        vm.setAttribute("My_profile", ViewModel.stringUTF8(resourceBundle.getString("My_profile")));
        vm.setAttribute("Products", ViewModel.stringUTF8(resourceBundle.getString("Products")));
        vm.setAttribute("Select_categories", ViewModel.stringUTF8(resourceBundle.getString("Select_categories")));
        vm.setAttribute("Select_price_range", ViewModel.stringUTF8(resourceBundle.getString("Select_price_range")));
        vm.setAttribute("Signin", ViewModel.stringUTF8(resourceBundle.getString("Signin")));
        vm.setAttribute("Signup", ViewModel.stringUTF8(resourceBundle.getString("Signup")));
        vm.setAttribute("Welcome", ViewModel.stringUTF8(resourceBundle.getString("Welcome")));
        vm.setAttribute("message_L", ViewModel.stringUTF8(resourceBundle.getString("message_L")));
        vm.setAttribute("All", ViewModel.stringUTF8(resourceBundle.getString("All")));
        vm.setAttribute("Language", ViewModel.stringUTF8(resourceBundle.getString("Language")));
        vm.setAttribute("Select", ViewModel.stringUTF8(resourceBundle.getString("Select")));
        vm.setAttribute("OrderLabel", ViewModel.stringUTF8(resourceBundle.getString("OrderLabel")));
        vm.setAttribute("IncreasePrice", ViewModel.stringUTF8(resourceBundle.getString("IncreasePrice")));
        vm.setAttribute("DecreasePrice", ViewModel.stringUTF8(resourceBundle.getString("DecreasePrice")));
//        =============         localization        ======================


        User user = (User) vm.getAttribute("user");
        if (user == null) {
            user = new User(0, "Unknown", "", "", "user");
        }

        Cart cart = (Cart) vm.getAttribute("cart");
        if (cart == null) {
            cart = new Cart(user);
        } else {
            cart.setUser(user);
        }
        vm.setAttribute("cart", cart);
        vm.setView("home");
        logger.info("home page selected");
        return vm;
    }
}
