package Modele;
import Metier.I_Catalogue;
import Metier.I_Produit;
import Metier.Produit;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProduitDao {

    I_Catalogue cat;
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

    public boolean addProduit(String nom, double prixUnitaireHT, int quantiteStock) throws  ClassNotFoundException {
       boolean result = true;
        try {
           seConnecter();
           st.executeUpdate("INSERT INTO Gestprod_produit (nom, prix_UnitaireHT,quantite_Stock) VALUES (" + nom + "," + prixUnitaireHT + ", " + quantiteStock + ")");
           seDeconnecter();
       }catch(SQLException e){
           result = false;
       }
        return result;
    }

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

    public boolean deleteProduit(String nom) throws SQLException, ClassNotFoundException {
        boolean result = true;
        try {
            seConnecter();
            st.executeUpdate("DELETE FROM  Gestprod/produit WHERE nom = "+ nom);
            seDeconnecter();
        }catch(SQLException e){
            result = false;
        }
            return result;
    }

    public ArrayList<I_Produit> getListeProduits() throws ClassNotFoundException {
        ArrayList<I_Produit> listeProduit = null;
        try {
            seConnecter();
            st.executeQuery("select nom, prixUnitaireHT, quantiteStock from Gestprod/produit");
            ResultSet rs = st.getResultSet();

            if(rs.next()){
                String nom = rs.getString("nom");
                double prixUnitaireHT = rs.getDouble("prix_UnitaireHT");
                int quantiteStock = rs.getInt("quantite_Stock");
                cat.addProduit(nom, prixUnitaireHT, quantiteStock);
            }
            seDeconnecter();
        }catch(SQLException e){

        }
        return listeProduit;
    }

}
