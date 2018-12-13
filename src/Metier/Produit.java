package Metier;

public class Produit implements I_Produit{
    private int quantiteStock;
    private String nom;
    private double prixUnitaireHT;
    private double tauxTVA = 0.2;

    public Produit(String nom, double prixUnitaireHT, int quantiteStock){
        this.nom = nom;
        this.prixUnitaireHT = prixUnitaireHT;
        this.quantiteStock = quantiteStock;
    }

    @Override
    public boolean ajouter(int qteAchetee) {
        return false;
    }

    @Override
    public boolean enlever(int qteVendue) {
        return false;
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
        return (prixUnitaireHT*tauxTVA)+prixUnitaireHT;
    }

    @Override
    public double getPrixStockTTC() {
        return 0;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
