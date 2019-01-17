package Metier;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;


public class Catalogue implements I_Catalogue {
	private ArrayList<I_Produit> lesProduits = new ArrayList<I_Produit>();

	/**
	 * Constructeur qui instancie un nouveau catalogue pouvant contenir des produits.
	 */
	public Catalogue(){
	}

	/**
	 * Méthode qui permet d'ajouter un produit.Acompleter...
	 * @param produit
	 * @return bool
	 */
	@Override
	public boolean addProduit(I_Produit produit) {
		if(produit != null && produit.getPrixUnitaireHT() >0)
		{
			lesProduits.add(produit);
			return true;
		}
		return false;
	}

	/**
	 * Méthode qui permet d'ajouter un produit, avec son nom, prix et qte.
	 * @param nom String
	 * @param prix double
	 * @param qte int
	 * @return bool
	 */
	@Override
	public boolean addProduit(String nom, double prix, int qte) {
		boolean bool = false;
		String test = nom.trim();
		Produit p1 = new Produit(test, prix, qte);
		if(lesProduits.contains(p1)) {
			lesProduits.add(p1);
			bool = true;
		}
		return bool;
	}

	/**
	 * Méthode qui permet d'ajouter des produits au catalogue depuis une liste de produits. Retourne le nombre de produits ajouter.
	 * @param l List<I_Produit>
	 * @return int
	 */
	@Override
	public int addProduits(List<I_Produit> l) {
		int i = 0;
		
		if(l!=null && !l.isEmpty()){
			for (I_Produit unProduit : l) {
				if(unProduit.getPrixUnitaireHT() != 0 && unProduit.getQuantite() >0) {
					if(!lesProduits.contains(unProduit)) {
						lesProduits.add(unProduit);		
						i++;
					}	
				}
			}
		}
		return i;
	}

	/**
	 * Méthode qui permet d'enlever un produit du catalogue via son nom.
	 * @param nom String
	 * @return bool
	 */
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

	/**
	 * Méthode qui permet d'ajouter un stock à un produit.
	 * @param nomProduit String
	 * @param qteAchetee  int
	 * @return bool
	 */
	@Override
	public boolean acheterStock(String nomProduit, int qteAchetee) {
		for (I_Produit unProduit : lesProduits) {
			if (unProduit.getNom() == nomProduit) {
				unProduit.ajouter(qteAchetee);
				return true;
			}
		}
		return false;
	}
		/*if(lesProduits.contains((nomProduit))) {
			I_Produit unProduit = lesProduits.get(lesProduits.indexOf(nomProduit));
			unProduit.ajouter(qteAchetee);
			return true;
		}
		return false;*/


	/**
	 * Méthode qui permet de vendre le stock d'un produit. Elle décremente le stock du produit passé en paramètre selon la quantité saisie en paramètre.
	 * @param nomProduit
	 * @param qteVendue
	 * @return bool
	 */
	@Override
	public boolean vendreStock(String nomProduit, int qteVendue) {
		for(I_Produit unProduit :lesProduits) {
			if (unProduit.getNom() == nomProduit) {
				if(unProduit.getQuantite() >= qteVendue && unProduit.getQuantite() >0) {
					unProduit.enlever(qteVendue);
					return true;
				}
			}	
		}
		return false;
	}

	/**
	 * Méthode qui retourne les produit sous forme de tableau de string
	 * @return Strings[noms]
	 */
	@Override
	public String[] getNomProduits() {
		String noms[] = new String[lesProduits.size()];
		int i=0;
		for(I_Produit unProduit :lesProduits){
			noms[i]=unProduit.getNom();
			i++;
		}
		return noms;
	}

	/**
	 * Retourne le Montant Total TTC de la liste des produits
	 * @return
	 */
	@Override
	public double getMontantTotalTTC() {
		double montant = 0;
		for(I_Produit unProduit :lesProduits){
			montant += unProduit.getPrixStockTTC();
		}
		return montant;
	}

	/**
	 * Retourne l'ensemble des infos des produits du catalogue.
	 * Retour sous la forme : Nom du produit - Prix HT - Prix TTC - Quantité en stock
	 * @return String
	 */
	@Override
	public String toString() {
		String listeProduit = new String();
		if (lesProduits.isEmpty()){
			listeProduit += "\n" + "Montant total TTC du stock : " + getMontantTotalTTC();
		}


		//return "la liste de produit contients : " + lesProduits;
		for(I_Produit unProduit :lesProduits){
			listeProduit += unProduit.getNom() + "- prix HT : " + unProduit;
		}
		//"Treets - prix HT : 10,00  - prix TTC : 12,00  - quantité en stock : 4" + "\n" +
		return listeProduit;
	}

	/**
	 * Vide le catalogue des produits
	 */
	@Override
	public void clear() {
		lesProduits.removeAll(lesProduits);
	}

}