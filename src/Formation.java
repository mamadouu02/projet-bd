import java.sql.Date;

public class Formation {
    
    private int annee;
    private int rang;
    private String nom;
    private Date date;
    private int duree;
    private int nbPlacesFormation;

    public Formation(int annee, int rang, String nom, Date date, int duree, int nbPlacesFormation) {
        this.annee = annee;
        this.rang = rang;
        this.nom = nom;
        this.date = date;
        this.duree = duree;
        this.nbPlacesFormation = nbPlacesFormation;
    }

    public int getAnnee() {
        return annee;
    }

    public int getRang() {
        return rang;
    }
    
    public String getNom() {
        return nom;
    }
    
    public Date getDate() {
        return date;
    }
    
    public int getDuree() {
        return duree;
    }

    public int getNbPlacesFormation() {
        return nbPlacesFormation;
    }
}
