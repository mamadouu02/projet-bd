import java.sql.*;

public class ReservationFormation {

    private Connection conn;
    private int idAdh;
    private Formation formation;
    private int rangLA;

    public ReservationFormation(int idAdh, int annee, int rang) {
        this.conn = JDBC.getConnection();
        this.idAdh = idAdh;
        setFormation(annee, rang);
    }

    public Formation getFormation() {
        return formation;
    }

    public int getRangLA() {
        return rangLA;
    }

    public void setFormation(int annee, int rang) {
        try {
            String query = "SELECT * FROM formation WHERE annee_formation = ? AND rang_formation = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, annee);
            pstmt.setInt(2, rang);
            ResultSet rset = pstmt.executeQuery();
            
            if (rset.next()) {
                String nom = rset.getString("nom_formation");
                Date date = rset.getDate("date_formation");
                int duree = rset.getInt("duree");
                int nbPlacesFormation = rset.getInt("nb_places_formation");
                this.formation = new Formation(annee, rang, nom, date, duree, nbPlacesFormation);
            }
            
            rset.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setRangLA() {
        try {
            String query = "SELECT COUNT(rang_la) FROM reservation_formation WHERE annee_formation = ? AND rang_formation = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, formation.getAnnee());
            pstmt.setInt(2, formation.getRang());
            ResultSet rset = pstmt.executeQuery();

            if (rset.next()) {
                this.rangLA = Math.max(0, rset.getInt(1) - formation.getNbPlacesFormation() + 1);
            }

            rset.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int insert() {
        try {
            String query = "INSERT INTO reservation_formation VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, idAdh);
            pstmt.setInt(2, formation.getAnnee());
            pstmt.setInt(3, formation.getRang());
            pstmt.setInt(4, rangLA);
            pstmt.executeUpdate();
            pstmt.close();
            conn.commit();
            return 0;
        } catch (SQLIntegrityConstraintViolationException e) {
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return 2;
        }
    }

    public boolean check() {
        try {
            String query = "SELECT * FROM reservation_formation WHERE id_adh = ? AND annee_formation = ? AND rang_formation = ? AND rang_LA = 0";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, idAdh);
            pstmt.setInt(2, formation.getAnnee());
            pstmt.setInt(3, formation.getRang());
            ResultSet rset = pstmt.executeQuery();
            boolean test = rset.next();
            rset.close();
            pstmt.close();
            return test;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int delete() {
        try {
            String query = "DELETE FROM reservation_formation WHERE id_adh = ? AND annee_formation = ? AND rang_formation = ? AND rang_LA = 0";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, idAdh);
            pstmt.setInt(2, formation.getAnnee());
            pstmt.setInt(3, formation.getRang());
            pstmt.executeUpdate();
            pstmt.close();
            conn.commit();
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 1;
        }
    }

    public void update() {
        try {
            String query = "UPDATE reservation_formation SET rang_la = rang_la - 1 WHERE rang_la > 0";
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);
            stmt.close();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}