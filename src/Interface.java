import java.sql.*;
import java.util.Scanner;

public class Interface {

    private Connection conn;
    private Scanner sc;
    private User user;

    public Interface() {
        this.conn = JDBC.getConnection();
        this.sc = new Scanner(System.in);
    }

    private String getCmd() {
        System.out.print("> ");
        return sc.next();
    }

    private int getInt() {
        System.out.print("> ");
        return sc.nextInt();
    }

    private Date getDate(){
        System.out.print("> ");
        Date date = Date.valueOf(sc.next()); //converting string into sql date
        return date;
    }

    public void connexionUser() {
        try {
            System.out.println("\n===== CONNEXION =====\n");

            System.out.println("Email :");
            String email = getCmd();
            
            String getMail = "SELECT mail_user FROM membre WHERE mail_user = ?";
            PreparedStatement getMailSQL = conn.prepareStatement(getMail);
            getMailSQL.setString(1, email);
            ResultSet result = getMailSQL.executeQuery();

            if (!result.next()) {
                result.close();
                System.out.println("\nL'identifiant est incorrect.");
                connexionUser();
            }

            System.out.println("\nPassword :");
            String psswrd = getCmd();
            
            String getPsswrd = "SELECT password, nom_user, prenom FROM membre WHERE mail_user = ?";
            String getIdUser = "SELECT id_user FROM utilisateur WHERE mail_user = ?";
            String getIdAdh = "SELECT id_adh FROM adherent WHERE id_user = ?";

            PreparedStatement getPsswrdSQL = conn.prepareStatement(getPsswrd);
            getPsswrdSQL.setString(1, email);
            result = getPsswrdSQL.executeQuery();
            result.next();

            String str = result.getString("password");

            if (!psswrd.equals(str)) {
                result.close();
                System.out.println("\nLe mot de passe est incorrect.");
                connexionUser();
            } else {
                PreparedStatement getIdUserSQL = conn.prepareStatement(getIdUser);
                getIdUserSQL.setString(1, email);
                ResultSet resultID = getIdUserSQL.executeQuery();
                resultID.next();

                int idUser = resultID.getInt("id_user");
                String nom = result.getString("nom_user");
                String prenom = result.getString("prenom");

                PreparedStatement getIdAdhSQL = conn.prepareStatement(getIdAdh);
                getIdAdhSQL.setString(1, String.valueOf(idUser));
                resultID = getIdAdhSQL.executeQuery();

                int idAdh = 0;

                if (resultID.next()) {
                    idAdh = resultID.getInt("id_adh");
                }

                user = new User(idUser, email, nom, prenom, idAdh);

                System.out.println("\nConnexion réussie.");

                resultID.close();
                result.close();

                menu();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            connexionUser();
        }
    }


    private void menu() {
        System.out.println("\n===== MENU PRINCIPAL =====\n");
        System.out.println("Bienvenue, " + user.getPrenom() + " " + user.getNom() + " (n°" + user.getIdUser() + ").");
        
        while (true) {
            System.out.println("\nChoississez une option :");
            System.out.println("[1] Parcours du catalogue");
            System.out.println("[2] Réservation");
            System.out.println("[3] Consultation du solde");
            System.out.println("[4] Suppression des données personnelles");
            System.out.println("[5] Quitter\n");
            
            String cmd = getCmd();

            if (cmd.equals("1")) {
                System.out.println("\n===== PARCOURS =====\n");
                System.out.println("Choississez une option :");
                System.out.println("[1] Consulter les refuges");
                System.out.println("[2] Consulter les formations");
                System.out.println("[3] Consulter le matériel\n");

                cmd = getCmd();

                if (cmd.equals("1")) {
                    parcoursRefuges();
                } else if (cmd.equals("2")) {
                    parcoursFormations();
                } else if (cmd.equals("3")) {
                    parcoursMateriels();
                } else {
                    System.out.println("Exited.");
                }
            } else if (cmd.equals("2")) {
                System.out.println("\n===== RESERVATION =====\n");
                System.out.println("Choississez une option :");
                System.out.println("[1] Réservation de refuge");
                System.out.println("[2] Réservation de formation");
                System.out.println("[3] Réservation de matériel");
                System.out.println("[4] Retour de matériel\n");

                cmd = getCmd();

                if (cmd.equals("1")) {
                    reservationRefuge();
                } else if (cmd.equals("2")) {
                    reservationFormation();
                } else if (cmd.equals("3")) {
                    reservationMateriel();
                } else if (cmd.equals("4")) {
                    retourMateriel();
                } else {
                    System.out.println("Exited.");
                }
            } else if (cmd.equals("3")) {
                System.out.println("\n===== SOLDE =====\n");
                System.out.println("Choississez une option :");
                System.out.println("[1] Consulter le solde");
                System.out.println("[2] Remboursement\n");

                cmd = getCmd();

                if (cmd.equals("1")) {
                    consulterSolde();
                } else if (cmd.equals("2")) {
                    remboursement();
                } else {
                        System.out.println("Exited.");
                    }
            } else if (cmd.equals("4")) {
                supprimerDonneesPersonnelles();
                return;
            } else if (cmd.equals("5")) {
                System.out.println("Bye");
                try{
                    conn.close();
                }
                catch (SQLException e){
                    e.printStackTrace();
                }
                return;
            } else {
                System.out.println("\nVeuillez entrer un choix correct : 1, 2, 3, 4, 5");
            }
        }
    }

    private void parcoursRefuges() {
        try {
            String getRefuge = "SELECT r.nom_refuge, r.secteur_geo, r.nb_places_nuits, r.nb_places_repas " +
                    "FROM refuge r " +
                    "ORDER BY r.nb_places_nuits, r.nom_refuge ASC";

            PreparedStatement getRefugeSQL = conn.prepareStatement(getRefuge);
            ResultSet result = getRefugeSQL.executeQuery();

            while (result.next()) {
                System.out.println("\nNom : " + result.getString(1) + "\n" +
                        "Secteur géographique : " + result.getString(2) + "\n" +
                        "Nombre de places pour dormir : " + result.getString(3) + "\n" +
                        "Nombre de places pour manger : " + result.getString(4));
            }

            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void parcoursFormations() {
        try {
            String getFormation = "SELECT nom_formation, activite, date_formation, duree, nb_places_formation " +
                    "FROM formation " +
                    "JOIN activites_formation " +
                    "ON formation.annee_formation = activites_formation.annee_formation " +
                    "AND formation.rang_formation = activites_formation.rang_formation " +
                    "ORDER BY date_formation, nom_formation ASC";

            PreparedStatement getFormationSQL = conn.prepareStatement(getFormation);
            ResultSet result = getFormationSQL.executeQuery();

            while (result.next()) {
                System.out.println("\nNom de la formation : " + result.getString(1) + "\n" +
                        "Activité : " + result.getString(2) + "\n" +
                        "Date de la formation : " + result.getString(3) + "\n" +
                        "Durée : " + result.getString(4) + "jours\n" +
                        "Nombre de places de la formation : " + result.getString(5)) ;
            }
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void parcoursMateriels() {
        try {
            System.out.println("\nTri par :");
            System.out.println("[1] Catégorie");
            System.out.println("[2] Activité\n");

            String cmd = getCmd();

            if (cmd.equals("1")){
                String getMaterielCategorie = "SELECT l.marque, l.modele, l.nb_pieces_lot, l.nb_pieces_lot, l.sous_categorie " +
                        "FROM lot l " +
                        "ORDER BY l.sous_categorie ASC";

                PreparedStatement getMaterielCategorieSQL = conn.prepareStatement(getMaterielCategorie);
                ResultSet result = getMaterielCategorieSQL.executeQuery();

                int i = 0;
                boolean bool = true;
                boolean debut = false;
                String cat = "";
                while (result.next()) {
                    if (debut && !result.getString(5).equals(cat)){
                        i++;
                        System.out.println("Catégorie n°" + i + " : " + result.getString(5));
                    }
                    cat = result.getString(5);
                    if (!debut) {
                        i++;
                        System.out.println("Catégorie n°" + i + " : " + cat);
                    }
                    while (bool && result.getString(5).equals(cat)) {
                        System.out.println("\t Marque : " + result.getString(1) + "\n" +
                                "\t Modèle : " + result.getString(2) + "\n" +
                                "\t Nombre de pièces total : " + result.getString(3) + "\n" +
                                "\t Nombre de pièces disponibles : " + result.getString(4) + "\n");
                        bool = result.next();
                    }
                    if (bool && !result.getString(5).equals(cat)) {
                        i++;
                        cat = result.getString(5);
                        System.out.println("Catégorie n°" + i + " : " + cat);
                        System.out.println("\t Marque : " + result.getString(1) + "\n" +
                                "\t Modèle : " + result.getString(2) + "\n" +
                                "\t Nombre de pièces total : " + result.getString(3) + "\n" +
                                "\t Nombre de pièces disponibles : " + result.getString(4) + "\n");
                        debut = true;
                    } else {
                        debut = false;
                    }
                }
                result.close();
            } else if (cmd.equals("2")) {
                String getMaterielActivite = "SELECT l.marque, l.modele, l.nb_pieces_lot, l.nb_pieces_lot, l.activite " +
                        "FROM lot l " +
                        "ORDER BY l.activite ASC";

                PreparedStatement getMaterielActiviteSQL = conn.prepareStatement(getMaterielActivite);
                ResultSet result = getMaterielActiviteSQL.executeQuery();

                int i = 0;
                boolean bool = true;
                boolean debut = false;
                String act = "";
                while (result.next()) {
                    if (debut && !result.getString(5).equals(act)){
                        i++;
                        System.out.println("Activité n°" + i + " : " + result.getString(5));
                    }
                    act = result.getString(5);
                    if (!debut) {
                        i++;
                        System.out.println("Activité n°" + i + " : " + act);
                    }
                    while (bool && result.getString(5).equals(act)) {
                        System.out.println("\t Marque : " + result.getString(1) + "\n" +
                                "\t Modèle : " + result.getString(2) + "\n" +
                                "\t Nombre de pièces total : " + result.getString(3) + "\n" +
                                "\t Nombre de pièces disponibles :" + result.getString(4) + "\n");
                        bool = result.next();
                    }
                    if (bool && !result.getString(5).equals(act)) {
                        i++;
                        act = result.getString(5);
                        System.out.println("Activité n°" + i + " : " + act);
                        System.out.println("\t Marque : " + result.getString(1) + "\n" +
                                "\t Modèle : " + result.getString(2) + "\n" +
                                "\t Nombre de pièces total : " + result.getString(3) + "\n" +
                                "\t Nombre de pièces disponibles : " + result.getString(4) + "\n");
                        debut = true;
                    } else {
                        debut = false;
                    }
                }
                result.close();
            } else {
                System.out.println("Exited.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void reservationRefuge() {
        System.out.println("\n===== RESERVATION DE REFUGE =====\n");
        System.out.println("Nom du Refuge :");
        String refuge = getCmd();
        System.out.println("\nDate de reservation (YYYY-MM-DD) :");
        Date date_res = getDate();
        System.out.println("\nHeure de reservation (hh:mm) :");
        String heure_res = getCmd();
        System.out.println("Choississez une option :");
        System.out.println("[0] pas Manger et pas dormir");
        System.out.println("[1] Manger et Pas dormir");
        System.out.println("[2] Pas Manger et Dormir");
        System.out.println("[3] Manger et Dormir\n");
        int choix = getInt();
        int nbNuits =0;
        Boolean dejeuner = false;
        Boolean case_croute = false;
        Boolean diner = false;
        Boolean souper = false;
        Boolean manger = (choix ==1) | (choix==3);
        Boolean dormir = (choix >1);
        if (dormir){
            System.out.println("\nNombre de nuit(s) :");
            nbNuits= getInt();
        } else if (manger) {
            Repas typeRepas = new Repas();
            typeRepas = typeRepas.repasDisponible(conn,refuge);
            System.out.println("\ntype de repas disponible :");

            if(typeRepas.getDejeuner()==true){
                System.out.println("\nVeux-tu un Déjeuner :");
                System.out.println("[0] non");
                System.out.println("[1] oui");
                dejeuner = (getInt()==1);
            }else if(typeRepas.getCasse_croute()){
                System.out.println("\nVeux-tu un Casse_croute :");
                System.out.println("[0] non");
                System.out.println("[1] oui");
                case_croute = (getInt()==1);
            }else if(typeRepas.getDiner()){
                System.out.println("\nVeux-tu un Diner :");
                System.out.println("[0] non");
                System.out.println("[1] oui");
                diner = (getInt()==1);
            }else if(typeRepas.getSouper()){
                System.out.println("\nVeux-tu un Souper:");
                System.out.println("[0] non");
                System.out.println("[1] oui");
                souper = (getInt()==1);
            }




        }
        String[] repasvoulu = new String[4];
        repasvoulu[0]= (dejeuner==true)? "déjeuner":null;
        repasvoulu[1]= (case_croute==true)? "casse-croûte":null;
        repasvoulu[2] = (diner==true)? "dîner":null;
        repasvoulu[3] = (souper==true)? "souper":null;

        ReservationRefuge Resrefuge= new ReservationRefuge();
        int res = Resrefuge.testReservationRefuge(conn,refuge,date_res,manger,dormir,nbNuits,repasvoulu);
        switch (res){
            case 0 :

                Resrefuge.insertReserveRefuge(conn,user.getIdUser(),user.getMail(),date_res,heure_res, nbNuits);
                break;
            case 1:
                System.out.println("\nChoississez une option :");
                System.out.println("[0] Quitter");
                System.out.println("[1] Reserver à une autre date");
                choix = getInt();
                if(choix==1){
                    reservationRefuge();
                }
                break;
            case 2:
                System.out.println("Choississez une option :");
                System.out.println("[0] Quitter");
                System.out.println("[1] Reserver sans repas");
                if(choix==1) {
                    reservationRefuge();
                }
                break;
            case 3:
                System.out.println("Choississez une option :");
                System.out.println("[0] Quitter");
                System.out.println("[1] Reserver sans nuits");
                if(choix==1) {
                    reservationRefuge();
                }
                break;

        }
    };

    private void reservationFormation() {
        try {
            System.out.println("\n===== RESERVATION DE FORMATION =====\n");
            
            if (user.getIdAdh() == 0) {
                System.out.println("Vous devez être adhérent pour réserver une formation.");
                menu();
            }

            System.out.println("Année de la formation :");
            int annee = getInt();

            System.out.println("\nRang de la formation :");
            int rang = getInt();

            String query = "SELECT annee_formation, rang_formation FROM formation WHERE annee_formation = ? AND rang_formation = ?";
            PreparedStatement prepare = conn.prepareStatement(query);
            prepare.setInt(1, annee);
            prepare.setInt(2, rang);
            ResultSet res = prepare.executeQuery();

            if (!res.next()) {
                prepare.close();
                System.out.println("\nLa formation choisie est inexistante.");
                menu();
            }

            prepare.close();

            String query2 = "SELECT nb_places_formation FROM formation WHERE annee_formation = ? AND rang_formation = ?";
            PreparedStatement prepare2 = conn.prepareStatement(query2);
            prepare2.setInt(1, annee);
            prepare2.setInt(2, rang);
            res = prepare2.executeQuery();
            res.next();
            int nbPlacesFormation = res.getInt(1);
            prepare2.close();

            String query3 = "SELECT COUNT(rang_la) FROM reservation_formation WHERE annee_formation = ? AND rang_formation = ?";
            PreparedStatement prepare3 = conn.prepareStatement(query3);
            prepare3.setInt(1, annee);
            prepare3.setInt(2, rang);
            res = prepare3.executeQuery();
            res.next();
            int rangLA = res.getInt(1) + 1;
            rangLA = Math.max(0, rangLA - nbPlacesFormation);
            prepare3.close();

            res.close();

            String query4 = "INSERT INTO reservation_formation VALUES (?, ?, ?, ?)";
            PreparedStatement prepare4 = conn.prepareStatement(query4);
            prepare4.setInt(1, user.getIdAdh());
            prepare4.setInt(2, annee);
            prepare4.setInt(3, rang);
            prepare4.setInt(4, rangLA);
            prepare4.executeUpdate();
            prepare4.close();

            conn.commit();

            if (rangLA == 0) {
                System.out.println("\nVous êtes inscrit à la formation.");
            } else {
                System.out.println("\nVous êtes en liste complémentaire (rang : " + rangLA + ").");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    };

    private void reservationMateriel() {
        System.out.println("\n===== RESERVATION DE MATERIEL =====\n");

        //Check si l'user est adherent
        if(user.getIdAdh()==0){
            System.out.println("ERREUR : Vous devez etre adherent pour reserver du materiel");
            menu();
        }
        ///////////////

        ReservationMateriel resMateriel = new ReservationMateriel(this.conn,this.user.getIdAdh());

        System.out.println("Date de retour (AAAA-MM-JJ) :");
        resMateriel.dateRetour=getDate();

        System.out.println("\nMarque :");
        String cmd = getCmd();

        while (!cmd.equals("exit")) {
            String marque = cmd;

            System.out.println("\nModèle :");
            String modele = getCmd();

            System.out.println("\nAnnée :");
            int anneeAchat = getInt();

            Lot lot = new Lot(marque, modele, anneeAchat, 0);

            System.out.println("\nQuantité voulue :");
            int qte = getInt();

            resMateriel.addLot(lot, qte);

            System.out.println("\nSi vous avez fini, tapez exit pour revenir au menu, sinon, tapez la marque du prochain matériel à ajouter.");

            System.out.println("\nMarque :");
            //cmd = "exit";
            cmd=getCmd();
        }

        if (resMateriel.getListLotReserve().isEmpty()) {
            return;
        }
        
        resMateriel.makeReservation();
    }

    public void retourMateriel() {
        String getReservation = "SELECT * FROM quantite_materiel NATURAL JOIN location_materiel WHERE id_adh= ? AND id_res_materiel= ?";
        String updateQtt = "UPDATE quantite_materiel SET nb_pieces_perdues = ? WHERE id_res_materiel = ? AND marque = ? AND modele = ? AND annee_achat = ?";
        String updateLot = "UPDATE lot SET nb_pieces_lot =  nb_pieces_lot - ? WHERE marque = ? AND modele = ? AND annee_achat = ?";

        System.out.println("Retour Materiel :\n");
        int idAdh = user.getIdAdh();
        System.out.println("Numéro de reservation\n");
        int numRez = getInt();
        try {
            PreparedStatement getReservationSQL = conn.prepareStatement(getReservation);
            getReservationSQL.setInt(1, idAdh);
            getReservationSQL.setInt(2, numRez);
            ResultSet result = getReservationSQL.executeQuery();
            while (result.next()) {
                String Marque = result.getString("marque");
                String Modele = result.getString("modele");
                int Annee = result.getInt("annee_achat");
                //int idRez = result.getInt("id_res_formation");

                System.out.println("Quantite perdue pour tel lot");
                System.out.println(Marque + " " + Modele + " " + Annee);

                int qttPerdu = getInt();

                //Update QTT
                PreparedStatement updateQttSQL = conn.prepareStatement(updateQtt);
                updateQttSQL.setInt(1, qttPerdu);
                updateQttSQL.setInt(2, numRez);
                updateQttSQL.setString(3,Marque);
                updateQttSQL.setString(4,Modele);
                updateQttSQL.setInt(5,Annee);
                updateQttSQL.executeUpdate();

                //Update Lot
                PreparedStatement updateLotSQL = conn.prepareStatement(updateLot);
                updateLotSQL.setInt(1, -qttPerdu);
                updateLotSQL.setString(2, Marque);
                updateLotSQL.setString(3, Modele);
                updateLotSQL.setInt(4, Annee);
                updateLotSQL.executeUpdate();

            }
            conn.commit();
            result.close();
            System.out.println("Retour au menu");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("ERREUR Lors du retour de Materiel");

        }
    }

    private void consulterSolde(){
        try {
            String id_user_connecte = String.valueOf(user.getIdUser());
            String getCoutReservationRefuge = "SELECT SUM(res_r.nb_nuits*r.prix_nuit) FROM reservation_refuge res_r JOIN refuge r ON res_r.mail_refuge = r.mail_refuge WHERE res_r.id_user = ?";
            PreparedStatement getCoutReservationRefugeSQL = conn.prepareStatement(getCoutReservationRefuge);
            getCoutReservationRefugeSQL.setString(1, id_user_connecte);

            ResultSet result = getCoutReservationRefugeSQL.executeQuery();
            result.next();
            System.out.println("\nCoût des réservations des refuges : " + result.getString(1));
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        try {
            String id_adh_connecte = String.valueOf(user.getIdAdh());
            String getCoutReservationFormation = "SELECT SUM(f.prix_formation) FROM formation f JOIN reservation_formation res_f ON res_f.annee_formation = f.annee_formation AND res_f.rang_formation = f.rang_formation WHERE res_f.id_adh = ? AND res_f.rang_la = 0";
            PreparedStatement getCoutReservationFormationSQL = conn.prepareStatement(getCoutReservationFormation);
            getCoutReservationFormationSQL.setString(1, id_adh_connecte);

            ResultSet result = getCoutReservationFormationSQL.executeQuery();
            result.next();
            System.out.println("Coût des réservations des formations : " + result.getString(1));
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        try {
            String id_adh_connecte = String.valueOf(user.getIdAdh());
            String getCoutMaterielAbime = "SELECT SUM(l.prix_caution*q.nb_pieces_perdues) FROM quantite_materiel q JOIN lot l ON q.marque = l.marque AND q.modele = l.modele AND q.annee_achat = l.annee_achat JOIN location_materiel lm ON q.id_res_materiel = lm.id_res_materiel WHERE lm.id_adh = ?";
            PreparedStatement getCoutMaterielAbimeSQL = conn.prepareStatement(getCoutMaterielAbime);
            getCoutMaterielAbimeSQL.setString(1, id_adh_connecte);

            ResultSet result = getCoutMaterielAbimeSQL.executeQuery();
            result.next();
            System.out.println("Somme due à cause des matériels abîmés lors des locations : " + result.getString(1));
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        try {
            String id_user_connecte = String.valueOf(user.getIdUser());
            String getSommeRemboursee = "SELECT somme_remboursee FROM utilisateur WHERE id_user = ?";
            PreparedStatement getSommeRembourseeSQL = conn.prepareStatement(getSommeRemboursee);
            getSommeRembourseeSQL.setString(1, id_user_connecte);

            ResultSet result = getSommeRembourseeSQL.executeQuery();
            result.next();
            System.out.println("Somme déjà remboursée par le membre : " + result.getString(1));
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void remboursement() {
        try {
            String id_adh_connecte = String.valueOf(user.getIdAdh());
            String getCoutMaterielAbime = "SELECT (l.prix_caution*q.nb_pieces_perdues) FROM quantite_materiel q JOIN lot l ON q.marque = l.marque AND q.modele = l.modele AND q.annee_achat = l.annee_achat JOIN location_materiel lm ON q.id_res_materiel = lm.id_res_materiel WHERE lm.id_adh = ?";
            PreparedStatement getCoutMaterielAbimeSQL = conn.prepareStatement(getCoutMaterielAbime);
            getCoutMaterielAbimeSQL.setString(1, id_adh_connecte);
            ResultSet result = getCoutMaterielAbimeSQL.executeQuery();
            result.next();
            int sommeTotal = result.getInt(1);
            result.close();


            String id_user_connecte = String.valueOf(user.getIdUser());
            String getSommeRemboursee = "SELECT somme_remboursee FROM utilisateur WHERE id_user = ?";
            PreparedStatement getSommeRembourseeSQL = conn.prepareStatement(getSommeRemboursee);
            getSommeRembourseeSQL.setString(1, id_user_connecte);
            result = getSommeRembourseeSQL.executeQuery();
            result.next();
            int sommeRemboursee = result.getInt(1);
            result.close();

            int aRembourser = sommeTotal - sommeRemboursee;

            System.out.println("\nReste à payer : " + aRembourser);
            System.out.println("Combien voulez-vous payer ?\n");

            String cmd = getCmd();

            String getSommeRembourseeMAJ = "UPDATE utilisateur SET somme_remboursee = somme_remboursee + ? WHERE id_user = ?";
            PreparedStatement getSommeRembourseeMAJSQL = conn.prepareStatement(getSommeRembourseeMAJ);
            getSommeRembourseeMAJSQL.setString(1, cmd);
            aRembourser -= Integer.valueOf(cmd);
            if (aRembourser >= 0) {
                getSommeRembourseeMAJSQL.setString(2, id_user_connecte);
                result = getSommeRembourseeMAJSQL.executeQuery();
                result.next();
                result.close();
                System.out.println("\nVotre solde a bien été mis à jour.");
                System.out.println("Reste à payer : " + aRembourser);
            } else {
                System.out.println("Veuillez saisir un montant inférieur à la somme à rembourser. \n");
                remboursement();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int fonctionHashInjective(int entier, int max) {
        return max + entier;
    }
        //L'utilisateur exerce son droit à l'oubli : il ne veut plus garder ses infos personnelles dans notre système
    public void supprimerDonneesPersonnelles() {
        try {

            String choice = "";
            do {
                //On lui demande une confirmatin de la suppression de ses données
                System.out.println("\nAttention : cette action est irréversible. Êtes-vous sur de continuer ?\n");
                System.out.println("[1] OUi, supprimer définitivement mes données.");
                System.out.println("[2] Non, conserver mes données.");
                choice = getCmd();
                if(choice.equals("1")) {
                    String maxUser= "SELECT MAX(id_user) FROM utilisateur";
                    PreparedStatement maxUserQuery = conn.prepareStatement(maxUser);
                    ResultSet result = maxUserQuery.executeQuery();
                    result.next();
                    int maxIdUser = Integer.valueOf(result.getString(1));

                    int newIdUser = fonctionHashInjective(user.getIdUser(),maxIdUser);

                    String mail_user = user.getMail();
                    //On met à jour la table utilisateur pour rendre son mail à  NULL
                    String updateUser= "UPDATE utilisateur SET mail_user = NULL WHERE id_user = ? ";
                    PreparedStatement updateUserQuery = conn.prepareStatement(updateUser);
                    updateUserQuery.setInt(1,user.getIdUser());
                    updateUserQuery.executeUpdate();
                    String deleteMember=  "DELETE FROM membre WHERE mail_user = ?";
                    PreparedStatement deleteMemberQuery = conn.prepareStatement(deleteMember);
                    deleteMemberQuery.setString(1, mail_user);
                    deleteMemberQuery.executeUpdate(); //On supprime le membre de notre système

                    String updateIdUser= "INSERT INTO utilisateur (id_user) VALUES (?)";
                    PreparedStatement updateIdUserQuery = conn.prepareStatement(updateIdUser);
                    updateIdUserQuery.setInt(1,newIdUser);
                    updateIdUserQuery.executeUpdate();

                    String updateIdUserAdherent= "UPDATE adherent SET id_user = ? WHERE id_user = ? ";
                    PreparedStatement updateIdUserAdherentQuery = conn.prepareStatement(updateIdUserAdherent);
                    updateIdUserAdherentQuery.setInt(1,newIdUser);
                    updateIdUserAdherentQuery.setInt(2,user.getIdUser());
                    updateIdUserAdherentQuery.executeUpdate();

                    String updateIdUserReservationRefuge= "UPDATE reservation_refuge SET id_user = ? WHERE id_user = ? ";
                    PreparedStatement updateIdUserReservationRefugeQuery = conn.prepareStatement(updateIdUserReservationRefuge);
                    updateIdUserReservationRefugeQuery.setInt(1,newIdUser);
                    updateIdUserReservationRefugeQuery.setInt(2,user.getIdUser());
                    updateIdUserReservationRefugeQuery.executeUpdate();

                    String updateIdUserQuantiteRepas= "UPDATE quantite_repas SET id_user = ? WHERE id_user = ? ";
                    PreparedStatement updateIdUserQueryQuantiteRepas = conn.prepareStatement(updateIdUserQuantiteRepas);
                    updateIdUserQueryQuantiteRepas.setInt(1,newIdUser);
                    updateIdUserQueryQuantiteRepas.setInt(2,user.getIdUser());
                    updateIdUserQueryQuantiteRepas.executeUpdate();

                    String deleteIdUser= "DELETE FROM utilisateur WHERE id_user = ? ";
                    PreparedStatement deleteIdUserQuery = conn.prepareStatement(deleteIdUser);
                    deleteIdUserQuery.setInt(1,user.getIdUser());
                    deleteIdUserQuery.executeUpdate();



                    System.out.println("Oups !!! Ça nous fait vraiment de la peine de vous partir !! ");
                    conn.commit();
                    conn.close();
                } else if (choice.equals("2")) {
                    menu();
                }
            }while (!choice.equals("1") && !choice.equals("2"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
