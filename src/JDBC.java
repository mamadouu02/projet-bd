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
        reservationMat reservation = new reservationMat(this.connection);
        System.out.println("Tapez exit pour revenir au menu\n");
        System.out.println("Marque ?\n");
        getCmd();
        while (!cmd.equals("exit")) {
            String marque = cmd;
            System.out.println("Modele ?\n");
            getCmd();
            String modele = cmd;
            System.out.println("Anne ? \n");
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
        String getPsswrd= "SELECT password FROM Membre WHERE mail_user = ? ;";
        String getIdUser= "SELECT id_user FROM Utilisateur WHERE mail_user = ? ;";

        try{
            PreparedStatement getPsswrdSQL = connection.prepareStatement(getPsswrd);
            getPsswrdSQL.setString(1,email);
            ResultSet result= getPsswrdSQL.executeQuery();
            if(psswrd != result.getString("password")){
                System.out.println("MAUVAIS PASSWORD\n");
                textConnectionUser();
            }
            else{
                PreparedStatement getIdUserSQL = connection.prepareStatement(getIdUser);
                getIdUserSQL.setString(1,email);
                ResultSet resultID= getIdUserSQL.executeQuery();
                idUser=result.getInt("ID_USER");
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
