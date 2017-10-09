package com.fluffypets.mvc.controller.post;

import com.fluffypets.mvc.controller.Controller;
import com.fluffypets.entities.Category;
import com.fluffypets.entities.Product;
import com.fluffypets.entities.User;
import com.fluffypets.mvc.page_objects.CreateProductPref;
import com.fluffypets.mvc.servlets.Action;
import com.fluffypets.mvc.servlets.ViewModel;
import com.fluffypets.services.CategoryService;
import com.fluffypets.services.ProductService;

import java.math.BigDecimal;

public class CreateProductController implements Controller {


    private ProductService productService;
    private CategoryService categoryService;

    public CreateProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @Override
    public ViewModel process(Action action, ViewModel vm) {
        User user = (User) vm.getAttribute("user");
        if (user == null) {
            vm.setView("login");
        } else {
            CreateProductPref createProductPref=new CreateProductPref(action.getAttribute("productName"),
                    action.getAttribute("producer"),
                    action.getAttribute("description"),
                    action.getAttribute("pictureURL"),
                    action.getAttribute("price"),
                    action.getAttribute("categorySelId"));

            String productName = createProductPref.getProductName();
            String producer = createProductPref.getProducer();
            String description = createProductPref.getDescription();
            String pictureURL = createProductPref.getPictureURL();
            BigDecimal price = new BigDecimal(createProductPref.getPrice());
            Integer categoryName = new Integer(createProductPref.getCategorySelId());
            Category category = categoryService.get(categoryName);
            vm.setAttribute("currentCategory",category);
            vm.setAttribute("createProductPref",createProductPref);
            vm.setView("createProduct");

            Product product = new Product(productName, producer, price, description, pictureURL, category);

            productService.create(product);
        }
        vm.setView("createProduct");
        return vm;
    }
}