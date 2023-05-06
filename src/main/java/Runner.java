import java.sql.Connection;
import java.sql.Statement;

public class Runner {
    public static void main(String[] args) {
        Connection connection = JdbcUtils.connectToDataBase("localhost","postgres","postgres","12345");
        Statement statement = JdbcUtils.createStatement();

        //JdbcUtils.execute("CREATE TABLE students(name VARCHAR(20), id INT, address VARCHAR(80))");
        JdbcUtils.createTable("abcdef","classes VARCHAR(20)","teacherName VARCHAR(20)", "id INT");
        JdbcUtils.closeConnectionAndstatement();
    }
}
