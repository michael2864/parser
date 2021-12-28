import java.sql.*;
import java.util.Calendar;

public class testconnection {

        public static void main(String[] args)
        {
            try
            {

                // create a mysql database connection
                String myDriver = "com.mysql.jdbc.Driver";
                String myUrl = "jdbc:mysql://localhost:3306/parse";
                Class.forName(myDriver);
                Connection conn = DriverManager.getConnection(myUrl, "root", "root");

                // create a sql date object so we can use it in our INSERT statement
                Calendar calendar = Calendar.getInstance();
                java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());

                // the mysql insert statement
                String query = " insert into process (image_name, PID, session_name, session, mem_usage)"
                        + " values (?, ?, ?, ?, ?)";

                // create the mysql insert preparedstatement
                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setString (1, "Barney");
                preparedStmt.setString (2, "Rubble");
                preparedStmt.setString   (3, "String");
                preparedStmt.setString(4, "String");
                preparedStmt.setString (5, "String");

                // execute the preparedstatement
                preparedStmt.execute();

                conn.close();
            }
            catch (Exception e)
            {
                System.err.println("Got an exception!");
                System.err.println(e.getMessage());
            }
        }

}
