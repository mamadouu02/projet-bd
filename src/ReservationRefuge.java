
import java.sql.*;
import java.util.Calendar;

import javax.xml.crypto.Data;



public class ReservationRefuge{
    private String UpdateNbPlaceRepas = "UPDATE Refuge SET nb_places_repas =  nb_places_repas - 1 WHERE nom_refuge = ?";
    private String UpdateNbPlaceNuits = "UPDATE Refuge SET nb_places_nuits =  nb_places_nuits - 1 WHERE nom_refuge = ?";
    private String CompteNombre = "SELECT date FROM reservation_refuge WHERE date_res_refuge == ? ";
    private String mail_refuge;

    public int testReservationRefuge(Connection conn,String nom_refuge,Date res, Boolean manger, Boolean dormir,int  nb_nuit  ) {
        // return 0 si tout se passe bien
        // return 1 si il y a eu une date non conforme à l'intervalle
        // return 2 si le nombre de place pour les repas est à 0
        // return 3 si le nombre de place pour la nuit est à 0
        // return 4 si le nom est mauvais
        try {
            PreparedStatement stmt = conn.prepareStatement
                    ("select date_ouverture, date_fermeture,mail_refuge,nb_places_nuits from Refuge where nom_refuge = ? ");
            System.out.println(nom_refuge);
            stmt.setString(1, nom_refuge);
            
            ResultSet rset = stmt.executeQuery();
            int nb_places_nuits = 0; 
            if (rset.next()) {
                nb_places_nuits  = rset.getInt(4);
                 mail_refuge  = rset.getString("mail_refuge");
                if ( res.compareTo(rset.getDate("date_ouverture"))<0) {
                    System.out.println("Date fourni < date d'ouverture du refuge");
                    return 1;
                }
                if ( res.compareTo(rset.getDate("date_fermeture"))>0) {
                    System.out.println("Date fourni > date de fermeture du refuge");
                    return 1;
                }
            }
            PreparedStatement stmt2 = conn.prepareStatement
                    ("SELECT date_res_refuge -  ? ,nb_nuits from reservation_refuge where mail_refuge = ?");
            stmt2.setDate(1,res);
            stmt2.setString(2,mail_refuge);
            ResultSet rset2 = stmt2.executeQuery();
            System.out.println(mail_refuge);
            int[] tabDateRes = new int[nb_nuit];
            while(rset2.next()){

                int diffdate = rset2.getInt(1);
                int nbNUit = rset2.getInt(2);
                if ((diffdate<0) && (diffdate +nbNUit >0) ){
                    long fin = (diffdate + nbNUit+1<nb_nuit)?diffdate+nbNUit+1:nb_nuit;
                    for (int i=0;i<fin ;i++){
                        tabDateRes[i]+=1;
                    }
                }else if(diffdate>0){
                    for (int i=diffdate;i<nb_nuit;i++){
                        tabDateRes[i]+=1;
                    }
                }

            }
            System.out.println(nb_places_nuits);
            Boolean place_restante = true;
            for (int i=0;i<nb_nuit;i++){
                if (tabDateRes[i]>= nb_places_nuits) {
                    place_restante = false;
                    System.out.println("Désolé, Pour votre jour" +i+ "le refuge est complet");
                    break;
                }
            }
            if (dormir & !place_restante){
                return 3;
            }
            /*
            if (dormir){
                stmt = conn.prepareStatement(UpdateNbPlaceNuits);
                stmt.setString(1, nom_refuge);
                stmt.executeUpdate();
            }
            if (manger){
                stmt = conn.prepareStatement(UpdateNbPlaceRepas);
                stmt.setString(1, nom_refuge);
                stmt.executeUpdate();
            }*/
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
            System.out.println(userid + mail_refuge + date + heure_res + nb_nuit);
            stmt.executeUpdate();
            System.out.println("Reservation effectué, il ne vous reste plus qu'à payer");
            conn.commit();
        }catch (SQLException e) {
            System.err.println("failed");
            e.printStackTrace( System.err);
        }
    }

}