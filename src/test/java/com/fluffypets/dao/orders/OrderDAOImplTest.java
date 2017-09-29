package com.fluffypets.dao.orders;

import com.fluffypets.dao.category.CategoryDAO;
import com.fluffypets.dao.product.ProductDAO;
import com.fluffypets.dao.user.UserDAO;
import com.fluffypets.mvc.model.*;
import com.fluffypets.factory.DaoFactory;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNotNull;

public class OrderDAOImplTest {
    @Test
    public void testOrderCRUD() {
        OrderDAO myOrders = DaoFactory.getOrderDAO();
        UserDAO myUsers = DaoFactory.getUserDao();
        CategoryDAO myCategories = DaoFactory.getCategoryDao();
        ProductDAO myProducts=DaoFactory.getProductDao();

        User dodik = new User(1, "David", "shabat", "DavidShimshilovits@mail.ru", "User");
        dodik = testCreateUser(myUsers, dodik);

        Category plushBears = new Category(1, "Plussssh bears", "plush toy it should be bear");
        Category plushDisney = new Category(2, "Plussssh W. Disney Cartons", "plush toy it should from carton");

        plushBears = testCreateCategory(myCategories, plushBears);
        plushDisney = testCreateCategory(myCategories, plushDisney);

        Product plushPanda = new Product(1, "plusssssh Panda", "Chingen Panda Corp.", new BigDecimal("99.99"), " nice 9 inch toll panda", "someURL", plushBears);
        Product plushIceBear = new Product(1, "plusssssh ice bear", "Chingen Panda Corp.", new BigDecimal("89.99"), " nice 8 inch toll bear", "someURL2", plushBears);
        Product plushMickyMouse = new Product(1, "plusssssh Micky Mouse", "Canton MakeAll Corp.", new BigDecimal("129.99"), " nice 7 inch toll Micky", "someURL2", plushDisney);

        plushPanda = testCreateProduct(myProducts, plushPanda);
        plushIceBear = testCreateProduct(myProducts, plushIceBear);
        plushMickyMouse = testCreateProduct(myProducts, plushMickyMouse);

        Order dodiksOrder = new Order(dodik.getId(), LocalDate.now().minusMonths(1), LocalDate.now(),"delivered", null, "deliver at evening");
        List<OrderItem> orderItemList = new ArrayList<>();
        OrderItem item1=new OrderItem(plushPanda.getId(),dodiksOrder.getUserId(),2,plushPanda.getPrice());
        OrderItem item2=new OrderItem(plushMickyMouse.getId(),dodiksOrder.getUserId(),3,plushMickyMouse.getPrice());
        OrderItem item3=new OrderItem(plushIceBear.getId(),dodiksOrder.getUserId(),1,plushIceBear.getPrice());
        orderItemList.add(item1);
        orderItemList.add(item2);
        orderItemList.add(item3);
        dodiksOrder.setItems(orderItemList);

        dodiksOrder=testProductCreation(myOrders, dodiksOrder);

        checkOrderContainProduct(dodik, plushPanda, dodiksOrder);
        checkOrderContainProduct(dodik, plushIceBear, dodiksOrder);
        checkOrderContainProduct(dodik, plushMickyMouse, dodiksOrder);

        testDeleteOrder(myOrders, dodiksOrder);

        myProducts.delete(plushIceBear);
        myProducts.delete(plushPanda);
        myProducts.delete(plushMickyMouse);

        myCategories.delete(plushBears);
        myCategories.delete(plushDisney);

        testDeleteUser(myUsers, dodik);
    }

    private void testDeleteOrder(OrderDAO myOrders, Order dodiksOrder) {
        assertNotNull("Dodi's order should exist",dodiksOrder);
        myOrders.delete(dodiksOrder);
        Order expectedOrder = myOrders.get(dodiksOrder);
        assertNull("Order should be absent", expectedOrder);
    }

    private void checkOrderContainProduct (User dodik, Product plushPanda, Order dodiksOrder) {
        assertNotNull("There is order", dodiksOrder);
        assertEquals("Order is make by David",dodik.getId(), dodiksOrder.getUserId());
        assertEquals("Order containes 3 positions",3, dodiksOrder.getItems().size());
        Boolean containedPanda=dodiksOrder.getItems().stream().anyMatch(orderItem -> orderItem.getProductId()==plushPanda.getId());
        assertTrue("Order containes panda",containedPanda);
    }

    private Order testProductCreation(OrderDAO myOrders, Order dodiksOrder) {
        Order expectedOrder=myOrders.get(dodiksOrder);
        assertNull("Order should be absent", expectedOrder);
        dodiksOrder=myOrders.create(dodiksOrder);
        assertNotNull("Order should be absent", dodiksOrder);
        return dodiksOrder;
    }

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