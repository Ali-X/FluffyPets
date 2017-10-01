package com.fluffypets.servicies.product;


import com.fluffypets.mvc.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAll();

    List<Product> getAllSelected(String categIds,int min, int max);

    Integer countSelected(String categoryIds, int min, int max, int paginationStep);

    List<Product> selectAndPagination (String categoryIds, int min, int max,String order, Integer paginationMax,Integer pagination);

    Product getProduct(Product product);

    Product getProductById(Integer id);

    Product create(Product product);

    Product delete(Product product);

    Product update(Product product);

}
