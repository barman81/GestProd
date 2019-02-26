package Modele;

import Affichage.FenetreAffichage;
import Controleur.ControleurCreate;
import Metier.Catalogue;
import Metier.I_Catalogue;
import Metier.I_Produit;
import java.sql.*;
import java.util.ArrayList;

public class ProduitDAO_Oracle implements I_ProduitDAO{

    private static I_Catalogue cat = ControleurCreate.cat;
    private final String url = "jdbc:oracle:thin:@162.38.222.149:1521:iut";
    private final String login = "baronm";
    private final String pdw = "1609013792K";
    private Connection cn;
    private Statement st;


    private void seConnecter() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        cn = DriverManager.getConnection(url, login, pdw);
        st = cn.createStatement();
    }

    private void seDeconnecter() throws SQLException {
        st.close();
        cn.close();
    }

    @Override
    public boolean addProduit(String nom, double prixUnitaireHT, int quantiteStock) throws  ClassNotFoundException {
       boolean result = true;
        try {
           seConnecter();
           st.executeUpdate("INSERT INTO GESTPROD_PRODUIT (NOM_PRODUIT, PRIX_UNITAIRE_HT, QUANTITE_STOCK) VALUES (" + nom + "," + prixUnitaireHT + ", " + quantiteStock + ")");
           seDeconnecter();
       }catch(SQLException e){
           result = false;
       }
        return result;
    }

    @Override
    public boolean updateProduit(String nom, double prixUnitaireHT, int quantiteStock) throws SQLException, ClassNotFoundException {
        boolean result = true;
        try {
            seConnecter();
            st.executeUpdate("CALL GESTPROD_ADDPRODUCT(" + nom + ", " + prixUnitaireHT + ", " + quantiteStock + ")");
            seDeconnecter();
        }catch(SQLException e){
            result = false;
        }
        return result;
    }

    @Override
    public boolean deleteProduit(String nom) throws SQLException, ClassNotFoundException {
        boolean result = true;
        try {
            seConnecter();
            st.executeUpdate("DELETE FROM  GESTPROD_PRODUIT WHERE NOM_PRODUIT = "+ nom);
            seDeconnecter();
        }catch(SQLException e){
            result = false;
        }
            return result;
    }

    @Override
    public void getListeProduits() throws ClassNotFoundException {
        ArrayList<I_Produit> listeProduit = null;
        try {
            seConnecter();
            st.executeQuery("select NOM_PRODUIT, PRIX_UNITAIRE_HT, QUANTITE_STOCK from BARONM.GESTPROD_PRODUITS");
            ResultSet rs = st.getResultSet();

            while(rs.next()){
                String nom = rs.getString("NOM_PRODUIT");
                double prixUnitaireHT = rs.getDouble("PRIX_UNITAIRE_HT");
                int quantiteStock = rs.getInt("QUANTITE_STOCK");
                cat.addProduit(nom, prixUnitaireHT, quantiteStock);
            }
            seDeconnecter();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

}
