import java.sql.*;

public class ExecuteQuery02 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // 1. step: Register to Driver
        Class.forName("org.postgresql.Driver");
        //"org.postgresql.Driver"

        // 2. step connect the Database
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","12345");
        //"jdbc:postgresql://localhost:5432/SecondTest","postgres","12345"

        // 3. step Create a statement

        Statement st = con.createStatement();
        //1.way
        String sql01 = "SELECT company, number_of_employees FROM companies ORDER BY number_of_employees DESC OFFSET 1 ROW FETCH NEXT 1 ROW ONLY";
        ResultSet resultSet = st.executeQuery(sql01);

        while (resultSet.next()){

            System.out.println(resultSet.getString("company")+"--"+resultSet.getInt("number_of_employees"));

        }

        // 2. way

        String sql02 = "SELECT company, number_of_employees\n" +
                "FROM companies\n" +
                "WHERE number_of_employees = (SELECT MAX(number_of_employees)\n" +
                "FROM companies\n" +
                "WHERE number_of_employees < (SELECT MAX(number_of_employees) FROM companies))";
        ResultSet resultSet02 = st.executeQuery(sql02);

        while (resultSet02.next()){

            System.out.println(resultSet02.getString("company")+"--"+resultSet02.getInt("number_of_employees"));

        }
        con.close();
        st.close();
    }
}
