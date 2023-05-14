import java.sql.*;

public class CallableStatement01 {


    //ıf ıt returns data that ıs called "function", otherwise "procedure".

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","12345");
        Statement st = con.createStatement();

        // wıth the  Callablestatement we parametrize the calling function.

        // 1. step create teh function
        String sql01 = "CREATE OR REPLACE FUNCTION addFunction(x NUMERIC, y NUMERIC)\n" +
                "RETURNS NUMERIC\n" +
                "LANGUAGE plpgsql\n" +
                "AS\n" +
                "$$\n" +
                "BEGIN\n" +
                "\n" +
                "RETURN x+y;\n" +
                "\n" +
                "END\n" +
                "$$";

        // 2. step run the function
        st.execute(sql01);

        //3. step call the function

        CallableStatement cst01 =  con.prepareCall("{? = call addFunction(?, ?)}");

        // 4. step
        // ıt ıs used registerOurParameter() for return type and ıs used set()...methods for parameters


        cst01.registerOutParameter(1, Types.NUMERIC);
        cst01.setInt(2,4);
        cst01.setInt(3,6);

        // 5. step run wıth execute() methods
        cst01.execute();

        // 6. step call the result ın lıne wıth return data type
        System.out.println(cst01.getBigDecimal(1));

        System.out.println("---------------------------------------------------------------");
        // task: write a function to calculate the volume of cone
        String sql02 = "CREATE OR REPLACE FUNCTION Volume_of_Cone(r NUMERIC, h NUMERIC)\n" +
                "RETURNS NUMERIC\n" +
                "LANGUAGE plpgsql\n" +
                "AS\n" +
                "$$\n" +
                "BEGIN\n" +
                "\n" +
                "RETURN 3.14*r*r*h/3;\n" +
                "\n" +
                "END\n" +
                "$$\n";

        st.execute(sql02);

        CallableStatement cst02 =  con.prepareCall("{? = call Volume_of_Cone(?, ?)}");


        cst02.registerOutParameter(1, Types.NUMERIC);
        cst02.setInt(2,4);
        cst02.setInt(3,6);

        // 5. step run the wıth execute() methods
        cst02.execute();

        // 6. step call the result ın lıne wıth return data type
        System.out.printf("%.2f",cst02.getBigDecimal(1));


    }
}
