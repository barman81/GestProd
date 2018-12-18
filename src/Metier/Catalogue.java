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
        lesProduits.add(produit);
        return true;
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
        lesProduits.addAll(l);
        return l.size();
    }

    @Override
    public boolean removeProduit(String nom) {
        if(lesProduits.contains((nom))) {
            for (I_Produit unProduit : lesProduits) {
                if (nom.equals(unProduit.getNom())) {
                    lesProduits.remove(unProduit);
                    return true;
                }
            }
        }
            return false;
    }

    @Override
    public boolean acheterStock(String nomProduit, int qteAchetee) {
        if(lesProduits.contains((nomProduit))) {
            I_Produit unProduit = lesProduits.get(lesProduits.indexOf(nomProduit));
            unProduit.ajouter(qteAchetee);
            return true;
        }
        return false;
    }

    @Override
    public boolean vendreStock(String nomProduit, int qteVendue) {
        if(lesProduits.contains((nomProduit))) {
            I_Produit unProduit = lesProduits.get(lesProduits.indexOf(nomProduit));
            if(unProduit.getQuantite() >= qteVendue) {
                unProduit.enlever(qteVendue);
                return true;
            }
        }
        return false;
    }

    @Override
    public String[] getNomProduits() {
        String noms[] = {};
        int i=0;
        for(I_Produit unProduit :lesProduits){
            noms[i] =(unProduit.getNom());
            i++;
        }
        return noms;
    }

    @Override
    public double getMontantTotalTTC() {
        double montant = 0;
        for(I_Produit unProduit :lesProduits){
            montant += unProduit.getPrixStockTTC();
        }
        return montant;
    }

    @Override
    public String toString() {
        return "la liste de produit contients : " + lesProduits;
    }

    @Override
    public void clear() {
        lesProduits.removeAll(lesProduits);
    }

}