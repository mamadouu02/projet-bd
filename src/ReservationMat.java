import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;

public class ReservationMat {
    Connection connection;
    Map<Lot, Integer> listLotReserve;

    int idAdh;
    String getLot = "SELECT nb_pieces_lot FROM Lot WHERE marque = ? AND modele= ? AND annee_achat= ?";
    String updateLot = "UPDATE lot SET nb_pieces_lot =? WHERE marque = ?, modele= ?, annee_achat= ?";

    String addReserv = "INSERT INTO location_materiel (id_adh,date_emprunt,date_retour) VALUES (?,?,?)";

    String getAdh = "SELECT * FROM Adherent WHERE id_User = ?";
    String addQtt = "INSERT INTO quantite VALUES (?,?,?)";

    public ReservationMat(Connection connection, Map<Lot, Integer> listLotReserve, int idAdh, Date dateEmprunt,
            Date dateRetour) {
        this.connection = connection;
        this.listLotReserve = new HashMap<>(listLotReserve);
    }

    public ReservationMat(Connection connection) {
        this.connection = connection;
        this.listLotReserve = new HashMap<>();
    }

    public void addLot(Lot lot, int qtt) {
        this.listLotReserve.put(lot, qtt);
    }

    public void checkAdherent() {
        try (PreparedStatement getAdhSQL = connection.prepareStatement(getAdh);) {
            ResultSet result = getAdhSQL.executeQuery();
            idAdh = result.getInt("id_adh");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void makeReservation() {
        this.checkAdherent();
        try {
            PreparedStatement updateLotSQL = connection.prepareStatement(updateLot);
            PreparedStatement addQttSQL = connection.prepareStatement(addQtt);
            PreparedStatement addReservSQL = connection.prepareStatement(addReserv);

            connection.setAutoCommit(false);
            for (Lot lot : listLotReserve.keySet()) {
                // quantite a reserver demander
                int qtt = listLotReserve.get(lot);
                PreparedStatement getLotSQL = connection.prepareStatement(getLot);
                getLotSQL.setString(1, lot.marque);
                getLotSQL.setString(2, lot.modele);
                getLotSQL.setInt(3, lot.anneAchat);
                // result sql request
                ResultSet result = getLotSQL.executeQuery();
                result.next();
                int qttdispo = result.getInt("nb_pieces_lot");
                if (qttdispo < qtt) {
                    System.out.println("Pas assez de materiel dispo dans le lot ");
                    // abort
                    connection.rollback();
                    return;
                }
                // Mise a jour inventaire
                updateLotSQL.setInt(1, qttdispo - qtt);
                updateLotSQL.setString(2, lot.marque);
                updateLotSQL.setString(3, lot.modele);
                updateLotSQL.setInt(4, lot.anneAchat);
                updateLotSQL.executeUpdate();
                // Creation Reservation
                addQttSQL.setInt(1, qtt);
                addQttSQL.setInt(2, 0);
                addQttSQL.executeUpdate();
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}