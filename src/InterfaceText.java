import java.sql.Connection;

public class InterfaceText {

    public String cmd;
    public Connection connection;

    public static void main(String[] args) {
        // Ouverture Connexion
        JDBC jdbc = new JDBC();

        while (true) {
            System.out.println("Commande : ");
            jdbc.getCmd();

            if (jdbc.cmd.equals("reservation")) {
                System.out.println("Refuge 1 | Formation 2 | Materiel 3");
                jdbc.getCmd();

                if (jdbc.cmd.equals("3")) {
                    jdbc.textReservMat();
                } else {
                    System.out.println("Exited");
                }
            }

            else if (jdbc.cmd.equals("Parcours")) {
                System.out.println("Refuge 1 | Formation 2 | Materiel 3");
                jdbc.getCmd();

                if (jdbc.cmd.equals("1")) {
                    jdbc.parcoursRefuges();
                } else if (jdbc.cmd.equals("2")) {
                    jdbc.parcoursFormations();
                } else if (jdbc.cmd.equals("3")) {
                    jdbc.parcoursMateriels();
                } else {
                    System.out.println("Exited");
                }
            }
        }
    }
}
