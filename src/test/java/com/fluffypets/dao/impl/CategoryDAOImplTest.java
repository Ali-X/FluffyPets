package com.fluffypets.dao.impl;

import com.fluffypets.dao.CategoryDAO;
import com.fluffypets.entities.Category;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class CategoryDAOImplTest {
   private Connection connection;

    @BeforeClass
    public void openConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Pets","root", "nicolas");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("connection problem");
        }
    }

    @Test
    public void testCategoriesCRUD() {
        CategoryDAO categoryDAO = DaoFactory.getCategoryDAO(connection);
        Category teddyBear = new Category(1, "teddy bear", "brown bear 14 inch");
        Category expectedCategory = categoryDAO.get(teddyBear);
        assertNull("in base should not be this record", expectedCategory);
        teddyBear = categoryDAO.create(teddyBear);//getting real id
        expectedCategory = categoryDAO.get(teddyBear);
        assertEquals("it should be the same category", expectedCategory, teddyBear);
        categoryDAO.delete(teddyBear);
        expectedCategory = categoryDAO.get(teddyBear);
        assertNull("in base should not be this record", expectedCategory);
    }

    @AfterClass
    public void closeConnection() {

    }
}