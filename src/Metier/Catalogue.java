package Metier;
import java.util.ArrayList;
import java.util.List;

public class Catalogue implements I_Catalogue {
    private ArrayList<I_Produit> lesProduits = new ArrayList<I_Produit>();

    public Catalogue(){
    }

    @Override
    public boolean addProduit(I_Produit produit) {
        lesProduits
        return false;
    }

    @Override
    public boolean addProduit(String nom, double prix, int qte) {
        Produit p1 = new Produit(nom, prix, qte);
        return true;
    }

    @Override
    public int addProduits(List<I_Produit> l) {
        return 0;
    }

    @Override
    public boolean removeProduit(String nom) {
        return false;
    }

    @Override
    public boolean acheterStock(String nomProduit, int qteAchetee) {
        return false;
    }

    @Override
    public boolean vendreStock(String nomProduit, int qteVendue) {
        return false;
    }

    @Override
    public String[] getNomProduits() {
        return new String[0];
    }

    @Override
    public double getMontantTotalTTC() {
        return 0;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public void clear() {

    }

}
