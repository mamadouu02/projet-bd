import java.sql.*;

public class ReservationFormation {

    private Connection conn;

    public ReservationFormation() {
        this.conn = JDBC.getConnection();
    }
}
