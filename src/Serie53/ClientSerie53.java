package Serie53;

import iPane.ES;
import mesExceptions.*;

public class ClientSerie53 {
	static ES ES = new ES();

	public static void main(String[] args) {
		String fichCde = "TableCommandes.data", fichArt = "TableDesArticles.data", fichFact = "TableDesFactures.data", fichCli = "TableDesClient.data";

		GestionTableDesCommandes53 gestionTabCde = new GestionTableDesCommandes53(fichCde);
		GestionTableDesArticles53 gestionTabArt = new GestionTableDesArticles53(fichArt);
		GestionTableDesFactures53 gestionTabFact = new GestionTableDesFactures53(fichFact);
		
		GestionTableDesClients gestionTabClient = new GestionTableDesClients(fichCli);
		TableDesClientsS tabCli = gestionTabClient.recuperer();
		TableDesArticles53 tabArt = gestionTabArt.recuperer();
		TableDesCommandes53 tabCde = gestionTabCde.recuperer();
		TableDesFactures53 tabFact = gestionTabFact.recuperer();
		tabArt.purge();
		
		int choix;
		// MENU GENERAL
		do {
			try {
				choix = menuChoix();
				switch (choix) {
				case 1:
					gestionTabArt.menuGeneral(tabArt, tabCde);
					break;
				case 2:
					gestionTabCde.menuGeneral(tabCde, tabArt, tabFact, tabCli);
					break;
				case 3:
					gestionTabFact.menuGeneral(tabFact, tabCde, tabArt);
					break;
				case 4:
					gestionTabClient.menuGeneral(tabCli);
					break;
				case 5: 
					ES.affiche(" SAUVEGARDE EN COURS DES FICHIERS :\n ___________________________________ \n\n "+ fichArt + "\n " + fichCde + "\n " + fichFact + "\n " + fichCli);
					gestionTabArt.sauvegarder(tabArt);
					gestionTabCde.sauvegarder(tabCde); 
					gestionTabFact.sauvegarder(tabFact);
					gestionTabClient.sauvegarder(tabCli);
					break;
				case 0:
					break;
				}
			} catch (AbandonException abe) {
				choix = 0;
			}
		} while (choix != 0);
		ES.affiche(" SAUVEGARDE EN COURS DES FICHIERS :\n ___________________________________ \n\n "+ fichArt + "\n " + fichCde + "\n " + fichFact + "\n " + fichCli);
		gestionTabArt.sauvegarder(tabArt);
		gestionTabCde.sauvegarder(tabCde); 
		gestionTabFact.sauvegarder(tabFact);
		gestionTabClient.sauvegarder(tabCli);
		ES.affiche("******                           ******\n\n  AU REVOIR ET A BIENTOT  \n\n******                           ******\n");
	
	
	}

	

	/**
	 * MENU GENERAL
	 * 
	 * @return
	 */
	public static int menuChoix() throws AbandonException {
		String menu = "  \n***   BIENVENUE A LA SUPERETTE   *** \n\n" 
						+ " GESTION DES ARTICLES ............... 1 \n"
						+ " GESTION DES COMMANDES ......... 2 \n" 
						+ " GESTION DES FACTURES .............. 3 \n"
						+ " GESTION DES CLIENTS ................. 4 \n" 
						+ " SAUVEGARDER ............................. 5 \n"
						+ " FIN .............................................. 0 \n"
						+ " VOTRE CHOIX : \n";
		return ES.saisie(menu, 0, 5);
	}
}