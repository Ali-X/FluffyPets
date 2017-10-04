//package com.fluffypets.dao.category;
//
//
//import com.fluffypets.entities.Category;
//import com.fluffypets.dao.impl.DaoFactory;
//import org.junit.Test;
//
//import static junit.framework.TestCase.*;
//
//public class CategoryDAOImplTest {
//    @Test
//    public void testCategoriesCRUD(){
//        CategoryDAO categoryDAO= DaoFactory.getCategoryDao();
//        Category teddyBear =new Category(1,"teddy bear","brown bear 14 inch");
//        Category expectedCategory=categoryDAO.get(teddyBear);
//        assertNull("in base should not be this record",expectedCategory);
//        teddyBear =categoryDAO.create(teddyBear);//getting real id
//        expectedCategory=categoryDAO.get(teddyBear);
//        assertEquals("it should be the same category",expectedCategory,teddyBear);
//        categoryDAO.delete(teddyBear);
//        expectedCategory=categoryDAO.get(teddyBear);
//        assertNull("in base should not be this record",expectedCategory);
//    }
//}