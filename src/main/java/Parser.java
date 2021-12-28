import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//8 28
//9 17

public class Parser {

    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {

        String myDriver = "com.mysql.jdbc.Driver";
        String myUrl = "jdbc:mysql://localhost:3306/parse";
        Class.forName(myDriver);
        Connection conn = DriverManager.getConnection(myUrl, "root", "root");

        String query = " insert into process (image_name, pid, session_name, session_number, mem_usage)"
                + " values (?, ?, ?, ?, ?)";
        PreparedStatement preparedStmt = conn.prepareStatement(query);




        Process p = Runtime.getRuntime().exec("tasklist");


        try (BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
            String line;
            int counter = 1;
            while ((line = input.readLine()) != null ) {
//                int pid=0;
//                int session_number = 0;
//                int mem_usage =0;
                if (counter > 2) {
                    System.out.println(line);
                    String[] split = line.split("\\s+");
                    preparedStmt.setString(1, split[0]);
                    //      preparedStmt.setInt (2, pid = Integer.parseInt (split [1]) );
                    preparedStmt.setString(2, split[1]);
                    preparedStmt.setString(3, split[2]);
                    //      preparedStmt.setInt (4, session_number = Integer.parseInt (split [3]) );
                    //      preparedStmt.setInt (5, mem_usage = Integer.parseInt (split [4]));
                    preparedStmt.setString(4, split[3]);
                    preparedStmt.setString(5, split[4]);
                 //   preparedStmt.setString(6, split[5]);
                    preparedStmt.execute();
                }
                else {counter++;
                    continue;
                }
            }
        }
        conn.close();

    }

}
