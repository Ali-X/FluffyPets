package com.fluffypets.servicies.product;

import com.fluffypets.DAO.product.ProductDAO;
import com.fluffypets.MVC.model.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ProductServiceImpl implements ProductService,AutoCloseable {
    private static final Logger logger = LogManager.getLogger(ProductServiceImpl.class.getName());

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
    public Integer countSelected(String categoryIds, int min, int max,int paginationStep) {
        return productDAO.countSelected(categoryIds,min,max,paginationStep);
    }

    @Override
    public List<Product> selectAndPagination(String categoryIds, int min, int max, String order, Integer paginationStep, Integer pagination) {
        return productDAO.selectAndPagination(categoryIds,min,max,order,paginationStep,pagination);
    }

    @Override
    public Product getProduct(Product product) {
        return productDAO.get(product);
    }

    @Override
    public Product getProductById(Integer id) {
        return productDAO.findById(id);
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

    @Override
    public void close() throws Exception {
        productDAO.close();
    }
}
