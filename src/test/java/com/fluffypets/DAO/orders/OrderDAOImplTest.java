package com.fluffypets.DAO.orders;

import com.fluffypets.DAO.category.CategoryDAO;
import com.fluffypets.DAO.product.ProductDAO;
import com.fluffypets.DAO.user.UserDAO;
import com.fluffypets.MVC.model.*;
import com.fluffypets.factory.Factory;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertNotNull;

public class OrderDAOImplTest {
    @Test
    public void testOrderCRUD() {
        OrderDAO myOrders = Factory.getOrderDAO();
        OrderItemDAO myOrderItems = Factory.getOrderItemDAO();
        UserDAO myUsers = Factory.getUserDao();
        CategoryDAO myCategories = Factory.getCategoryDao();
        ProductDAO myProducts=Factory.getProductDao();

        User dodik = new User(1, "David", "shabat", "Davids_token_123456","DavidShimshilovits@mail.ru", "User");
        dodik = testCreateUser(myUsers, dodik);

        Category plushBears = new Category(1, "Plussssh bears", "plush toy it should be bear");
        Category plushDisney = new Category(2, "Plussssh W. Disney Cartons", "plush toy it should from carton");

        plushBears = testCreateCategory(myCategories, plushBears);
        plushDisney = testCreateCategory(myCategories, plushDisney);

        Product plushPanda = new Product(1, "plusssssh Panda", "Chingen Panda Corp.", new BigDecimal(99.99), " nice 9 inch toll panda", "someURL", plushBears);
        Product plushIceBear = new Product(1, "plusssssh ice bear", "Chingen Panda Corp.", new BigDecimal(89.99), " nice 8 inch toll bear", "someURL2", plushBears);
        Product plushMickyMouse = new Product(1, "plusssssh Micky Mouse", "Canton MakeAll Corp.", new BigDecimal(129.99), " nice 7 inch toll Micky", "someURL2", plushDisney);

        plushPanda = testCreateProduct(myProducts, plushPanda);
        plushIceBear = testCreateProduct(myProducts, plushIceBear);
        plushMickyMouse = testCreateProduct(myProducts, plushMickyMouse);

        Order dodiksOrder = new Order(dodik.getId(), LocalDate.now().minusMonths(1), LocalDate.now(),
                "delivered", null, "deliver at evening");

        dodiksOrder = testCreateOrder(myOrders, dodiksOrder);

        List<OrderItem> orderItemList = new ArrayList<>();

        OrderItem item1=new OrderItem(plushPanda.getId(),dodiksOrder.getUserId(),2,plushPanda.getPrice());
        item1 = testCreateItem(myOrderItems, item1);
        orderItemList.add(item1);

        OrderItem item2=new OrderItem(plushMickyMouse.getId(),dodiksOrder.getUserId(),3,plushMickyMouse.getPrice());
        item2 = testCreateItem(myOrderItems, item2);
        orderItemList.add(item2);

        OrderItem item3=new OrderItem(plushIceBear.getId(),dodiksOrder.getUserId(),1,plushIceBear.getPrice());
        item3 = testCreateItem(myOrderItems, item3);
        orderItemList.add(item3);

        dodiksOrder.setItems(orderItemList);
        //todo: method not finished, doesnt work
//        UserData dodikData=new UserData(dodik.getId().longValue(),"dodik's full name", LocalDate.now(),"Male",
//                false,"Poltavska oblast","misto Lubnu","vulitsa Litovska 4",
//                "app. 43", "858453421","95673421");
//        UserData expectedUserData=myUserData.get(dodikData);
//        assertNull("User data should be absent", expectedUserData);
//        dodikData=myUserData.create(dodikData);
//        expectedUserData=myUserData.get(dodikData);
//        assertNotNull("User data should be present", expectedUserData);
//
//        dodikData.setSecondaryNumber("222222");
//        dodikData=myUserData.update(dodikData);
//        assertEquals("222222",dodikData.getSecondaryNumber());
//
//        myUserData.delete(dodikData);
//        expectedUserData = myUserData.get(dodikData);
//        assertNull("User data should be absent", expectedUserData);
//
        testDeleteUser(myUsers, dodik);
    }
//todo: check all messages
    private OrderItem testCreateItem(OrderItemDAO myOrderItems, OrderItem item1) {
        OrderItem expectedOrderItem=myOrderItems.get(item1);
        assertNull("User should be absent", expectedOrderItem);
        item1=myOrderItems.create(item1);
        assertNotNull("User should be absent", item1);
        return item1;
    }

    private Order testCreateOrder(OrderDAO myOrders, Order dodiksOrder) {
        Order expectedOrder=myOrders.get(dodiksOrder);
        assertNull("User should be absent", expectedOrder);
        dodiksOrder=myOrders.create(dodiksOrder);
        assertNotNull("User should be present", dodiksOrder);
        return dodiksOrder;
    }

    private Product testCreateProduct(ProductDAO myProducts, Product plushPanda) {
        Product expectedProduct = myProducts.get(plushPanda);
        assertNull("this testing product should be absent", expectedProduct);
        plushPanda = myProducts.create(plushPanda);
        assertNotNull("testing product was not created", plushPanda);
        return plushPanda;
    }

    private Category testCreateCategory(CategoryDAO myCategories, Category testCategory) {
        Category expectedCategory = myCategories.get(testCategory);
        assertNull("this testing category should be absent", expectedCategory);
        testCategory = myCategories.create(testCategory);
        assertNotNull("this testing category should be absent", testCategory);
        return testCategory;
    }

    private void testDeleteUser(UserDAO myUsers, User dodik) {
        assertNotNull("User should be present", dodik);
        myUsers.delete(dodik);
        User expectedUser = myUsers.get(dodik);
        assertNull("User should be absent ", expectedUser);
    }

    private User testCreateUser(UserDAO myUsers, User testUser) {
        User expectedUser = myUsers.get(testUser);
        assertNull("User should be absent", expectedUser);
        testUser = myUsers.create(testUser);    //essential because Dodik Id will change
        assertNotNull(testUser);
        return testUser;
    }
}