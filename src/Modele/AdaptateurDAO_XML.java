package Modele;

import Controleur.ControleurCreate;
import Metier.Catalogue;
import Metier.I_Catalogue;
import Metier.I_Produit;
import Metier.Produit;

import java.sql.SQLException;
import java.util.List;

public class AdaptateurDAO_XML implements I_ProduitDAO{
    private ProduitDAO_XML produitDAO_xml = new ProduitDAO_XML();
    @Override
    public boolean addProduit(I_Produit produit) throws ClassNotFoundException {
        return produitDAO_xml.creer(produit);
    }

    @Override
    public boolean updateProduit(I_Produit produit) throws SQLException, ClassNotFoundException {
        return produitDAO_xml.maj(produit);
    }

    @Override
    public boolean deleteProduit(I_Produit produit) throws SQLException, ClassNotFoundException {
        return produitDAO_xml.supprimer(produit);
    }

    @Override
    public List<I_Produit> getListeProduits() throws ClassNotFoundException {
        return  produitDAO_xml.lireTous();
    }

    @Override
    public I_Produit getUnProduit(String nom) throws SQLException, ClassNotFoundException {
        return produitDAO_xml.lire(nom);
    }
}
