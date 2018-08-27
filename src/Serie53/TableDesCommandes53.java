package Serie53;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import MesInterfaces.InterfaceStructure;


public class TableDesCommandes53 implements InterfaceStructure<Commande53<String>, String>{
	
	private static final long serialVersionUID = 1L;
	private TreeMap<String, Commande53<String>> tabCde = new TreeMap<>();

	public TableDesCommandes53() {
	};

	public String toString() {
		String st = "";
		if (taille() == 0)
			st = "\n *** AUCUNE COMMANDE EN COURS ***\n";
		else {
			st = "\n *** LISTE DES COMMANDES PASSEES ***\n";
			for (Commande53<String> cde : tabCde.values()) {
				st += cde.toString() + "\n";
			}
		}
		return st;
	}
	
	public int taille () {
		return tabCde.size();
	}
	public void ajouter(Commande53<String> cde) {
		if (! tabCde.containsKey(cde.getNumcde()))
		tabCde.put(cde.getNumcde(), cde);
	}
	
	public Commande53<String> retourner(String code){
		if ( taille() == 0 ) return null;
		return tabCde.get(code);
	}
	
	/**
	 * renvoi toutes les clés de tabCde
	 * @return
	 */
	public Set<String> cle(){
		return tabCde.keySet();
	}

	public void supprimer(Integer code) {
		
	}
	
	public void supprimer(String code) {
		tabCde.remove(code);
	} 


	/**
	 * Supprime la lignes de l'article en paramètre dans toutes les commandes 
	 * @param code
	 */
	public void purge(int code) {
		Collection<Commande53<String>> cdes = tabCde.values();
		for (Iterator<Commande53<String>> i = cdes.iterator() ; i.hasNext();){
			Commande53<String> element = i.next();
			element.supprimer(code);
			if (element.taille() == 0) {
    				i.remove(); 
 			}
		}
	}
	
	/**
	 * Supprime toutes les commandes qui sont vides
	 * @param code
	 */
	public void purgeVide() {
		Collection<Commande53<String>> cdes = tabCde.values();
		for (Iterator<Commande53<String>> i = cdes.iterator() ; i.hasNext();){
			Commande53<String> element = i.next();
			if (element.taille() == 0) {
    				i.remove(); 
 			}
		}
	}
	
	

	/**
	 * retourne les numéros des commandes non facturées
	 * @return
	 */
	public Set<String> clesNonFacture() {
		Set<String> cles = new TreeSet<>();
		Collection<Commande53<String>> cdes = tabCde.values();
		for (Commande53<String> cde : cdes) {
			if (!cde.getEtatFacture())
				cles.add(cde.getNumCde());
		}
		return cles;
	}
}
