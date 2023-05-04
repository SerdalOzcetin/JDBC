import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Execute01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // 1. step: Register to Driver
        Class.forName("org.postgresql.Driver");
                                //"org.postgresql.Driver"
        // 2. step connect the Database
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","12345");
                                                        //"jdbc:postgresql://localhost:5432/SecondTest","postgres","12345"
        // 3. step Create a statement
        Statement st = con.createStatement();

        // 4. step run the query

        /*
        execute() method can be used for DDL(create, drop, alter) and DQL(select)
        1) ıf execute() method ıs used for DDL ıt returns "false".
        2) ıf execute() method ıs used for DQL when ResultSet ıs receıved returns "true" otherwose "false"


         */
        // example 1 : create a table named "workers" and add "worker_id, worker_salary, worker_name" columns

        boolean sql1 = st.execute("CREATE TABLE workers (worker_id VARCHAR(20), worker_name VARCHAR(20), worker_salary INT)");
        System.out.println(sql1);// returns "false" because we dont call data

        // example 2 : alter the table adding worker_address
        String sql02  = "ALTER TABLE workers ADD worker_address VARCHAR(80)";
        st.execute(sql02);

        // example 2 : Drop the worker table
        String sql03 = "DROP TABLE workers";
        st.execute(sql03);


        // 5. step close the connection and statement


    }
}
