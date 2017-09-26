package com.fluffypets.MVC.controller.post;

import com.fluffypets.MVC.controller.Controller;
import com.fluffypets.MVC.model.Category;
import com.fluffypets.MVC.model.HomePagePref;
import com.fluffypets.MVC.model.Product;
import com.fluffypets.MVC.model.enumes.Prices;
import com.fluffypets.MVC.servlets.Request;
import com.fluffypets.MVC.servlets.ViewModel;
import com.fluffypets.servicies.CategoryService;
import com.fluffypets.servicies.ProductService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class SelectGoodsController implements Controller, AutoCloseable {

    private ProductService productService;
    private CategoryService categoryService;

    public SelectGoodsController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @Override
    public ViewModel process(Request request, ViewModel vm) {
        HomePagePref homePagePref = (HomePagePref) vm.getAttribute("homePagePref");
        StringJoiner sj = new StringJoiner(",");
        String formN = request.getAttribute("formN");

        if (formN.equals("1")) {
            String order = request.getAttribute("order");

            List<Category> categories = categoryService.getAll();

            categories = categories.stream().filter(category -> request.containsAtribute(category.getName())).collect(Collectors.toList());
            categories.stream().map(Category::getId).forEach(categId -> sj.add(categId.toString()));

            if(sj.toString().equals(""))categories.forEach(category -> sj.add(category.getId().toString()));
            String priceLabel = request.getAttribute("selectedPrice");

            Optional<Prices> priceSel = Arrays.stream(Prices.values()).filter(value -> value.getLabel().equals(priceLabel)).findAny();

            Integer paginationMax = productService.countSelected(sj.toString(), priceSel.get().getMin(), priceSel.get().getMax(),homePagePref.getPaginationStep());
            List<Product> products = productService.selectAndPagination(sj.toString(), priceSel.get().getMin(), priceSel.get().getMax(), order, homePagePref.getPaginationStep(), 1);

            homePagePref = new HomePagePref(categories, priceSel.get().getLabel(), products, order, paginationMax, homePagePref.getPaginationStep(), homePagePref.getPagination());
            vm.setAttribute("homePagePref", homePagePref);
        } else {
            Integer pagination = new Integer(request.getAttribute("pagination"));
            homePagePref.setPagination(pagination);
            homePagePref.getCategoryList().forEach(category -> sj.add(category.getId().toString()));
            String price = homePagePref.getPrice();
            Prices priceSel = Arrays.stream(Prices.values()).filter(value -> value.getLabel().equals(price)).findAny().get();

            Integer paginationMax = productService.countSelected(sj.toString(), priceSel.getMin(), priceSel.getMax(),homePagePref.getPaginationStep());
            List<Product> products = productService.selectAndPagination(sj.toString(), priceSel.getMin(), priceSel.getMax(),
                    homePagePref.getOrder(),homePagePref.getPaginationStep() , pagination);
            homePagePref.setProducts(products);
            homePagePref.setPaginationMax(paginationMax);
            vm.setAttribute("homePagePref", homePagePref);
        }

        vm.setView("home");
        return vm;
    }

    @Override
    public void close() throws Exception {
        productService.close();
        categoryService.close();
    }
}