import java.sql.*;
import java.util.ArrayList;
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

        ArrayList<Date_SQL> converted_brake_dates = new ArrayList<>();


        String query = "SELECT * FROM Bike;";
        ArrayList<String>  list_brake_dates = new ArrayList<>();
        // Try to connect
        try (Connection con = DriverManager.getConnection(url, user, pass);

             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            System.out.println("It works!");
            while (rs.next())
            {
                String temp = rs.getString(4);
                list_brake_dates.add(temp);
                System.out.println(temp);
            }
            for (int i =0; i < list_brake_dates.size(); i++)
            {
                converted_brake_dates.add(new Date_SQL(list_brake_dates.get(i)));
            }
            for (int i =0; i < converted_brake_dates.size(); i++)
            {
                System.out.println("day: " + converted_brake_dates.get(i).getD());
            }
            //System.out.println(Date_SQL.compareDates(converted_brake_dates.get(3),converted_brake_dates.get(0)));
            System.out.println("oldest date: "+ converted_brake_dates.get(Date_SQL.findOldestDate(converted_brake_dates)).getD());
            System.out.println("oldest date: "+ converted_brake_dates.get(Date_SQL.findOldestDate(converted_brake_dates)).getM());


        }
        catch (SQLException ex) {

            Logger lgr = Logger.getLogger(testing_sql_java.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }



    }
}