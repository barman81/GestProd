package Modele;

import java.sql.SQLException;

public interface I_ProduitDAO {

    boolean addProduit(String nom, double prixUnitaireHT, int quantiteStock) throws ClassNotFoundException;

    boolean updateProduit(String nom, double prixUnitaireHT, int quantiteStock) throws SQLException, ClassNotFoundException;

    boolean deleteProduit(String nom) throws SQLException, ClassNotFoundException;

    void getListeProduits() throws ClassNotFoundException;
}
