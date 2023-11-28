public class Lot {

    private String marque;
    private String modele;
    private int anneeAchat;
    private int qteDispo;

    public Lot(String marque, String modele, int anneeAchat, int qteDispo) {
        this.marque = marque;
        this.modele = modele;
        this.anneeAchat = anneeAchat;
        this.qteDispo = qteDispo;
    }

    public String getMarque() {
        return marque;
    }

    public String getModele() {
        return modele;
    }

    public int getAnneeAchat() {
        return anneeAchat;
    }
}
