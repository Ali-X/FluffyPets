package com.fluffypets.DAO.product;


import com.fluffypets.DAO.GenericDAO;
import com.fluffypets.MVC.model.Product;

import java.util.List;

public interface ProductDAO extends GenericDAO<Product> {

    List<Product> getAll(Integer c_id);

    Product getProduct(Integer p_id);

    Product create(String name, String description, String c_name);

    Product delete(Product item);

    Product update(String old_name, String new_name, String new_descr, String c_name);
}
