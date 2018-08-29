import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class testing_sql_java {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        // Load the JDBC driver
        Class.forName("org.mariadb.jdbc.Driver");
        System.out.println("Driver loaded");
        String url = "jdbc:mysql://192.168.40.119/bike_test";

        String user = "root";
        String pass = "test";

        String query = "SELECT VERSION()";
        // Try to connect
        try (Connection con = DriverManager.getConnection(url, user, pass);

             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            System.out.println("It works!");
            if (rs.next()) {

                System.out.println(rs.getString(1));
            }
        }
        catch (SQLException ex) {

            Logger lgr = Logger.getLogger(testing_sql_java.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }



    }
}