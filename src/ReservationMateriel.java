import java.sql.*;
import java.sql.Date;
import java.util.*;

public class ReservationMateriel {

    private Connection connection;
    private Map<Lot, Integer> listLotReserve;
    private int idAdh;
    private String getLot = "SELECT nb_pieces_lot FROM Lot WHERE marque = ? AND modele = ? AND annee_achat = ?";
    private String updateLot = "UPDATE lot SET nb_pieces_lot = ? WHERE marque = ?, modele = ?, annee_achat = ?";
    private String addReserv = "INSERT INTO location_materiel (id_adh, date_emprunt, date_retour) VALUES (?, ?, ?)";
    private String getAdh = "SELECT * FROM adherent WHERE id_user = ?";
    private String addQte = "INSERT INTO quantite_materiel VALUES (?, ?, ?)";

    public ReservationMateriel() {
        this.connection = JDBC.getConnection();
        this.listLotReserve = new HashMap<>();
    }

    public ReservationMateriel(Map<Lot, Integer> listLotReserve, int idAdh, Date dateEmprunt, Date dateRetour) {
        this.connection = JDBC.getConnection();
        this.listLotReserve = new HashMap<>(listLotReserve);
    }

    public Map<Lot, Integer> getListLotReserve() {
        return listLotReserve;
    }

    public void addLot(Lot lot, int qte) {
        this.listLotReserve.put(lot, qte);
    }

    private void checkAdherent() {
        try {
            PreparedStatement getAdhSQL = connection.prepareStatement(getAdh);
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
            PreparedStatement addQteSQL = connection.prepareStatement(addQte);
            PreparedStatement addReservSQL = connection.prepareStatement(addReserv);
            connection.setAutoCommit(false);

            for (Lot lot : listLotReserve.keySet()) {
                // quantite a reserver demander
                int qte = listLotReserve.get(lot);

                PreparedStatement getLotSQL = connection.prepareStatement(getLot);
                getLotSQL.setString(1, lot.getMarque());
                getLotSQL.setString(2, lot.getModele());
                getLotSQL.setInt(3, lot.getAnneeAchat());
                // result sql request
                ResultSet result = getLotSQL.executeQuery();
                result.next();

                int qtedispo = result.getInt("nb_pieces_lot");
                
                if (qtedispo < qte) {
                    System.out.println("Pas assez de materiel dispo dans le lot");
                    // abort
                    connection.rollback();
                    return;
                }

                // Mise a jour inventaire
                updateLotSQL.setInt(1, qtedispo - qte);
                updateLotSQL.setString(2, lot.getMarque());
                updateLotSQL.setString(3, lot.getModele());
                updateLotSQL.setInt(4, lot.getAnneeAchat());
                updateLotSQL.executeUpdate();

                // Creation Reservation
                addQteSQL.setInt(1, qte);
                addQteSQL.setInt(2, 0);
                addQteSQL.executeUpdate();
            }

            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}