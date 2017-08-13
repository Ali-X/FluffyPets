package com.fluffypets.DAO.product;


import com.fluffypets.DAO.GenericDAO;
import com.fluffypets.MVC.model.Product;

import java.util.List;

public interface ProductDAO extends GenericDAO<Product> {

    List<Product> getAll(Integer c_id);

}
