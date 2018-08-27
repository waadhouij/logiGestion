package Serie53;

import java.util.Set;
import java.util.TreeMap;

import MesInterfaces.InterfaceStructure;

public class TableDesClientsS implements InterfaceStructure<ClientS<String>, String>{
	private static final long serialVersionUID = -6497333798645717698L;
	private TreeMap <String, ClientS<String>> tabClient = new TreeMap <String, ClientS<String>>();
	
	public TableDesClientsS() {
		ClientS<String> cli1 = new ClientS<String>("ka-1", "Kala-Jean", "Paris");
		ClientS<String> cli2= new ClientS<String>("oua1","OUABASA-JEAN-CHRISTOPHE","TOULOUSE"); 
		ClientS<String> cli3= new ClientS<String>("oua2","OUABASA-JEAN-CHARLES","MONTPELLIER");
		
		tabClient.put((String)cli1.getCodeClient(), cli1);
		tabClient.put((String)cli2.getCodeClient(), cli2);
		tabClient.put((String)cli3.getCodeClient(), cli3);
	}


	@Override
	public ClientS<String> retourner(String cle) {
		return tabClient.get(cle);
	}
	
	
	public void ajouter(ClientS<String> client) {
		if (! tabClient.containsKey(client.getCodeClient()))
			tabClient.put((String)client.getCodeClient(), client);
	}
	
	public void modifier(ClientS<String> client) {
		tabClient.put(client.getCodeClient(), client);
	}

	@Override
	public void supprimer(String cle) {
		tabClient.remove(cle);
	}

	@Override
	public int taille() {
		return tabClient.size();
	}

	@Override
	public Set<String> cle() {
		return tabClient.keySet();
	}
public String toString() {
	if (tabClient.isEmpty()) {
		return "***    Il n'y a aucun client actuellement    ****";
	}
	String mes = "\n*** LISTE DES CLIENTS ****\n\n";
	for (ClientS<String> client : tabClient.values()) {
		mes += client.toString() ;
	}
	return mes;
}


}
