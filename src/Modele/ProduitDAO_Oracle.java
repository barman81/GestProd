package Modele;

import Affichage.FenetreAffichage;
import Controleur.ControleurCreate;
import Metier.Catalogue;
import Metier.I_Catalogue;
import Metier.I_Produit;
import Metier.Produit;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProduitDAO_Oracle implements I_ProduitDAO{

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
    public boolean addProduit(I_Produit produit) throws  ClassNotFoundException {
        boolean result = true;
        try {
            seConnecter();
            CallableStatement callableStatement = cn.prepareCall("CALL GESTPROD_ADDPRODUCT (?,?,?)");
            callableStatement.setString(1, produit.getNom());
            callableStatement.setDouble(2, produit.getPrixUnitaireHT());
            callableStatement.setInt(3, produit.getQuantite());
            callableStatement.executeQuery();
            seDeconnecter();
        }catch(SQLException e){
            result = false;
        }
        return result;
    }

    @Override
    public boolean updateProduit(I_Produit produit) throws SQLException, ClassNotFoundException {
        boolean result = true;
        try {
            seConnecter();
            PreparedStatement preparedStatement = cn.prepareStatement("UPDATE GESTPROD_PRODUIT(PRIX_UNITAIRE_HT = ?,QUANTITE_STOCK = ? where NOM_PRODUIT = ?");
            preparedStatement.setDouble(1, produit.getPrixUnitaireHT());
            preparedStatement.setInt(2, produit.getQuantite());
            preparedStatement.setString(3, produit.getNom());
            preparedStatement.executeQuery();
            //st.executeUpdate("UPDATE GESTPROD_PRODUIT( NOM_PRODUIT  = " + produit.getNom()+ ", QUANTITE_STOCK = " + produit.getQuantite() + ")");
            seDeconnecter();
        }catch(SQLException e){
            result = false;
        }
        return result;
    }

    @Override
    public boolean deleteProduit(I_Produit produit) throws SQLException, ClassNotFoundException {
        boolean result = true;
        try {
            seConnecter();
            st.executeUpdate("DELETE FROM  GESTPROD_PRODUIT WHERE NOM_PRODUIT = "+ produit.getNom());
            seDeconnecter();
        }catch(SQLException e){
            result = false;
        }
        return result;
    }

    @Override
    public List<I_Produit> getListeProduits() throws ClassNotFoundException {
        List<I_Produit> listeProduits = new ArrayList<>();
        try {
            seConnecter();
            st.executeQuery("select NOM_PRODUIT, PRIX_UNITAIRE_HT, QUANTITE_STOCK from BARONM.GESTPROD_PRODUITS");
            ResultSet rs = st.getResultSet();

            while(rs.next()){
                String nom = rs.getString("NOM_PRODUIT");
                double prixUnitaireHT = rs.getDouble("PRIX_UNITAIRE_HT");
                int quantiteStock = rs.getInt("QUANTITE_STOCK");
//                cat.addProduit(nom, prixUnitaireHT, quantiteStock);
                I_Produit produit = new Produit(nom, prixUnitaireHT, quantiteStock);
                listeProduits.add(produit);
            }
            seDeconnecter();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return listeProduits;
    }

    @Override
    public I_Produit getUnProduit(String nom) throws SQLException, ClassNotFoundException {
        seConnecter();
        I_Produit produit = null;
        PreparedStatement preparedStatement = cn.prepareStatement("select NOM_PRODUIT, PRIX_UNITAIRE_HT, QUANTITE_STOCK from BARONM.GESTPROD_PRODUITS where NOM_PRODUIT = ?");
        preparedStatement.setString(1, nom);
        preparedStatement.executeQuery();
        ResultSet rs = preparedStatement.getResultSet();
        if(rs.next()){
        double prixUnitaireHT = rs.getDouble("PRIX_UNITAIRE_HT");
        int quantiteStock = rs.getInt("QUANTITE_STOCK");
        produit = new Produit(nom, prixUnitaireHT, quantiteStock);

        }
        return produit;
    }

}
