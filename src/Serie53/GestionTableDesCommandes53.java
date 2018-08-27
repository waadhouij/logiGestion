package Serie53;


import MesInterfaces.InterfaceGestion;
import Utils.DateUser;
import connexions.ConnexionFichiers;
import mesExceptions.AbandonException;

import iPane.ES;

public class GestionTableDesCommandes53 implements InterfaceGestion <TableDesCommandes53, Commande53<String> >{
	private ES ES = new ES();
	private ConnexionFichiers<TableDesCommandes53> fichCde; //nomLogique
	
	public GestionTableDesCommandes53(String nomPhysique) {
		fichCde = new ConnexionFichiers<TableDesCommandes53>(nomPhysique);
	}
	
	public void menuGeneral (TableDesCommandes53 tabCde, Object ...objects) throws AbandonException {
		TableDesArticles53 tabArt = (TableDesArticles53)objects[0];
		TableDesFactures53 tabFact = (TableDesFactures53)objects[1];
		TableDesClientsS tabCli = (TableDesClientsS)objects[2];
		tabArt.purge();
		int choix;
		tabCde.purgeVide();
		do {
			String num = premierNumero(tabCde, tabFact);
			choix = menuChoix(num);
			try {
				switch (choix) {
				case 1:
					creer(tabCde, tabArt, num, tabCli);
					break;
				case 2:
					modifier(tabCde, tabArt);
					break;
				case 3:
					supprimer(tabCde);
					break;
				case 4:
					afficher(tabCde);
					break;
				case 5:
					afficherTous(tabCde);
					break;
				case 6:
					sauvegarder(tabCde);
					break;
				case 0:
					break;
				}
			} catch (AbandonException abe) {
			}
		}while (choix != 0);
		sauvegarder(tabCde);
	}

	public int menuChoix(Object ...objects) throws AbandonException{
		String num = (String)objects[0];
		String menu = "\n\n ***   GESTION DES COMMANDES   ***\n\n"
				+" CRÉATION d'une commande N° " + num + " ........... 1 \n"
				+" MODIFIER une commande ..................................... 2 \n"
				+" SUPPRESSION d'une commande ............................. 3 \n"
				+" AFFICHAGE d'une commande ................................ 4 \n"
				+" AFFICHAGE de toutes les commandes ................... 5 \n"
				+" SAUVEGARDER les commandes .............................. 6 \n"
				+" FIN ........................................................................ 0 \n"
				+" VOTRE CHOIX  :\n";
		return ES.saisie(menu, 0, 6);
				
	}
	public  void creer(TableDesCommandes53 tabCde,  Object ...objects) throws AbandonException {
		TableDesArticles53 tabArt = (TableDesArticles53) objects[0];
		String num = (String) objects[1];
		TableDesClientsS tabCli = (TableDesClientsS)objects[2];
		Commande53<String> cde = new Commande53<String>();
		String codeClient = ES.saisie("Entre le code client : " + tabCli.cle().toString());
		ClientS<String> client = tabCli.retourner(codeClient);
		if ( client != null) {
			cde.setNumCde(num);
			cde.setNumClient(codeClient);
			Gestion1Commande53 g1Cde = new Gestion1Commande53();
			g1Cde.menuGeneral(cde, tabArt, num, tabCde);
			//vérifie que la commande est remplie de lignes de commande. car cde n'est jamais vide puisque il y a la date et le numéro…
			if (cde.taille() != 0) {
				cde.setValeurTotal(cde.prixTotalHT(tabArt));
				tabCde.ajouter(cde);
				client.setCaREalise(client.getCaREalise()+cde.prixTotalHT(tabArt));
				client.setNbCdes(client.getNbCdes()+1);
				
			}
		}
	}
	
	public void supprimer(TableDesCommandes53 tabCde, Object... objects) throws AbandonException {
		if (tabCde.taille() == 0)
			ES.affiche("*** AUCUNE COMMANDE ACTUELLEMENT ***");
		else {
			String code = ES.saisie(
					" \nQuelle commande voulez vous supprimer ? \nNuméro de Commande :" + tabCde.cle().toString());
			if (tabCde.retourner(code) != null && tabCde.retourner(code).getEtatFacture()) {
				Boolean sur = ES.saisieOuiNon("Êtes-vous certain de vouloir supprimer? \nCette action est irréversible (O/N): ");
				if (!sur) throw new AbandonException();
				tabCde.supprimer(code);
			}else if (tabCde.retourner(code).taille() == 0)
				tabCde.supprimer(code);
			else if (!tabCde.retourner(code).getEtatFacture())
				ES.affiche("La facture n'a pas encore été édité, vous ne pouvez pas supprimer la commande");
			else
				ES.affiche("\nLa commande n'existe pas ! ");
		}
	}
	
	/**
	 * Affiche une commande
	 */
	public void afficher(TableDesCommandes53 tabCde) throws AbandonException {
		if (tabCde.taille() == 0)
			ES.affiche("*** AUCUNE COMMANDE ACTUELLEMENT ***");
		else {
			String code = ES.saisie("Quelle commande voulez-vous afficher ? \nNuméro de Commande :" + tabCde.cle().toString());
			Commande53<String> cde = tabCde.retourner(code);
			if (cde != null) 
				ES.affiche(cde.toString());
			else 
				ES.affiche("\n La commande n'existe pas ! ");
		}
	}
	
	public  void afficherTous(TableDesCommandes53 tabCde) {
		if (tabCde.taille() == 0)
			ES.affiche("*** AUCUNE COMMANDE ACTUELLEMENT ***");
		else 
			ES.affiche(tabCde.toString());
	}
	

	/**
	 * retourne le premier numéro disponible d'une commande
	 * 
	 * @param tabCde
	 */
	private  String premierNumero(TableDesCommandes53 tabCde, TableDesFactures53 tabFact) {
		int num = 1;
		DateUser dat = new DateUser();
		String fixe = "" + dat.getAnnee() + dat.getMois() + dat.getJour();
		String cle;
		do {
			cle = fixe + num;
			if (tabCde.retourner(cle) == null && (tabFact.retourner(cle) == null))
				return cle;
			num++;
		} while (true);
	}

	/**
	 * modifie une commande
	 * @param tabCde
	 * @param tabArt
	 * @throws AbandonException 
	 */
	public void modifier(TableDesCommandes53 tabCde, Object ...objects) throws AbandonException {
		TableDesArticles53 tabArt = (TableDesArticles53) objects[0];
		if (tabCde.taille() == 0)
			ES.affiche("*** AUCUNE COMMANDE ACTUELLEMENT ***");
		else {
			String num = ES.saisie("\n Choisissez le numéro de la commande à modifier : " + tabCde.cle());
			Commande53<String> cde = tabCde.retourner(num) ;
			if (cde != null) {
				if (!cde.getEtatFacture()) {
					Gestion1Commande53 gestion1Cde =  new Gestion1Commande53();
					gestion1Cde.menuGeneral(cde, tabArt, num, tabCde);
				}else {
					ES.affiche("la commande ne peut être modifiée car la facture a été éditée");
				}
			}else
				ES.affiche("\nCe numéro de commande n'existe pas.");
		}
	}
	
	public Commande53<String> saisie(TableDesCommandes53 tab, Object ...objects) throws Exception{
		// Laisser vide pour remplir contrat interface
		return null;
	}

	/**
	 * lis le fichier et retourne la table
	 * @return
	 */
	public TableDesCommandes53 recuperer(){
		TableDesCommandes53 tabCde = fichCde.lire();
		if(tabCde == null) {
			tabCde = new TableDesCommandes53();
		}
		return tabCde;	
	}
	
	/**
	 * sauvegarde dans un fichier
	 * @param tabArt
	 */
	public void sauvegarder(TableDesCommandes53 tabCde) {
		fichCde.ecrire(tabCde);
	}

}
