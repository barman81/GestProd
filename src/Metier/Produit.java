package Metier;
import java.math.BigDecimal;

public class Produit implements I_Produit{
    private int quantiteStock;
    private String nom;
    private double prixUnitaireHT;
    private double tauxTVA = 0.2;

    public Produit(String nom, double prixUnitaireHT, int quantiteStock){
        this.nom = ajusterNomProduit(nom);
        //this.nom = nom;
        this.prixUnitaireHT = prixUnitaireHT;
        this.quantiteStock = quantiteStock;
    }

    /**
     * Permet d'ajuster le nom du produit en elevant les espaces, les tabulations en debut milieu et fin.
     * @param nom
     * @return String nomProduit
     */
    private String ajusterNomProduit(String nom) {
        nom = nom.replaceAll("\\s+$", ""); //debut
        nom = nom.replaceAll("^\\s+", ""); //fin
        nom = nom.replaceAll("\\t", " "); //tabulation

        return nom;
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
        return (prixUnitaireHT*tauxTVA)+prixUnitaireHT;

    }

    @Override
    public double getPrixStockTTC() {
        return getPrixUnitaireTTC()*quantiteStock;
    }

    @Override
    public String toString() {
        return "le produit "+ this.nom +" coute " +this.prixUnitaireHT +" HT et est en " +this.quantiteStock +" exemplaire.";
    }
}
