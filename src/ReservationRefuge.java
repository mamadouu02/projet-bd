import java.sql.*;

public class ReservationRefuge {

    private String mailRefuge;

    // return 0 si tout se passe bien
    // return 1 si il y a eu une date non conforme à l'intervalle
    // return 2 si le nombre de place pour les repas est à 0
    // return 3 si le nombre de place pour la nuit est à 0
    // return 4 si le nom est mauvais
    public int testReservationRefuge(Connection conn, String nomRefuge, Date res, Boolean manger, Boolean dormir, int nbNuits, String[] repasVoulu) {
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT date_ouverture, date_fermeture, mail_refuge, nb_places_nuits, nb_places_repas FROM refuge WHERE nom_refuge = ?");
            stmt.setString(1, nomRefuge);
            int nbRepastot = 9;
            ResultSet rset = stmt.executeQuery();
            int nbPlacesNuits = 0;

            if (rset.next()) {
                nbPlacesNuits = rset.getInt(4);
                nbRepastot = rset.getInt(5);
                mailRefuge = rset.getString("mail_refuge");

                if (res.compareTo(rset.getDate("date_ouverture")) < 0) {
                    System.out.println("\nDate fournie < Date d'ouverture du refuge (" + rset.getDate(1) + ")");
                    return 1;
                }

                if (res.compareTo(rset.getDate("date_fermeture")) > 0) {
                    System.out.println("\nDate fournie < Date d'ouverture du refuge (" + rset.getDate(2) + ")");
                    return 1;
                }
            }

            System.out.println(nbPlacesNuits);
            PreparedStatement stmt2 = conn.prepareStatement("SELECT date_res_refuge - ?, nb_nuits FROM reservation_refuge WHERE mail_refuge = ?");
            stmt2.setDate(1, res);
            stmt2.setString(2, mailRefuge);
            ResultSet rset2 = stmt2.executeQuery();
            int[] tabDateRes = new int[nbNuits];

            while (rset2.next()) {
                int diffdate = rset2.getInt(1);
                int nbNuit = rset2.getInt(2);

                if ((diffdate < 0) && (diffdate + nbNuit > 0)) {
                    int fin = (diffdate + nbNuit + 1 < nbNuits) ? diffdate + nbNuit + 1 : nbNuits;

                    for (int i = 0; i < fin; i++) {
                        tabDateRes[i] += 1;
                    }
                } else if (diffdate >= 0) {
                    for (int i = diffdate; i < nbNuits; i++) {
                        tabDateRes[i] += 1;
                    }
                }
            }

            Boolean placeRestante = true;

            for (int i = 0; i < nbNuits; i++) {

                if (tabDateRes[i] >= nbPlacesNuits) {
                    placeRestante = false;
                    System.out.println("Désolé, pour votre jour " + i + " le refuge est complet.");
                    break;
                }
            }

            if (dormir & !placeRestante) {
                return 3;
            }

            if (manger) {
                for (int i = 0; i < 4; i++) {
                    int compte = 1;

                    if (repasVoulu[i] == null) {

                    } else {
                        PreparedStatement stmt3 = conn.prepareStatement("SELECT nb_repas FROM quantite_repas WHERE mail_refuge = ? AND type_repas = ?");
                        stmt3.setString(1, mailRefuge);
                        stmt3.setString(2, repasVoulu[i]);

                        ResultSet rset3 = stmt3.executeQuery();

                        while (rset3.next()) {
                            compte += rset3.getInt(1);
                        }

                        if (compte >= nbRepastot) {
                            System.out.println("Désolé, il ne reste plus de " + repasVoulu[i]);
                            return 2;
                        }

                    }

                }
            }

            return 0;
        } catch (SQLException e) {
            e.printStackTrace(System.err);
            return 4;
        }
    }

    public void insertReserveRefuge(Connection conn, int idUser, String mail, Date date, String heureRes, int nbNuits) {
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO reservation_refuge VALUES (?, ?, ?, ?, ?)");
            stmt.setInt(1, idUser);
            stmt.setString(2, mailRefuge);
            stmt.setDate(3, date);
            stmt.setString(4, heureRes);
            stmt.setInt(5, nbNuits);
            stmt.executeUpdate();
            System.out.println("\nReservation effectuée, il ne vous reste plus qu'à payer.");
            conn.commit();
        } catch (SQLException e) {
            System.err.println("failed");
            e.printStackTrace(System.err);
        }
    }
}
