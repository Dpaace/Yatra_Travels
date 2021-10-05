import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void Username() {
        String name = "Samita";
        User user = new User();
        user.setUsername("Samita");
        String value = user.getUsername();
        assertEquals(name,value);
    }

    @Test
    public void MobileNumber() {
        String name = "9833";
        User user = new User();
        user.setMobileNumber("0039");
        String value = user.getMobileNumber();
        assertEquals(name,value);
    }


    @Test
    public void UserEmail() {
        String name = "dawa@gmail.com";
        User user = new User();
        user.setUserEmail("prashid@gmail.com");
        String value = user.getUserEmail();
        assertEquals(name,value);
    }

    @Test
    public void UserGender() {
        String name = "female";
        User user = new User();
        user.setUserGender("male");
        String value = user.getUserGender();
        assertEquals(name,value);
    }

    @Test
    public void getUserDob() {
        String name = "10 march";
        User user = new User();
        user.setUserDob("11 march");
        String value = user.getUserDob();
        assertEquals(name,value);
    }

    @Test
    public void getUserAddress() {
        String name = "Dhapakhel";
        User user = new User();
        user.setUserAddress("pokhara");
        String value = user.getUserAddress();
        assertEquals(name,value);
    }

    @Test
    public void getPassword1() {
        String name = "depacce";
        User user = new User();
        user.setPassword1("depace12");
        String value = user.getPassword1();
        assertEquals(name,value);
    }

    @Test
    public void getPassword2() {
        String name = "depacce";
        User user = new User();
        user.setPassword2("depace12");
        String value = user.getPassword2();
        assertEquals(name,value);
    }
}