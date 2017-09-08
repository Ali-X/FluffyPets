package com.fluffypets.servicies;

import com.fluffypets.DAO.product.ProductDAO;
import com.fluffypets.MVC.model.Product;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    private final ProductDAO productDAO;

    public ProductServiceImpl(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public List<Product> getAll() {
        return productDAO.getAll();
    }

    @Override
    public List<Product> getAllSelected(String categIds, int min, int max) {
        return productDAO.selectByCategoryAndPrice(categIds,min,max);
    }

    @Override
    public Product getProduct(Product product) {
        return productDAO.get(product);
    }

    @Override
    public Product create(Product product) {
        Product existing=productDAO.get(product);
        if (existing==null) return productDAO.create(product);
        else {return existing;}
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
