package Metier;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Catalogue implements I_Catalogue {
    private ArrayList<I_Produit> lesProduits = new ArrayList<I_Produit>();

    public Catalogue(){
    }

    @Override
    public boolean addProduit(I_Produit produit) {
        return false;
    }

    @Override
    public boolean addProduit(String nom, double prix, int qte) {
        if(!lesProduits.contains(nom)){
            Produit p1 = new Produit(nom, prix, qte);
            lesProduits.add(p1);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int addProduits(List<I_Produit> l) {
        return 0;
    }

    @Override
    public boolean removeProduit(String nom) {
        for (I_Produit unProduit : lesProduits) {
            if (nom.equals(unProduit.getNom())) {
                lesProduits.remove(unProduit);
            }
        }
        return true;
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
