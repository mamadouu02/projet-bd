import java.sql.*;

public class ReservationRefuge {

    public void testReservationRefuge(Connection conn, String mailRefuge, Date res) {
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT date_ouverture, date_fermeture FROM refuge WHERE mail_refuge = ?");
            stmt.setString(1, mailRefuge);
            ResultSet rset = stmt.executeQuery();

            if (rset.next()) {
                rset.getDate("date_ouverture");
            }
        } catch (SQLException e) {
            System.err.println("failed");
            e.printStackTrace(System.err);
        }
    }

    public void insertReserveRefuge(Connection conn, int userId, String mail, String heure, Date date, int nbNuit, int nbRepas, int prixResRefuge) {
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO reservation_refuge (?, ?, ?, ?, ?, ?, ?)");
            stmt.setInt(1, userId);
            stmt.setString(2, mail);
            stmt.setDate(3, date);
            stmt.setString(4, heure);
            stmt.setInt(5, nbNuit);
            stmt.setInt(6, nbRepas);
            stmt.setInt(7, prixResRefuge);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("failed");
            e.printStackTrace(System.err);
        }
    }
}
