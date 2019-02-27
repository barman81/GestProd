package Modele;

import Controleur.ControleurCatalogue;
import Metier.Catalogue;
import Metier.I_Catalogue;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CatalogueDAO implements I_CatalogueDAO{

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


    public boolean addCatalogue(I_Catalogue catalogue) throws  ClassNotFoundException {
        boolean result = true;
        try {
            seConnecter();
            CallableStatement callableStatement = cn.prepareCall("CALL GESTPROD_NEWCATALOGUE(?)");
            callableStatement.setString(1, catalogue.getNom());
            callableStatement.executeQuery();
            seDeconnecter();
        }catch(SQLException e){
            result = false;
        }
        return result;
    }


    public boolean deleteCatalogue(I_Catalogue catalogue) throws SQLException, ClassNotFoundException {
        boolean result = true;
        try {
            seConnecter();
            CallableStatement callableStatement = cn.prepareCall("CALL GESTPROD_DELETECATALOGUE(?)");
            callableStatement.setString(1, catalogue.getNom());
            callableStatement.executeQuery();
            seDeconnecter();
        }catch(SQLException e){
            result = false;
        }
        return result;
    }

    @Override
    public List<I_Catalogue> getListeCatalogues() throws ClassNotFoundException {
        List<I_Catalogue> listeCatalogues = new ArrayList<>();
        try {
            seConnecter();
            st.executeQuery("select * from BARONM.GESTPROD_CATALOGUES ");
            ResultSet rs = st.getResultSet();

            while(rs.next()){
                String nom = rs.getString("NOM_CATALOGUE");
                I_Catalogue catalogue = new Catalogue(nom);
                listeCatalogues.add(catalogue);
            }
            seDeconnecter();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return listeCatalogues;
    }

    @Override
    public int getNbProduits(String nom) {
        int nb = 0;
        try {
            seConnecter();
            PreparedStatement preparedStatement = cn.prepareStatement("select * from BARONM.GESTPROD_PRODUITS_CATALOGUES where ID_CATALOGUE = (select ID_CATALOGUE FROM BARONM.GESTPROD_CATALOGUES WHERE NOM_CATALOGUE = ?)");
            preparedStatement.setString(1, nom);
            preparedStatement.executeQuery();
            ResultSet rs = preparedStatement.getResultSet();

            while(rs.next()){
                nb++;
            }
            seDeconnecter();
        }catch(SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return nb;
    }

    @Override
    public I_Catalogue getCatalogue(String nom) {
        I_Catalogue catalogue =null;
        try {
            seConnecter();
            PreparedStatement preparedStatement = cn.prepareStatement("select * from BARONM.GESTPROD_CATALOGUE where NOM_CATALOGUE =  ?");
            preparedStatement.setString(1, nom);
            preparedStatement.executeQuery();
            ResultSet rs = preparedStatement.getResultSet();

            if(rs.next()){
                catalogue = new Catalogue(rs.getString("NOM_CATALOGUE"));
            }
            seDeconnecter();
        }catch(SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return catalogue;
    }


}
