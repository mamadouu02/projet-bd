
import java.sql.*;
import java.util.Calendar;

import javax.xml.crypto.Data;



public class ReservationRefuge{
    private String mail_refuge;

    public int testReservationRefuge(Connection conn,String nom_refuge,Date res, Boolean manger, Boolean dormir,int  nb_nuit , String[] RepasVoulu ) {
        // return 0 si tout se passe bien
        // return 1 si il y a eu une date non conforme à l'intervalle
        // return 2 si le nombre de place pour les repas est à 0
        // return 3 si le nombre de place pour la nuit est à 0
        // return 4 si le nom est mauvais
        try {
            PreparedStatement stmt = conn.prepareStatement
                    ("select date_ouverture, date_fermeture,mail_refuge,nb_places_nuits,nb_places_repas from Refuge where nom_refuge = ? ");
            stmt.setString(1, nom_refuge);
            int nbRepastot= 9;
            ResultSet rset = stmt.executeQuery();
            int nb_places_nuits = 0;
            if (rset.next()) {
                nb_places_nuits  = rset.getInt(4);
                nbRepastot = rset.getInt(5);

                mail_refuge  = rset.getString("mail_refuge");
                if ( res.compareTo(rset.getDate("date_ouverture"))<0) {
                    System.out.println("Date fournie < date d'ouverture du refuge");
                    return 1;
                }
                if ( res.compareTo(rset.getDate("date_fermeture"))>0) {
                    System.out.println("Date fournie > date de fermeture du refuge");
                    return 1;
                }
            }
            System.out.println(nb_places_nuits);
            PreparedStatement stmt2 = conn.prepareStatement
                    ("SELECT date_res_refuge -  ? ,nb_nuits from reservation_refuge where mail_refuge = ?");
            stmt2.setDate(1,res);
            stmt2.setString(2,mail_refuge);
            ResultSet rset2 = stmt2.executeQuery();
            int[] tabDateRes = new int[nb_nuit];

            while(rset2.next()){
                int diffdate = rset2.getInt(1);
                int nbNUit = rset2.getInt(2);

                if ((diffdate<0) && (diffdate +nbNUit >0) ){
                    int fin = (diffdate + nbNUit+1<nb_nuit)?diffdate+nbNUit+1:nb_nuit;
                    for (int i=0;i<fin ;i++){
                        tabDateRes[i]+=1;
                    }
                }else if(diffdate>0){
                    for (int i=diffdate;i<nb_nuit;i++){
                        tabDateRes[i]+=1;
                    }
                }

            }
            Boolean place_restante = true;
            for (int i=0;i<nb_nuit;i++){

                if (tabDateRes[i]>= nb_places_nuits) {
                    place_restante = false;
                    System.out.println("Désolé, Pour votre jour " +i+ " le refuge est complet");
                    break;
                }
            }

            if (dormir & !place_restante){
                return 3;
            }
            if (manger){
                for (int i=0;i<4;i++){
                    int compte = 1;
                    if(RepasVoulu[i]==null) {

                    }else{
                        PreparedStatement stmt3 = conn.prepareStatement
                                ("SELECT nb_repas from quantite_repas where mail_refuge = ? And type_repas = ?");
                        stmt3.setString(1,mail_refuge);
                        stmt3.setString(2,RepasVoulu[i]);

                        ResultSet rset3 = stmt3.executeQuery();
                        while (rset3.next()) {
                            compte += rset3.getInt(1);
                            }
                        if (compte >= nbRepastot) {
                            System.out.println("Désolé il ne reste plus de " + RepasVoulu[i]);
                            return 2;
                        }

                    }

                }
            }



            return 0;
        }catch (SQLException e){
            //System.err.println("Verifie le nom du refuge fourni, il ne correspond à aucun refuge");
            e.printStackTrace(System.err);
            return 4;


        }
    }

    public void insertReserveRefuge(Connection conn,int userid,String mail,Date date,String heure_res,int nb_nuit){
        try {


            PreparedStatement stmt = conn.prepareStatement
                    ("Insert Into reservation_refuge Values(?,?,?,?,?)");
            stmt.setInt(1, userid);
            stmt.setString(2, mail_refuge);
            stmt.setDate(3, date);
            stmt.setString(4, heure_res);
            stmt.setInt(5, nb_nuit);
            stmt.executeUpdate();
            System.out.println("\nReservation effectuée, il ne vous reste plus qu'à payer");
            conn.commit();
        }catch (SQLException e) {
            System.err.println("failed");
            e.printStackTrace( System.err);
        }
    }

}