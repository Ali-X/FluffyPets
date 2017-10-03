//package com.fluffypets.dao.user;
//
//import com.fluffypets.entities.User;
//import com.fluffypets.entities.UserAddress;
//import com.fluffypets.factory.DaoFactory;
//import org.junit.Test;
//
//import java.time.LocalDate;
//
//import static junit.framework.TestCase.*;
//
//public class TestUserDataDAO {
//    @Test
//    public void testUserDataCRUD(){
//        UserDAO myUsers = DaoFactory.getUserDao();
//        UserAddressDAO myUserData = DaoFactory.getUserDataDao();
//        System.out.println("in Users CRUD test connection is:  \n"+myUsers.printConnectInfo());
//        User dodik = new User(1, "David", "shabat", "DavidShimshilovits@mail.ru", "User");
//        User expectedUser = myUsers.get(dodik);
//        assertNull("User should be absent", expectedUser);
//        dodik=myUsers.create(dodik);    //essential because Dodik Id will change
//
//        UserAddress dodikData=new UserAddress(dodik.getId(),"dodik's full name", LocalDate.now(),"Male",
//                false,"Poltavska oblast","misto Lubnu","vulitsa Litovska 4",
//                "app. 43", "858453421","95673421");
//        UserAddress expectedUserData=myUserData.get(dodikData);
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
//        myUsers.delete(dodik);
//        expectedUser = myUsers.get(dodik);
//        assertNull("User should be absent ", expectedUser);
//    }
//}
