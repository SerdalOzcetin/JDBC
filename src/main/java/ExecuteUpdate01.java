import java.sql.*;

public class ExecuteUpdate01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        // 1. step: Register to Driver
        Class.forName("org.postgresql.Driver");
        //"org.postgresql.Driver"

        // 2. step connect the Database
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","12345");
        //"jdbc:postgresql://localhost:5432/SecondTest","postgres","12345"

        // 3. step Create a statement

        Statement st = con.createStatement();

        //Update the "number_of_employees" values that are less than the average number of employees to 16000.

        String sql01 = "UPDATE companies\n" +
                "SET number_of_employees = 16000\n" +
                "WHERE number_of_employees < (SELECT AVG(number_of_employees)\n" +
                "                             FROM companies)";

        int updatedRowNumber = st.executeUpdate(sql01);

        System.out.println(updatedRowNumber);

        ResultSet resultSet = st.executeQuery("SELECT * FROM companies");
        while (resultSet.next()){

            System.out.println(resultSet.getInt(1) + "--" + resultSet.getString(2) + "--" + resultSet.getInt(3));
        }

        con.close();
        st.close();
        resultSet.close();
    }
}
