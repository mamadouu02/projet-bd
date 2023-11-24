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
}