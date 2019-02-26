package Modele;
import java.sql.*;

public class ProduitDao {

    private final String url = "jdbc:oracle:thin:@162.38.222.149:1521:iut";
    private final String login = "guitardn";
    private final String pdw = "1108042381T";
    private Connection cn;
    private PreparedStatement ps;

    private void seConnecter() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        cn = DriverManager.getConnection(url, login,pdw);
    }

    private void seDeconnecter() throws SQLException {
        ps.close();
        cn.close();
    }

    public boolean addProduit(String nom, double prixUnitaireHT, int quantiteStock) throws SQLException, ClassNotFoundException {
        seConnecter();
        ps.execute("INSERT INTO produit (nom, prixUnitaireHT,quantiteStock) VALUES ("+ nom +","+ prixUnitaireHT+", " + quantiteStock +")");
        seDeconnecter();
        return true;
    }

    public boolean updateProduit(String nom, double prixUnitaireHT, int quantiteStock) throws SQLException, ClassNotFoundException {
        seConnecter();
        ps.execute("UPDATE produit set  prixUnitaireHT = "+ prixUnitaireHT +", quantiteStock = " +quantiteStock +" where nom =" + nom );
        seDeconnecter();
        return true;
    }

    public boolean deleteProduit(String nom) throws SQLException, ClassNotFoundException {
        seConnecter();
        ps.execute("DELETE FROM produit WHERE nom = "+ nom);
        seDeconnecter();
        return true;
    }
}
