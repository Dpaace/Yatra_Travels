

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class DatabaseOperationTest {

    @org.junit.Test
    public void insert() {
        String first_name = "Priya Shrestha";
        String password = "home";
        DatabaseOperation db = new DatabaseOperation();
        String query = "Insert into register (name, password) values('" + first_name + "','" + password + "')";
        int row = db.insert(query);
        assertEquals(1, row);
    }



    @org.junit.Test
    public void select()throws SQLException {
        String result;
        String first_name = "Nilam";
        DatabaseOperation db = new DatabaseOperation();
        String query = "Select name from register where name='Riya'";
        ResultSet rs = db.select(query);
        while (rs.next()) {
            result = rs.getString("name");
            assertEquals(first_name, result);
        }
    }

    @org.junit.Test
    public void update() {
        DatabaseOperation db = new DatabaseOperation();
        String query = "Update register SET name= 'JUnit_Test' where  name= 'u'";
        int row = db.Update(query);
        assertEquals(1, row);
    }

    @org.junit.Test
    public void delete() {
        DatabaseOperation db = new DatabaseOperation();
        String query = "Delete from addDriver where driverID=  '123'";
        int row = db.executeDelete(query);
        assertEquals(0, row);
    }
}
