
import java.sql.*;


import javax.xml.crypto.Data;



public class ReservationRefuge{
    private String UpdateNbPlaceRepas = "UPDATE Refuge SET nb_places_repas =  nb_places_repas - 1 WHERE nom_refuge = ?";
    private String UpdateNbPlaceNuits = "UPDATE Refuge SET nb_places_nuits =  nb_places_nuits - 1 WHERE nom_refuge = ?";
    private String CompteNombre = "SELECT date FROM reservation_refuge WHERE date_res_refuge == ?) ";

    public int testReservationRefuge(Connection conn,String nom_refuge,Timestamp res, Boolean manger, Boolean dormir,int  nb_nuit  ) {
        // return 0 si tout se passe bien
        // return 1 si il y a eu une date non conforme à l'intervalle
        // return 2 si le nombre de place pour les repas est à 0
        // return 3 si le nombre de place pour la nuit est à 0
        // return 4 si le nom est mauvais
        try {
            PreparedStatement stmt = conn.prepareStatement
                    ("select date_ouverture, date_fermeture from Refuge where nom = ? ");
            stmt.setString(1, nom_refuge);
            ResultSet rset = stmt.executeQuery();

            if (rset.next()) {
                if ( res.compareTo(rset.getDate("date_ouverture"))<0) {
                    System.out.println("Date fourni < date d'ouverture du refuge");
                    return 1;
                }
                if ( res.compareTo(rset.getDate("date_fermeture"))>0) {
                    System.out.println("Date fourni > date de fermeture du refuge");
                    return 1;
                }
            }
            stmt = conn.prepareStatement
                    ("SELECT  date_res_refuge - DATE ?,nb_nuits FROM reservation_refuge");
            stmt.setTimestamp(1,res);
            rset = stmt.executeQuery();

            int[] tabDateRes = new int[nb_nuit];
            while(rset.next()){
                int diffdate = rset.getInt(1);
                int nbNUit = rset.getInt(2);
                if ((diffdate<0) && (diffdate +nbNUit >0) ){
                    int fin = (diffdate+nbNUit+1<nb_nuit)?diffdate+nbNUit+1:nb_nuit;
                    for (int i=0;i<fin ;i++){
                        tabDateRes[i]+=1;
                    }
                }else if(diffdate>0){
                    for (int i=diffdate;i<nb_nuit;i++){
                        tabDateRes[i]+=1;
                    }
                }

            }


            if (manger && rset.getInt("nb_places_repas")==0){
                System.out.println("Désolé, il ne reste plus de place pour manger");
                return 2;
            }
            if (dormir && rset.getInt("nb_places_nuits")==0){
                System.out.println("Désolé, il ne reste plus de place pour dormir");
                return 3;
            }
            if (dormir){
                stmt = conn.prepareStatement(UpdateNbPlaceNuits);
                stmt.setString(1, nom_refuge);
                stmt.executeUpdate();
            }
            if (manger){
                stmt = conn.prepareStatement(UpdateNbPlaceRepas);
                stmt.setString(1, nom_refuge);
                stmt.executeUpdate();
            }
            return 0;
        }catch (SQLException e){
            System.err.println("Verifie le nom du refuge fourni, il ne correspond à aucun refuge");
            e.printStackTrace(System.err);
            return 4;


        }
    }

    public void insertReserveRefuge(Connection conn,int userid,String mail,Timestamp date,int nb_nuit){
        try {


            PreparedStatement stmt = conn.prepareStatement
                    ("Insert Into RESERVATION_REFUGE(?,?,?,?,?)");
            stmt.setInt(1, userid);
            stmt.setString(2, mail);
            stmt.setTimestamp(3, date);
            stmt.setInt(5, nb_nuit);
            int rset = stmt.executeUpdate();
            System.out.println("Reservation effectué, il ne vous reste plus qu'à payer");
        }catch (SQLException e) {
            System.err.println("failed");
            e.printStackTrace(System.err);
        }
    }

}