import java.sql.*;

public class ExecuteQuery01 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        // 1. step: Register to Driver
        Class.forName("org.postgresql.Driver");
        //"org.postgresql.Driver"

        // 2. step connect the Database
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","12345");
        //"jdbc:postgresql://localhost:5432/SecondTest","postgres","12345"

        // 3. step Create a statement
        Statement st = con.createStatement();

        // Query the countrıes wırh region_id=1
        String sql01 = "SELECT country_name FROM countries WHERE region_id =1";

        //SELECT country_name
        //FROM countries
        //WHERE region_id =1

        boolean r01 = st.execute(sql01);
        System.out.println("r01 = "+r01);

        //to see the result of the datas we need to use ExecuteQuery() methodu kullanmalıyız.
       ResultSet resultSet01 = st.executeQuery(sql01);

       while (resultSet01.next()){
           System.out.println(resultSet01.getString(1));
       }

        System.out.println("------------------------------------------------------------");

       //Query country_id ve country_name with region_id hgher than 2
        String sql02 = "SELECT country_name, country_id FROM countries WHERE region_id > 1";
        ResultSet resultSet02 = st.executeQuery(sql02);

        while (resultSet02.next()){
            System.out.println(resultSet02.getString("country_name")+"--"+resultSet02.getString("country_id"));

        }

        System.out.println("------------------------------------------------------------");


        //query all rows wıth the lowest number_of_employees

        String sql03 = "SELECT* FROM companies WHERE number_of_employees = (SELECT MIN(number_of_employees) FROM companies)";
        ResultSet resultSet03 = st.executeQuery(sql03);

        while (resultSet03.next()){
            System.out.println(resultSet03.getInt(1)+"--"+resultSet03.getString(2)+"--"+resultSet03.getInt(3));

        }

        con.close();
        st.close();

    }
}
