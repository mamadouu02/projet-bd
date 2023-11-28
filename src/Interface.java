import java.sql.*;
import java.util.Scanner;

public class Interface {

    private Connection connection;
    private Scanner sc;
    private int idUser;

    public Interface() {
        this.connection = JDBC.getConnection();
        this.sc = new Scanner(System.in);
    }

    private String getCmd() {
        System.out.print("> ");
        return sc.nextLine();
    }
    
    private int getInt() {
        System.out.print("> ");
        return sc.nextInt();
    }

    public void run() {
        // Connexion utilisateur
        textConnectionUser();

        System.out.println("Pour faire une reservation, tapez : reservation");

        while (true) {
            String cmd = getCmd();

            if (cmd.equals("reservation")) {
                System.out.println("Refuge 1 | Formation 2 | Materiel 3");
                
                if (getInt() == 3) {
                    textReservMat();
                } else {
                    System.out.println("Exited");
                }
            } else if (cmd.equals("Parcours")) {
                System.out.println("Refuge 1 | Formation 2 | Materiel 3");
                int n = getInt();

                if (n == 1) {
                    parcoursRefuges();
                } else if (n == 2) {
                    parcoursFormations();
                } else if (n == 3) {
                    parcoursMateriels();
                } else {
                    System.out.println("Exited");
                }
            }
        }
    }

    private void textConnectionUser() {
        System.out.println("Connexion utilisateur:\n");

        System.out.println("Email ?\n");
        String email = getCmd();
        
        System.out.println("Password ?\n");
        String psswrd = getCmd();
        
        String getPsswrd = "SELECT password, prenom FROM membre WHERE mail_user = ?";
        String getIdUser = "SELECT id_user FROM utilisateur WHERE mail_user = ?";

        try {
            PreparedStatement getPsswrdSQL = connection.prepareStatement(getPsswrd);
            getPsswrdSQL.setString(1, email);
            ResultSet result = getPsswrdSQL.executeQuery();
            result.next();

            String str = result.getString("password");

            if (!psswrd.equals(str)) {
                result.close();
                System.out.println("MAUVAIS PASSWORD\n");
                textConnectionUser();
            } else {
                PreparedStatement getIdUserSQL = connection.prepareStatement(getIdUser);
                getIdUserSQL.setString(1, email);
                ResultSet resultID = getIdUserSQL.executeQuery();
                resultID.next();

                idUser = resultID.getInt("id_user");
                System.out.println("Connexion réussie\n");
                String welcome = "Bienvenue " + result.getString("prenom");
                System.out.println(welcome);
                resultID.close();
                result.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            textConnectionUser();
        }
    }

    private void textReservMat() {
        System.out.println("Reservation Materiel :\n");
        ReservationMateriel reservation = new ReservationMateriel();
        System.out.println("Tapez exit pour revenir au menu\n");

        System.out.println("Marque ?\n");
        String cmd = getCmd();

        while (!cmd.equals("exit")) {
            String marque = cmd;

            System.out.println("Modele ?\n");
            String modele = getCmd();

            System.out.println("Annee ?\n");
            int anneeAchat = getInt();

            Lot lot = new Lot(marque, modele, anneeAchat, 0);

            System.out.println("Quantite voulue ? \n");
            int qte = getInt();

            reservation.addLot(lot, qte);

            System.out.println("Tapez exit pour revenir au menu ou la marque du prochain materiel a ajouter\n");

            System.out.println("Marque ?\n");
            cmd = getCmd();
        }

        if (reservation.getListLotReserve().isEmpty()) {
            return;
        }
        
        reservation.makeReservation();
    }

    private void parcoursFormations() {
        try {
            String getFormation = "SELECT nom_formation, activite, date_formation, duree, nb_places_formation " +
                    "FROM formation " +
                    "JOIN activites_formation " +
                    "ON formation.annee_formation = activites_formation.annee_formation " +
                    "AND formation.rang_formation = activites_formation.rang_formation " +
                    "GROUP BY date_formation ASC ORDER BY nom_formation ASC;";

            PreparedStatement getFormationSQL = connection.prepareStatement(getFormation);
            ResultSet result = getFormationSQL.executeQuery();

            while (result.next()) {
                System.out.println("Nom de formation : " + result.getString(1) +
                        "Activite : " + result.getString(2) +
                        "Date de formation : " + result.getString(3) +
                        "Duree :" + result.getString(4) +
                        "Nombre de places de formation :" + result.getString(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void parcoursMateriels() {
        try {
            String getMateriel = "SELECT l.marque, l.modele, l.nb_pieces_lot, l.nb_pieces_lot - SUM(qm.nb_pieces_perdues) - SUM(qm.nb_pieces_res) AS nb_pieces_disponibles " +
                    "FROM lot l " +
                    "JOIN activites_lot al ON l.marque = al.marque AND l.modele = al.modele AND l.annee_achat = al.annee_achat " +
                    "JOIN quantite_materiel qm ON al.marque = qm.marque AND al.modele = qm.modele AND al.annee_achat = qm.annee_achat " +
                    "JOIN activite a ON al.activite = a.activite " +
                    "GROUP BY l.activite;";

            PreparedStatement getMaterielSQL = connection.prepareStatement(getMateriel);
            ResultSet result = getMaterielSQL.executeQuery();

            while (result.next()) {
                System.out.println("Marque : " + result.getString(1) +
                        "Modèle : " + result.getString(2) +
                        "Nombre de pièces total : " + result.getString(3) +
                        "Nombre de pièces disponibles :" + result.getString(4));
            }

            String getMaterielCategorie = "SELECT l.marque, l.modele, l.nb_pieces_lot, l.nb_pieces_lot - SUM(qm.nb_pieces_perdues) - SUM(qm.nb_pieces_res) AS nb_pieces_disponibles " +
                    "FROM lot l " +
                    "JOIN activites_lot al ON l.marque = al.marque AND l.modele = al.modele AND l.annee_achat = al.annee_achat " +
                    "JOIN quantite_materiel qm ON al.marque = qm.marque AND al.modele = qm.modele AND al.annee_achat = qm.annee_achat " +
                    "JOIN activite a ON al.activite = a.activite " +
                    "GROUP BY l.categorie;";

            PreparedStatement getMaterielCategorieSQL = connection.prepareStatement(getMaterielCategorie);
            result = getMaterielCategorieSQL.executeQuery();

            while (result.next()) {
                System.out.println("Marque : " + result.getString(1) +
                        "Modèle : " + result.getString(2) +
                        "Nombre de pièces total : " + result.getString(3) +
                        "Nombre de pièces disponibles :" + result.getString(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void parcoursRefuges() {
        String afficheRefuge = "SELECT r.nom_refuge, r.secteur_geo, r.nb_places_repas, (r.nb_places_nuits - COUNT(SELECT * FROM refuge WHERE date_res_refuge < CURRENT_DATE))"
                +
                "FROM refuge r" +
                "ORDER BY (r.nb_places_nuits - COUNT(SELECT * FROM refuge WHERE date_res_refuge < CURRENT_DATE)) ASC, r.nom_refuge ASC";
    }
}
