import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MedunnaTest {

    @Test
    public void test01() throws SQLException {
/*

    Given
        User connects to the database
        (hostname : medunna.com, database name : medunna_db, user name : medunna_user, password : medunna_pass_987)

    When
        User sends the query to the get the names of created_by column from "room" table

    Then
        Assert that there are some rooms created_by "john_doe"
    And
        User closes the connection

 */
        // User connects to the database
        Connection connection = JdbcUtils.connectToDataBase("medunna.com","medunna_db","medunna_user","medunna_pass_987");
        Statement statement = JdbcUtils.createStatement();

        //  User sends the query to the get the names of created_by column from "room" table
        String sql01 = "SELECT created_by FROM room";
        ResultSet resultSet01 = statement.executeQuery(sql01);
        List<String> created_by = new ArrayList<>();

        while (resultSet01.next()){

            created_by.add(resultSet01.getString(1));

        }
        System.out.println(created_by);

        Assert.assertTrue(created_by.contains("john_doe"));
    }
}
