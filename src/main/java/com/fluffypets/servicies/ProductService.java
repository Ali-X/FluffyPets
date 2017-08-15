package com.fluffypets.servicies;


import com.fluffypets.MVC.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAll(Integer id);

    Product getProduct(Product product);

    Product create(Product product);

    Product delete(Product product);

    Product update(Product product);

}
