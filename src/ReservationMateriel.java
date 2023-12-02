import java.sql.*;
import java.sql.Date;
import java.util.*;
import java.time.Year;

public class ReservationMateriel {

    public Date dateRetour;
    private Connection connection;
    private Map<Lot, Integer> listLotReserve;
    private int idAdh;

    private String getLot = "SELECT nb_pieces_lot FROM Lot WHERE marque = ? AND modele = ? AND annee_achat = ?";
    private String updateLot = "UPDATE lot SET nb_pieces_lot = nb_pieces_lot - ? WHERE marque = ? AND modele = ? AND annee_achat = ?";
    private String addReserv = "INSERT INTO location_materiel (id_adh, date_emprunt, date_retour) VALUES (?, ?, ?)";
    private String addQte = "INSERT INTO quantite_materiel VALUES (?, ?, ?, ?, ?, ?)";
    private String getDatePeremption = "SELECT annee_peremption FROM lot WHERE marque = ? AND modele = ? AND annee_achat = ?";
    private String getKey = "SELECT id_res_materiel_seq.CURRVAL FROM DUAL";

    public ReservationMateriel(Connection conn, int idAdh) {
        this.connection = conn;
        this.listLotReserve = new HashMap<>();
        this.idAdh = idAdh;
    }

    public ReservationMateriel(Map<Lot, Integer> listLotReserve, int idAdh, Date dateEmprunt, Date dateRetour) {
        this.connection = JDBC.getConnection();
        this.listLotReserve = new HashMap<>(listLotReserve);
        this.idAdh = idAdh;
    }

    public Map<Lot, Integer> getListLotReserve() {
        return listLotReserve;
    }

    public void addLot(Lot lot, int qtt) {
        // Annee actuelle
        int year = Year.now().getValue();

        try {
            PreparedStatement getDatePeremptionSQL = connection.prepareStatement(getDatePeremption);
            getDatePeremptionSQL.setString(1, lot.getMarque());
            getDatePeremptionSQL.setString(2, lot.getModele());
            getDatePeremptionSQL.setInt(3, lot.getAnneeAchat());
            ResultSet result = getDatePeremptionSQL.executeQuery();

            if (result.next()) {
                int anneePeremption = result.getInt("annee_peremption");

                if (!(result.wasNull()) && year > anneePeremption) {
                    System.out.println("ERREUR : lot périmé, impossible de réserver du matériel venant de ce lot");
                    return;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        // Teste si le lot est deja dedans et update la quantite
        for (Lot lotAlreadyIn : this.listLotReserve.keySet()) {
            if (lotAlreadyIn.equals(lot)) {
                int newQtt = this.listLotReserve.get(lotAlreadyIn) + qtt;
                this.listLotReserve.remove(lotAlreadyIn);

                if (testQttDispo(lot, qtt)) {
                    this.listLotReserve.put(lot, newQtt);
                } else {
                    return;
                }
            }
        }

        if (testQttDispo(lot, qtt)) {
            this.listLotReserve.put(lot, qtt);
        } else {
            return;
        }

    }

    private boolean testQttDispo(Lot lot, int qtt) {
        try {
            PreparedStatement getLotSQL = connection.prepareStatement(getLot);
            getLotSQL.setString(1, lot.getMarque());
            getLotSQL.setString(2, lot.getModele());
            getLotSQL.setInt(3, lot.getAnneeAchat());
            // result sql request
            ResultSet result = getLotSQL.executeQuery();
            result.next();

            int qtedispo = result.getInt("nb_pieces_lot");

            if (qtedispo < qtt) {
                System.out.println("Pas assez de materiel dispo dans le lot");
                // abort
                return false;
            } else {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void makeReservation() {
        try {
            PreparedStatement updateLotSQL = connection.prepareStatement(updateLot);
            PreparedStatement addQteSQL = connection.prepareStatement(addQte);
            PreparedStatement addReservSQL = connection.prepareStatement(addReserv, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement getKeySQL = connection.prepareStatement(getKey);
            // Creation Reservation

            addReservSQL.setInt(1, this.idAdh);
            long millis = System.currentTimeMillis();
            java.sql.Date date = new java.sql.Date(millis);
            addReservSQL.setDate(2, date);
            addReservSQL.setDate(3, this.dateRetour);

            // Retour num reservation
            addReservSQL.executeUpdate();

            ResultSet key = getKeySQL.executeQuery();
            int numRez = -1;

            if (key.next()) {
                numRez = key.getInt(1);
            }

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
                updateLotSQL.setInt(1, qte);
                updateLotSQL.setString(2, lot.getMarque());
                updateLotSQL.setString(3, lot.getModele());
                updateLotSQL.setInt(4, lot.getAnneeAchat());
                updateLotSQL.executeUpdate();

                // Creation Quantite
                addQteSQL.setInt(1, numRez);
                addQteSQL.setString(2, lot.getMarque());
                addQteSQL.setString(3, lot.getModele());
                addQteSQL.setInt(4, lot.getAnneeAchat());
                addQteSQL.setInt(5, qte);
                addQteSQL.setInt(6, 0);
                addQteSQL.executeUpdate();
            }
            System.out.println("Votre numéro de réservation de matériel est : " + key.getInt(1));

            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
