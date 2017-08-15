package com.fluffypets.servicies;

import com.fluffypets.DAO.product.ProductDAO;
import com.fluffypets.MVC.model.Product;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    private final ProductDAO productDAO;

    public ProductServiceImpl(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public List<Product> getAll(Integer c_id) {
        return productDAO.getAll(c_id);
    }

    @Override
    public Product getProduct(Product product) {
        return productDAO.get(product);
    }

    @Override
    public Product create(Product product) {
        return productDAO.create(product);
    }

    @Override
    public Product delete(Product product) {
        return productDAO.delete(product);
    }

    @Override
    public Product update(Product product) {
        return productDAO.update(product);
    }
}
