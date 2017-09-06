package com.fluffypets.DAO.category;


import com.fluffypets.MVC.model.Category;
import com.fluffypets.factory.Factory;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;

public class CategoryDAOImplTest {
    @Test
    public void testCategoriesCRUD(){
        CategoryDAO categoryDAO= Factory.getCategoryDao();
        Category teddyBear =new Category(1,"teddy bear","brown bear 14 inch");
        Category expectedCategory=categoryDAO.get(teddyBear);
        assertNull("in base should not be this record",expectedCategory);
        teddyBear =categoryDAO.create(teddyBear);//getting real id
        expectedCategory=categoryDAO.get(teddyBear);
        assertEquals("it should be the same category",expectedCategory,teddyBear);
        categoryDAO.delete(teddyBear);
        expectedCategory=categoryDAO.get(teddyBear);
        assertNull("in base should not be this record",expectedCategory);
    }
}