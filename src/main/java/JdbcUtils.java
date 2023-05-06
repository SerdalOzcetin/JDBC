import java.sql.*;

public class JdbcUtils {
    public static void main(String[] args) {
        createTable("abc","name VARCHAR(10)","id INT","address VARCHAR(80)");
    }
    private static Connection connection;
    private static Statement statement;

    public static Connection connectToDataBase(String hostName, String dataBaseName, String userName, String password) {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://"+hostName+":5432/"+dataBaseName,userName,password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (connection!=null){
            System.out.println("Connection Success!");
        }else System.out.println("Connection Fail!");


        return connection;
    }

    public static Statement createStatement() {
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return statement;
    }

    public static boolean execute(String sql){
        boolean isExecute;
        try {
           isExecute = statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    return isExecute;
    }

    public static void closeConnectionAndstatement(){

        try {
            connection.close();
            statement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            if (connection.isClosed()&& statement.isClosed()){
                System.out.println("Connection and Statement Closed!");
            }
            else System.out.println("Connection and Statement Not Closed!");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    //CreatingTableMethod
    public static void createTable(String tableName,String...ColumnName_DataType){
        
        StringBuilder ColumnName_DataValue = new StringBuilder("");

        for (String w:ColumnName_DataType) {

            ColumnName_DataValue.append(w).append(",");

        }
        ColumnName_DataValue.deleteCharAt(ColumnName_DataValue.length()-1);
        System.out.println(ColumnName_DataValue);


        try {
            statement.execute("CREATE TABLE "+tableName+"("+ColumnName_DataValue+")");
            System.out.println("Table "+tableName+" has been created!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
