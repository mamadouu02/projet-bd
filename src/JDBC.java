import java.sql.*;

public class JDBC {

    private static String url = "jdbc:oracle:thin:@oracle1.ensimag.fr:1521:oracle1";
    private static String user = "binia";
    private static String passwd = "binia";
    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
                connection = DriverManager.getConnection(url, user, passwd);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return connection;
    }
}
