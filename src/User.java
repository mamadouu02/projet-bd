public class User {

    private int idUser;
    private String mail;
    private String nom;
    private String prenom;
    private int idAdh;

    public User(int idUser, String mail, String nom, String prenom, int idAdh) {
        this.idUser = idUser;
        this.mail = mail;
        this.nom = nom;
        this.prenom = prenom;
        this.idAdh = idAdh;
    }

    public int getIdUser() {
        return idUser;
    }

    public String getMail() {
        return mail;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public int getIdAdh() {
        return idAdh;
    }
}
