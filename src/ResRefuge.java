import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;

import javax.xml.crypto.Data;

import oracle.net.aso.d;

public class ResRefuge {

    void TestReservationRefuge(Connection conn, String mail_refuge, Date res) {
        try {

            PreparedStatement stmt = conn
                    .prepareStatement("select date_ouverture,date fermeture from Refuge where mail_refuge = ? ");
            stmt.setString(1, mail_refuge);
            ResultSet rset = stmt.executeQuery();
            if (rset.next()) {
                rset.getDate("date_ouverture");
            }
        } catch (SQLException e) {
            System.err.println("failed");
            e.printStackTrace(System.err);

        }
    }

    void InsertReserveRefuge(Connection conn, int userid, String mail, String Heure, Date date, int nb_nuit,
            int nb_repas, int prix_res_refuge) {
        try {

            PreparedStatement stmt = conn.prepareStatement("Insert Into RESERVATION_REFUGE(?,?,?,?,?,?,?)");
            stmt.setInt(1, userid);
            stmt.setString(2, mail);
            stmt.setDate(3, date);
            stmt.setString(4, Heure);
            stmt.setInt(5, nb_nuit);
            stmt.setInt(6, nb_repas);
            stmt.setInt(7, prix_res_refuge);
            int rset = stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("failed");
            e.printStackTrace(System.err);
        }
    }

}