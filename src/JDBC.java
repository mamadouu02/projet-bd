import java.sql.*;

public class JDBC {

    private static String url = "jdbc:oracle:thin:@oracle1.ensimag.fr:1521:oracle1";
    private static String user = "thiongam";
    private static String passwd = "thiongam";
    private static Connection conn;

    public static Connection getConnection() {
        if (conn == null) {
            try {
                DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
                conn = DriverManager.getConnection(url, user, passwd);
                conn.setAutoCommit(false);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }
}
