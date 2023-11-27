import java.sql.Connection;
import java.util.Scanner;

public class InterfaceText {
    public String cmd;

    public Connection connection;


    public static void main(String[] args) {
        //Ouverture Connection
        JDBC jdbc= new JDBC();
        //Connection utilisateur
        jdbc.textConnectionUser();
        System.out.println("Pour faire une reservation tapez: reservation");
        while(true){
            System.out.println("Commande : ");
            jdbc.getCmd();
            if (jdbc.cmd.equals("reservation")){
                System.out.println("Refuge 1 | Formation 2 | Materiel 3");
                jdbc.getCmd();
                if (jdbc.cmd.equals("3")){
                    jdbc.textReservMat();
                }
                else{
                    System.out.println("Exited");
                }
            }
        }

    }
}
