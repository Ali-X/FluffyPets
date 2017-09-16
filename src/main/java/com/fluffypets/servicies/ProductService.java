package com.fluffypets.servicies;


import com.fluffypets.MVC.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAll();

    List<Product> getAllSelected(String categIds,int min, int max);

    Product getProduct(Product product);

    Product getProductById(Integer id);

    Product create(Product product);

    Product delete(Product product);

    Product update(Product product);

}
