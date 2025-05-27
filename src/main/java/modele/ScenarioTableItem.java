package modele;

public class ScenarioTableItem {

    private String vendeur;
    private String acheteur;

    public ScenarioTableItem(String vendeur, String acheteur) {
        this.vendeur = vendeur;
        this.acheteur = acheteur;
    }

    public String getVendeur() {
        return vendeur;
    }

    public String getAcheteur() {
        return acheteur;
    }

    public void setVendeur(String vendeur) {
        this.vendeur = vendeur;
    }

    public void setAcheteur(String acheteur) {
        this.acheteur = acheteur;
    }
}
