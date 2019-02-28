package Modele;

import Metier.Catalogue;
import Metier.I_Catalogue;
import Metier.I_Produit;
import Metier.Produit;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CatalogueDAO_XML implements I_CatalogueDAO {
    private String uri = "C:/Catalogues.xml";
    private Document doc;

    public CatalogueDAO_XML() {
        SAXBuilder sdoc = new SAXBuilder();
        try {
            doc = sdoc.build(uri);
        } catch (Exception e) {
            System.out.println("erreur construction arbre JDOM");
        }
    }
    @Override
    public boolean addCatalogue(I_Catalogue catalogue) throws ClassNotFoundException {
        try {
            Element root = doc.getRootElement();
            Element prod = new Element("catalogue");
            prod.setAttribute("nom", catalogue.getNom());
            root.addContent(prod);
            return sauvegarde();
        } catch (Exception e) {
            System.out.println("erreur creer produit");
            return false;
        }
    }

    @Override
    public boolean deleteCatalogue(I_Catalogue catalogue) throws SQLException, ClassNotFoundException {
        try {
            Element root = doc.getRootElement();
            Element prod = chercheCatalogue(catalogue.getNom());
            if (prod != null) {
                root.removeContent(prod);
                return sauvegarde();
            } else
                return false;
        } catch (Exception e) {
            System.out.println("erreur supprimer produit");
            return false;
        }
    }

    @Override
    public List<I_Catalogue> getListeCatalogues() throws ClassNotFoundException {
        List<I_Catalogue> l = new ArrayList<I_Catalogue>();
        try {
            Element root = doc.getRootElement();
            List<Element> lProd = root.getChildren("catalogue");

            for (Element prod : lProd) {
                String nomP = prod.getAttributeValue("nom");
                l.add(new Catalogue(nomP));
            }
        } catch (Exception e) {
            System.out.println("erreur");
        }
        return l;
    }

    @Override
    public int getNbProduits(String nom) {
        return 0;
    }

    @Override
    public I_Catalogue getCatalogue(String texteSupprime) {
        return null;
    }

    private boolean sauvegarde() {
        System.out.println("Sauvegarde");
        XMLOutputter out = new XMLOutputter();
        try {
            out.output(doc, new PrintWriter(uri));
            return true;
        } catch (Exception e) {
            System.out.println("erreur sauvegarde dans fichier XML");
            return false;
        }
    }

    private Element chercheCatalogue(String nom) {
        Element root = doc.getRootElement();
        List<Element> lProd = root.getChildren("catalogue");
        int i = 0;
        while (i < lProd.size() && !lProd.get(i).getAttributeValue("nom").equals(nom))
            i++;
        if (i < lProd.size())
            return lProd.get(i);
        else
            return null;
    }
}
