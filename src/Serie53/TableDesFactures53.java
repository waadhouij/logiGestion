package Serie53;

import java.util.Set;
import java.util.TreeMap;

import MesInterfaces.InterfaceStructure;

public class TableDesFactures53 implements InterfaceStructure<Facture53<String>, String>{
	private static final long serialVersionUID = 1L;
	private TreeMap<String, Facture53<String>> tabFactures = new TreeMap<String, Facture53<String>>();
	
	public TableDesFactures53() {
	}
	
	public String toString() {
		String st = "";
		for (Facture53<String> fact : tabFactures.values()) {
			st = st + fact.toString();
		}
		return st;
	}
	
	public void ajouter(Facture53<String> fact) {
		tabFactures.put(fact.getNumFacture(), fact);
	}
	
	public Facture53<String> retourner (String numfact){
		return tabFactures.get(numfact);
	}
	
	public int taille() {
		return tabFactures.size();
	}
	
	public void supprimer(String numFact) {
		tabFactures.remove(numFact);
	}
	
	public Set<String> cle(){
		return tabFactures.keySet();
	}
	
}
