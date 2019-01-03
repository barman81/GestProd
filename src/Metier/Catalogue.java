package Metier;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



public class Catalogue implements I_Catalogue {
	private ArrayList<I_Produit> lesProduits = new ArrayList<I_Produit>();

	public Catalogue(){
	}

	@Override
	public boolean addProduit(I_Produit produit) {
		if(produit != null && produit.getPrixUnitaireHT() >0)
		{
			lesProduits.add(produit);
			return true;
		}
		return false;
	}

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

	@Override
	public boolean acheterStock(String nomProduit, int qteAchetee) {
		if(lesProduits.contains((nomProduit))) {
			I_Produit unProduit = lesProduits.get(lesProduits.indexOf(nomProduit));
			unProduit.ajouter(qteAchetee);
			return true;
		}
		return false;
	}

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

	@Override
	public double getMontantTotalTTC() {
		double montant = 0;
		for(I_Produit unProduit :lesProduits){
			montant += unProduit.getPrixStockTTC();

		}
		return montant;
	}

	@Override
	public String toString() {
		return "la liste de produit contients : " + lesProduits;
	}

	@Override
	public void clear() {
		lesProduits.removeAll(lesProduits);
	}

}