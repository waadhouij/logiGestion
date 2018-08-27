package Serie53;

import MesInterfaces.InterfaceGestion;
import connexions.ConnexionFichiers;
import iPane.*;
import mesExceptions.AbandonException;

public class GestionTableDesClients implements InterfaceGestion<TableDesClientsS, ClientS<String>>{
	private	ES ES = new ES();
	private ConnexionFichiers<TableDesClientsS> fichCli;

	public GestionTableDesClients (String nomPhysique) {
		fichCli = new ConnexionFichiers<TableDesClientsS>(nomPhysique);
	}
	
	@Override
	public void menuGeneral(TableDesClientsS tabCli, Object... objects) throws AbandonException {
		int choix;
		do {
			choix = menuChoix();
			try {
				switch (choix) {
				case 1:
					creer(tabCli);
					break;
				case 2:
					modifier(tabCli); 
					break;
				case 3:
					afficher(tabCli);
					break;
				case 4:
					afficherTous(tabCli);
					break;
				case 5:
					sauvegarder(tabCli);
					break;
				case 0:
					break;
				}
			} catch (AbandonException abe) {
			}
		} while (choix != 0);
		sauvegarder(tabCli);
	}

	@Override
	public int menuChoix(Object... objects) throws AbandonException {
		String menu = "\n ***   GESTION des CLIENTS   ***\n\n" 
				+ " CRÉER un client ...................................... 1 \n"
				+ " MODIFIER un client ................................. 2 \n"
				+ " AFFICHER le client ................................... 3 \n"
				+ " AFFICHER tous les clients ....................... 4 \n"
				+ " SAUVEGARDER les clients ........................ 5 \n"
				+ " FIN .......................................................... 0 \n"
				+ " VOTRE CHOIX : \n";
return ES.saisie(menu, 0, 6);
	}

	@Override
	public void creer(TableDesClientsS tab, Object... objects) throws AbandonException {
		ClientS<String> client = saisie(tab, objects);
		if (client != null) tab.ajouter(client);
	}

	@Override
	public void supprimer(TableDesClientsS tab, Object... objects) throws AbandonException {
		// A garder pour respecter le contrat de l'interface
	}

	@Override
	public void afficher(TableDesClientsS tab) throws AbandonException {
		if (tab.taille() == 0)
			ES.affiche("*** AUCUN CLIENT ACTUELLEMENT ***");
		else {
			String code = ES.saisie("Quel client voulez-vous afficher ? \nNuméro de code client :" + tab.cle().toString()).toLowerCase();
			ClientS<String> cli = tab.retourner(code);
			if (cli != null) 
				ES.affiche(cli.toString());
			else 
				ES.affiche("\n Le client n'existe pas ! ");
		}
	}

	@Override
	public ClientS<String> saisie(TableDesClientsS tab, Object... objects) throws AbandonException {
		String nomPrenom = ES.saisie(" Saisissez le nom et prénom du client SVP : \n");
		String codeClient = createCodeClient(tab, nomPrenom);
		String localite = ES.saisie("Veuillez saisir la localité : ");
		return new ClientS<>(codeClient, nomPrenom, localite) ;
	}

	/**
	 * le code du client soit composé des 3 premiers caractères du nom-prénom suivi d’un numéro d’ordre (cas où il y a doublons). 
	 * @param tab
	 * @param nomPrenom
	 * @return le code client
	 */
	private String createCodeClient(TableDesClientsS tab, String nomPrenom) {
		String troisPremiers = "";
		for (int i = 0; i < nomPrenom.length() && i < 3 && nomPrenom.charAt(i) != ' '; i++) {
			troisPremiers += nomPrenom.charAt(i);
		}
		String codeClient = retournerPremierLibre(tab, troisPremiers);
		return codeClient;
	}

	private String retournerPremierLibre(TableDesClientsS client, String cle) {
		String code = cle;
		int compteur = 1;
		do {
			if(client.retourner(code) == null)return code;
			code  = cle + compteur;
			compteur++;
		}while (true);
	}

	@Override
	public void modifier(TableDesClientsS tab, Object... objects) throws AbandonException {
		ES.affiche(tab.toString());
		ClientS<String> a1 = saisieModification(tab); 
		if (a1 != null)
			tab.modifier(a1);
		else
			ES.affiche("\nCe client n'existe pas\n"); 
		
	}
	
	/**
	 * Le numéro de client ne change pas même si le nomPrenom change. 
	 * @param tabCli
	 * @return
	 * @throws AbandonException
	 */
	public ClientS<String> saisieModification(TableDesClientsS tabCli) throws AbandonException {
		String nCode = ES.saisie("\n***   MODIFIER UN CLIENT   ***\nSaisissez le code : \n" + tabCli.cle().toString() + "\n");
		ClientS<String>ancienCli = tabCli.retourner(nCode);
		if (ancienCli != null) {
			String nomPrenom = ES.saisie("Nom Prénom \n(laissez vide pour garder la valeur actuelle " + ancienCli.getNomPrenom()+ " ): ");
			String localite = ES.saisie("Localité \n(laissez vide pour garder la valeur actuelle" + ancienCli.getLocalite()+ " ): ");
			
			if(nomPrenom.isEmpty()) 
				nomPrenom = tabCli.retourner(nCode).getNomPrenom();
			if(localite.isEmpty())
				localite = tabCli.retourner(nCode).getLocalite();
			ClientS<String> nCli = new ClientS<String>(nCode, nomPrenom, localite);
			nCli.setCaREalise(ancienCli.getCaREalise());
			nCli.setNbCdes(ancienCli.getNbCdes());
			nCli.setDateCreation(ancienCli.getDateCreation());
			return nCli;
		}
		return null;
	}

	@Override
	public void afficherTous(TableDesClientsS tabCli) {
		if (tabCli.taille() == 0)
			ES.affiche("*** AUCUN CLIENT ACTUELLEMENT ***");
		else 
			ES.affiche(tabCli.toString() + "\n\n");
		
	}

	@Override
	public void sauvegarder(TableDesClientsS tabCli) {
		fichCli.ecrire(tabCli);
	}

	@Override
	public TableDesClientsS recuperer() {
		TableDesClientsS tabCli = fichCli.lire();
		if (tabCli == null)
			tabCli = new TableDesClientsS();
		return tabCli;
	}

}
