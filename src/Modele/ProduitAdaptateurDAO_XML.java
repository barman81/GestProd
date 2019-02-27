package Modele;

import Controleur.ControleurCreate;
import Metier.Catalogue;
import Metier.I_Catalogue;
import Metier.I_Produit;
import Metier.Produit;

import java.sql.SQLException;
import java.util.List;

public class ProduitAdaptateurDAO_XML implements I_ProduitDAO{
    private ProduitDAO_XML produitDAO_xml = new ProduitDAO_XML();
    @Override
    public boolean addProduit(I_Produit produit, I_Catalogue catalogue) throws ClassNotFoundException {
        return produitDAO_xml.creer(produit);
    }

    @Override
    public boolean updateProduit(I_Produit produit, I_Catalogue catalogue) throws SQLException, ClassNotFoundException {
        return produitDAO_xml.maj(produit);
    }

    @Override
    public boolean deleteProduit(I_Produit produit,I_Catalogue catalogue) throws SQLException, ClassNotFoundException {
        return produitDAO_xml.supprimer(produit);
    }

    @Override
    public List<I_Produit> getListeProduits(I_Catalogue catalogue) throws ClassNotFoundException {
        return  produitDAO_xml.lireTous();
    }

    @Override
    public I_Produit getUnProduit(String nom, I_Catalogue catalogue) throws SQLException, ClassNotFoundException {
        return produitDAO_xml.lire(nom);
    }
}
