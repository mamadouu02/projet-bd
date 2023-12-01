import java.sql.*;
public class Repas {
    private Boolean dejeuner =false;
    private Boolean casse_croute =false;
    private Boolean diner =false;
    private Boolean souper=false ;

    public Repas() {

    }
    public Repas(Boolean dejeuner, Boolean casse_croute ,Boolean diner, Boolean souper) {
        this.dejeuner = dejeuner;
        this.casse_croute = casse_croute;
        this.diner= diner;
        this.souper =souper;
    }
    public Boolean getDejeuner() {
        return dejeuner;
    }
    public Boolean getCasse_croute() {
        return casse_croute;
    }
    public Boolean getDiner() {
        return diner;
    }
    public Boolean getSouper() {
        return souper;
    }
    public Repas repasDisponible(Connection conn,String nom_refuge){
        try {
            PreparedStatement stmt = conn.prepareStatement
                    ("select type_repas from Refuge re JOIN Repas rp ON re.mail_refuge = rp.mail_refuge where nom_refuge = ? ");
            stmt.setString(1, nom_refuge);
            ResultSet rset = stmt.executeQuery();
            while(rset.next()){
                if (rset.getString(1).compareTo("déjeuner")==0){
                    this.dejeuner = true;
                } else if (rset.getString(1).compareTo("casse-croûte")==0){
                    this.casse_croute = true;
                }else if (rset.getString(1).compareTo("dîner")==0){
                    this.diner = true;
                }
                else if (rset.getString(1).compareTo("souper")==0){
                    this.souper = true;
                }
            }
            Repas repasRefuge = new Repas(this.dejeuner,this.casse_croute,this.diner,this.souper);
            rset.close();
            stmt.close();
            return repasRefuge;
        }catch (SQLException e) {
            //System.err.println("Verifie le nom du refuge fourni, il ne correspond à aucun refuge");
            e.printStackTrace(System.err);
            return null;

        }
    }

}
