import java.sql.*;

public class Preparedsatement01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","12345");
        Statement st = con.createStatement();

        //Update the "number_of_employees" value to 9999 for the company with the name "IBM" using a prepared statement.

        // 1. step create PreparedStatement Query
        String sql01 = "UPDATE companies SET number_of_employees = ? WHERE company = ? ";


        // 2. Create PreparedStatement object
        PreparedStatement pst01 = con.prepareStatement(sql01);

        // 3. wıth setInt(), setString()... methods ınsert the values ınto the question mark
        pst01.setInt(1,9999);
        pst01.setString(2,"IBM");

        // 4. run the query
        int updatedRowNumber = pst01.executeUpdate();
        System.out.println(updatedRowNumber);


        String sql02 = "SELECT*FROM companies";

        ResultSet resultSet = st.executeQuery(sql02);

        while (resultSet.next()){
            System.out.println(resultSet.getInt(1) + "--" + resultSet.getString(2) + "--" + resultSet.getInt(3));
        }

        System.out.println("--------------------------------------------------------------------------------------------");
        // update the number_of_employeee value wıth the name of comapony 'GOOGLE' as 5555

        pst01.setInt(1,5555);
        pst01.setString(2,"GOOGLE");

        int updatedRowNumber02 = pst01.executeUpdate();
        System.out.println(updatedRowNumber02);

        ResultSet resultSet02 = st.executeQuery(sql02);

        while (resultSet02.next()){
            System.out.println(resultSet02.getInt(1) + "--" + resultSet02.getString(2) + "--" + resultSet02.getInt(3));
        }

        con.close();
        st.close();
        resultSet.close();
        resultSet02.close();

    }
}
