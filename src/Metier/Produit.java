package Metier;

public class Produit implements I_Produit{
    private int quantiteStock;
    private String nom;
    private double prixUnitaireHT;
    private double tauxTVA = 0.2;

    public Produit(String nom, double prixUnitaireHT, int quantiteStock){
        nom = nom.replaceAll("\\s", "");
        this.nom = nom;
        this.prixUnitaireHT = prixUnitaireHT;
        this.quantiteStock = quantiteStock;
    }

    @Override
    public boolean ajouter(int qteAchetee) {
        this.quantiteStock += qteAchetee;
        return true;
    }

    @Override
    public boolean enlever(int qteVendue) {
        if(this.quantiteStock-qteVendue >= 0){
            this.quantiteStock -= qteVendue;
            return true;
        }else{
            return false;
        }

    }

    @Override
    public String getNom() {
        return nom;
    }

    @Override
    public int getQuantite() {
        return quantiteStock;
    }

    @Override
    public double getPrixUnitaireHT() {
        return prixUnitaireHT;
    }

    @Override
    public double getPrixUnitaireTTC() {
        //return (prixUnitaireHT*tauxTVA)+prixUnitaireHT;
        return (prixUnitaireHT*(1+(tauxTVA/100)));
    }

    @Override
    public double getPrixStockTTC() {
        //return ((prixUnitaireHT*tauxTVA)+prixUnitaireHT)*quantiteStock;
        return getPrixUnitaireTTC()*quantiteStock;
    }

    @Override
    public String toString() {
        return "le produit "+ this.nom +" coute " +this.prixUnitaireHT +" HT et est en " +this.quantiteStock +" exemplaire.";
    }
}
