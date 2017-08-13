package testsDAO;

import com.fluffypets.DAO.user.UserDAO;
import com.fluffypets.MVC.model.User;
import com.fluffypets.factory.Factory;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;

public class TestUserDAO {

    @Test
    public void testTableUsersCRUD() {
        UserDAO myUsers = Factory.getUserDao();
        System.out.println("in Users CRUD test connection is:  \n"+myUsers.printConnectInfo());
        User dodik = new User(1, "David", "shabat",
                "Davids_token_123456", "DavidShimshilovits@mail.ru", "User");
        User expectedUser = myUsers.get(dodik);
        assertNull("User should be absent ", expectedUser);
        dodik=myUsers.create(dodik);    //essential because Dodik Id will change
        expectedUser = myUsers.get(dodik);
        assertEquals("User should be present ", dodik, expectedUser);
        String token="Net_Etot_sovsem_drugoi";
        dodik.setToken(token);
        myUsers.update(dodik);
        expectedUser=myUsers.findByToken(token);
        myUsers.delete(expectedUser);
        expectedUser = myUsers.get(dodik);
        assertNull("User should be absent ", expectedUser);
        int idOfPrevious = expectedUser.getId();
    }
}
