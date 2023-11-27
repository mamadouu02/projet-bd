import java.sql.*;
import java.util.Scanner;

public class JDBC {
    public Connection connection;

    String cmd;

    int idUser;
    public JDBC() {
        this.connection = openConnection();
    }
    public void getCmd() {
        Scanner scan = new Scanner(System.in);
        this.cmd = scan.next();
    }
    public Connection openConnection() {
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            String url =
                    "jdbc:oracle:thin:@oracle1.ensimag.fr:1521:oracle1";
            String user = "binia";
            String passwd = "binia";
            Connection connection = DriverManager.getConnection(url, user, passwd);
            connection.setAutoCommit(false);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    public void textReservMat() {
        System.out.println("Reservation Materiel :\n");
        ReservationMat reservation = new ReservationMat(this.connection);
        System.out.println("Tapez exit pour revenir au menu\n");
        System.out.println("Marque ?\n");
        getCmd();
        while (!cmd.equals("exit")) {
            String marque = cmd;
            System.out.println("Modele ?\n");
            getCmd();
            String modele = cmd;
            System.out.println("Annee ? \n");
            getCmd();
            int anneAchat = Integer.parseInt(cmd);
            Lot lot = new Lot(marque, modele, anneAchat, 0);
            System.out.println("Quantite voulue ? \n");
            getCmd();
            int qtt = Integer.parseInt(cmd);
            reservation.addLot(lot, qtt);
            System.out.println("Tapez exit pour revenir au menu ou la marque du prochain materiel a ajouter\n");
            System.out.println("Marque ?\n");
            getCmd();
        }
        if (reservation.listLotReserve.isEmpty()) {
            return;
        }
        reservation.makeReservation();
    }

    public void textConnectionUser(){
        System.out.println("Connection utilisateur:\n");
        System.out.println("Email ?\n");
        getCmd();
        String email = cmd;
        System.out.println("Password ?\n");
        getCmd();
        String psswrd = cmd;
        String getPsswrd= "SELECT password,prenom FROM MEMBRE WHERE mail_user = ? ";
        String getIdUser= "SELECT id_user FROM Utilisateur WHERE mail_user = ? ";

        try{
            PreparedStatement getPsswrdSQL = connection.prepareStatement(getPsswrd);
            getPsswrdSQL.setString(1,email);
            ResultSet result= getPsswrdSQL.executeQuery();
            result.next();
            String str=result.getString("password");
            System.out.println(str);
            if(!psswrd.equals(str)){
                result.close();
                System.out.println("MAUVAIS PASSWORD\n");
                textConnectionUser();
            }
            else{
                PreparedStatement getIdUserSQL = connection.prepareStatement(getIdUser);
                getIdUserSQL.setString(1,email);
                ResultSet resultID= getIdUserSQL.executeQuery();
                resultID.next();
                idUser=resultID.getInt("ID_USER");
                System.out.println("Connection réussie\n");
                String welcome= "Bienvenu " + result.getString("prenom");
                System.out.println(welcome);
                resultID.close();
                result.close();
            }
        }
        catch (SQLException e){
            e.printStackTrace();
            System.out.println("ERREUR");
            textConnectionUser();
        }
    }


    public void parcoursFormations() {
        try {
            String getFormation = "SELECT nom_formation, activite, date_formation, duree, nb_places_formation " +
                    "FROM formation " +
                    "JOIN activites_formation " +
                    "ON formation.annee_formation = activites_formation.annee_formation " +
                    "AND formation.rang_formation = activites_formation.rang_formation " +
                    "GROUP BY date_formation ASC ORDER BY nom_formation ASC ;";
            
            PreparedStatement getFormationSQL = connection.prepareStatement(getFormation);
            ResultSet result = getFormationSQL.executeQuery();
            
            while (result.next()) {
                System.out.println("Nom de Formation : " + result.getString(1) + 
                        "Activite : " + result.getString(2) + 
                        "Date de Formation : " + result.getString(3) + 
                        "Duree :" + result.getString(4) + 
                        "Nombre de places de formation :" + result.getString(5));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void parcoursMateriels() {
        String getMateriel = "SELECT l.marque, l.modele, l.nb_pieces_lot, l.nb_pieces_lot - SUM(qm.nb_pieces_perdues) - SUM(qm.nb_pieces_res) AS nb_pieces_disponibles " +
                "FROM lot l " +
                "JOIN activites_lot al ON l.marque = al.marque AND l.modele = al.modele AND l.annee_achat = al.annee_achat " +
                "JOIN quantite_materiel qm ON al.marque = qm.marque AND al.modele = qm.modele AND al.annee_achat = qm.annee_achat " +
                "JOIN activite a ON al.activite = a.activite " +
                "GROUP BY l.activite;";

        PreparedStatement getMateriel = connection.prepareStatement(getMateriel);
        ResultSet result = getMateriel.executeQuery();

        while (result.next()) {
            System.out.println("Marque : " + result.getString(1) +
                    "Modèle : " + result.getString(2) +
                    "Nombre de pièces total : " + result.getString(3) +
                    "Nombre de pièces disponibles :" + result.getString(4);
        }



        String afficheMaterielCategorie = "SELECT l.marque, l.modele, l.nb_pieces_lot, l.nb_pieces_lot - SUM(qm.nb_pieces_perdues) - SUM(qm.nb_pieces_res) AS nb_pieces_disponibles " +
                "FROM lot l " +
                "JOIN activites_lot al ON l.marque = al.marque AND l.modele = al.modele AND l.annee_achat = al.annee_achat " +
                "JOIN quantite_materiel qm ON al.marque = qm.marque AND al.modele = qm.modele AND al.annee_achat = qm.annee_achat " +
                "JOIN activite a ON al.activite = a.activite " +
                "GROUP BY l.categorie;";

        PreparedStatement getMaterielCategorie = connection.prepareStatement(getMaterielCategorie);
        ResultSet result = getMaterielCategorie.executeQuery();

        while (result.next()) {
            System.out.println("Marque : " + result.getString(1) +
                    "Modèle : " + result.getString(2) +
                    "Nombre de pièces total : " + result.getString(3) +
                    "Nombre de pièces disponibles :" + result.getString(4);
        }
    }

    public void parcoursRefuges() {
        String afficheRefuge = "SELECT r.nom_refuge, r.secteur_geo, r.nb_places_repas, (r.nb_places_nuits-COUNT(SELECT * FROM refuge WHERE date_res_refuge < CURRENT_DATE))" +
        "FROM refuge r" +
        "ORDER BY (r.nb_places_nuits-COUNT(SELECT * FROM refuge WHERE date_res_refuge < CURRENT_DATE)) ASC, r.nom_refuge ASC";
    }
}
