import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CountriesTest {
    /*
    Given
        User connects to the database
    When
        User sends the query to the get region ids from "countries" table
    Then
        Verify that the number of the rgion ids grater than 1 is 17
    And
        User closes the connection
     */

    @Test
    public void countryTest() throws SQLException {

        // User connects to the database

        Connection connection = JdbcUtils.connectToDataBase("localhost","postgres","postgres","12345");
        Statement statement = JdbcUtils.createStatement();

        // User sends the query to the get region ids from "countries" table

        String sql01 = "SELECT region_id FROM countries";
        ResultSet resultSet01 = statement.executeQuery(sql01);
        List<Integer> ids = new ArrayList<>();

        while (resultSet01.next()){

            ids.add(resultSet01.getInt(1));

        }
        System.out.println(ids);
        List<Integer> greaterThanOne = new ArrayList<>();
        for (int w : ids){

            if (w>1){
                greaterThanOne.add(w);
            }
        }

        System.out.println(greaterThanOne);

        int actualData = greaterThanOne.size();
        int expectedData = 17;

        Assert.assertEquals(expectedData,actualData);

        JdbcUtils.closeConnectionAndstatement();
    }
}
