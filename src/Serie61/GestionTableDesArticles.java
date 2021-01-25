package Serie61;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import Serie53.Article53;
import Serie53.ArticleAbstrait53;
import Serie53.ArticleLot53;
import Serie53.ArticlePromo53;
import Serie53.TableDesArticles53;
import Serie53.TableDesCommandes53;
import connexions.ConnexionFichiers;
import iPane.ES;
import mesExceptions.AbandonException;
import java.awt.Color;
import java.awt.Font;

public class GestionTableDesArticles extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	TableDesArticles53 tableArt;
	TableDesCommandes53 tabCde;
	private JButton creer, modifier, supprimer, afficher, fin;
	private ES ES = new ES();
	ConnexionFichiers<TableDesArticles53> fichArt; //nomLogique
	
	public GestionTableDesArticles(String nomPhysique, TableDesArticles53 tabArt, TableDesCommandes53 tabCde) {
		fichArt = new ConnexionFichiers<TableDesArticles53>(nomPhysique);
		this.tableArt = tabArt;
		this.tabCde = tabCde;
		this.setSize(400, 300);
		this.setTitle("GESTION DES ARTICLES");
		creer= new JButton("CREER UN PRODUIT");
		creer.setBounds(56, 5, 271, 43);
		creer.setFont(new Font("Tahoma", Font.PLAIN, 18));
		creer.setBackground(Color.RED);
		modifier= new JButton("MODIFIER UN PRODUIT");
		supprimer = new JButton("SUPPRIMER UN PRODUIT");
		supprimer.setBounds(56, 59, 271, 43);
		supprimer.setBackground(Color.RED);
		supprimer.setFont(new Font("Tahoma", Font.PLAIN, 18));
		afficher= new JButton("AFFICHER");
		afficher.setBounds(50, 113, 277, 43);
		afficher.setFont(new Font("Tahoma", Font.PLAIN, 18));
		afficher.setBackground(Color.RED);
		fin= new JButton("FIN");
		fin.setBounds(50, 167, 277, 43);
		fin.setBackground(Color.RED);
		fin.setFont(new Font("Tahoma", Font.PLAIN, 18));
		getContentPane().setLayout(null);
		
		getContentPane().add(creer); 
		creer.addActionListener(this);
		getContentPane().add(supprimer); 
		supprimer.addActionListener(this);
		getContentPane().add(afficher); 
		afficher.addActionListener(this);
		getContentPane().add(fin); 
		fin.addActionListener(this);
		
		this.setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent event) {
		try {
			int num = premierNumero(tableArt);
			if (event.getSource()==creer) creer(tableArt, num, tabCde);
			if (event.getSource()==modifier) modifier(tableArt, tabCde);
			if (event.getSource()==supprimer) supprimer(tableArt, tabCde);
			if (event.getSource()==afficher) afficherTous(tableArt);
			if (event.getSource()==fin) { 
				setVisible(false); 
			}			
		} catch (Exception e) {
		}
	}
	
	/**
	 * MENU GESTION DES ARTICLES
	 * 
	 * @param tabArt
	 */
	public void menuGeneral(TableDesArticles53 tabArt, Object ...objects) throws AbandonException {
		TableDesCommandes53 tabCde = (TableDesCommandes53) objects[0];
		tabArt.purge();
		int choix;
		do {
			int num = premierNumero(tabArt);
			choix = menuChoix();
			try {
				switch (choix) {
				case 1:
					creer(tabArt, num, tabCde);
					break;
				case 2:
					modifier(tabArt, tabCde);
					break;
				case 3:
					supprimer(tabArt, tabCde);
					break;
				case 4:
					afficherTous(tabArt);
					break;
				case 5:
					affichePromo(tabArt); 
					break;
				case 6:
					afficheLot(tabArt);
					break;
				case 7:
					sauvegarder(tabArt);
					break;
				case 0:
					break;
				}
			} catch (AbandonException abe) {
			}
		} while (choix != 0);
		sauvegarder(tabArt);
	}


	public int menuChoix(Object ...objects) throws AbandonException {
		String menu = "\n ***   GESTION des ARTICLES   ***\n\n" 
						+ " CRÃ‰ER un article ................................ 1 \n"
						+ " MODIFIER un article ........................... 2 \n"
						+ " SUPPRIMER un article ......................... 3 \n"
						+ " AFFICHER le stock ............................. 4 \n"
						+ " AFFICHER les articles en Promotion ... 5 \n"
						+ " AFFICHER les articles bradÃ©s par lot ... 6 \n"
						+ " SAUVEGARDER les articles ................. 7 \n"
						+ " FIN ..................................................... 0 \n"
						+ " VOTRE CHOIX : \n";
		return ES.saisie(menu, 0, 7);
	}

	public void creer(TableDesArticles53 tabArt, Object ...objects) throws AbandonException {
	//	int num = (int)objects[0];
		TableDesCommandes53 tabCde = (TableDesCommandes53)objects[1];
		try {
			ArticleAbstrait53 art = saisie(tabArt, premierNumero(tabArt));
			tabArt.ajouter(art);
		}catch (AbandonException e) {
			menuGeneral(tabArt, tabCde);
		}
	}

	/**
	 * Saisie pour la crÃ©ation
	 * @param tabArt
	 * @param code
	 * @return
	 * @throws AbandonException
	 */
	public ArticleAbstrait53 saisie(TableDesArticles53 tabArt, Object ...objects) throws AbandonException {
		int code = (int)objects[0];
		String designation;
		do {
			designation = ES.saisie("\n***   SAISIE D'UN ARTICLE   ***\nDÃ©signation : ");
		}while(designation.isEmpty());
		float pu = ES.saisie("Prix Unitaire : ", 0.1f);
		
		int choix;
		do {
			choix = menuTypeProduit();
			switch (choix) {
			case 1:
				return new Article53(code, designation, pu);
			case 2:
				float reduction = ES.saisie(" RÃ©duction Ã  appliquer :",	1, Integer.MAX_VALUE);
				int quantiteMini = ES.saisie(" Pour quelle quantite mininum ?", 1, Integer.MAX_VALUE);
				return new ArticlePromo53(code, designation, pu, quantiteMini, reduction);
			case 3 : 
				float reduct = ES.saisie(" RÃ©duction Ã  appliquer :",	1, Integer.MAX_VALUE);
				int quantiteMin = ES.saisie(" Quelle est la quantitÃ© d'un lot ?", 1, Integer.MAX_VALUE);
				boolean periodeOui = ES.saisieOuiNon(" Votre article sera bradÃ© pendant 7 jours. \n Cela vous convient-il ?");
				if (periodeOui)
					return new ArticleLot53(code, designation, pu, quantiteMin, reduct);
				int periode = ES.saisie(" Pour combien de jours voulez-vous brader cet article par lot ? \n", 1, 365);
				ArticleLot53 art = new ArticleLot53(code, designation, pu, quantiteMin, reduct);
				art.setPeriode(periode);
				return art;
			case 0:
				throw new AbandonException();
			}
		} while (choix != 0);
		return null;
	}
	

	private int menuTypeProduit(Object... obj) throws AbandonException {
		String menuProduit = "\n ***   CHOIX DU TYPE D'ARTICLE   ***";
		if (obj.length == 1) {
			String msgSup = (String) obj[0];
			menuProduit += msgSup;
		}
		menuProduit += "\n Choisissez le type d'article que vous voulez : \n\n" +
						" ARTICLE sans rÃ©duction ............................................... 1 \n" +
						" ARTICLE en promotion \n (toute l'annÃ©e et selon une quantitÃ© donnÃ©e) ............... 2 \n" +
						" ARTICLE Ã  rÃ©duction par lot \n (sur une pÃ©riode donnÃ©e) ............................................. 3 \n" +
						" FIN .............................................................................. 0 \n" +
						" VOTRE CHOIX : \n";
		return  ES.saisie(menuProduit, 0, 3);
	
	}
	

	public ArticleAbstrait53 saisieModification(TableDesArticles53 tabArt) throws AbandonException {
		int code = ES.saisie("\n***   MODIFIER UN ARTICLE   ***\nSaisissez le code : ", 1);
		ArticleAbstrait53 ancienArt = tabArt.retourner(code);
		if ( ancienArt != null) {
			String designation = ES.saisie("Designation \n(laissez vide pour garder la valeur actuelle : " +  ancienArt.getDesignation() + " ): \n");
			float pu = ES.saisieModification("Prix Unitaire \n(laissez vide pour garder la valeur actuelle : " +  ancienArt.getPu() + " ): \n", 0.1f);
			if(designation.isEmpty())
				designation = ancienArt.getDesignation();
			if(pu == 0)
				pu = ancienArt.getPu();
			
			String type = "";
			if (ancienArt instanceof ArticlePromo53)
					type = "en promotion";
			else if(ancienArt instanceof ArticleLot53)
					type = "Ã  rÃ©duction par lot";
			else if(ancienArt instanceof Article53)
					type = "sans rÃ©duction";
			String msg = "\n Cet article est actuellement un article  *** " + type + " ***\n";
			int choix = menuTypeProduit(msg);
			switch (choix) {
				case 1:
					return new Article53(code, designation, pu);
				case 2:
					int quantiteMini;
					float reduction;
					if (ancienArt instanceof ArticlePromo53) {
						reduction = ES.saisieModification("RÃ©duction \n(laissez vide pour garder la valeur actuelle: " 
									+ ((ArticlePromo53)ancienArt).getReduction() + " ) :\n", 0.1f);
						quantiteMini = ES.saisieModification("QuantitÃ© Minimum \n(laissez vide pour garder la valeur actuelle: " 
								+ ((ArticlePromo53)ancienArt).getQuantiteMini() + " ) :\n", 1);						if(quantiteMini == 0)
							quantiteMini = ((ArticlePromo53) ancienArt).getQuantiteMini();
						if(reduction == 0)
							reduction = ((ArticlePromo53) ancienArt).getReduction();
					}else {
						reduction = ES.saisie("RÃ©duction :\n", 0.1f);
						quantiteMini = ES.saisie("QuantitÃ© Minimum :\n", 1);
					}
					return new ArticlePromo53(code, designation, pu, quantiteMini, reduction);
				case 3:
					float reduc;
					int quantiteMin;
					int periode ;
					if (ancienArt instanceof ArticleLot53) {
						reduc = ES.saisieModification("RÃ©duction \n(laissez vide pour garder la valeur actuelle: " 
								+ ((ArticleLot53)ancienArt).getReduction() + " ) :\n", 0.1f);
						quantiteMin = ES.saisieModification(" QuantitÃ© d'un lot \n(laissez vide pour garder la valeur actuelle: " 
								+ ((ArticleLot53)ancienArt).getQtiteLot() + " ) :\n", 1);
						periode = ES.saisieModification(" DurÃ©e en jour de la vente en lot \n(laissez vide pour garder la valeur actuelle: " + ((ArticleLot53)ancienArt).getPeriode() + " ) :\n", 1, 365);
						if(quantiteMin == 0)
							quantiteMin = ((ArticleLot53) ancienArt).getQtiteLot();
						if(reduc == 0)
							reduc = ((ArticleLot53) ancienArt).getReduction();
						if(periode == 0)
							periode = ((ArticleLot53) ancienArt).getPeriode();
					}else {
						reduc = ES.saisie("RÃ©duction :\n", 0.1f);
						quantiteMin = ES.saisie(" QuantitÃ© d'un lot :\n", 1);
						periode = ES.saisie(" DurÃ©e en jour de la vente en lot :\n", 1, 365);
						}
					ArticleLot53 art = new ArticleLot53(code, designation, pu, quantiteMin, reduc);
					art.setPeriode(periode);
					return art;
				case 0:
					throw new AbandonException();
			}
		}
		return ancienArt;
	}


	/**
	 * retourne le premier numÃ©ro disponible d'un Article
	 * 
	 * @param tabCde
	 */
	private int premierNumero(TableDesArticles53 tabArt) {
		int num = 1;
		do {
			if (tabArt.retourner(num) == null)
				return num;
			num++;
		} while (true);
	}
	

	/**
	 * Supprime un article et purge les commandes de cet article. Si la commande
	 * devient vide, suprression de la commande.
	 * 
	 * @param tabArt
	 * @param tabCde
	 */
	public void supprimer(TableDesArticles53 tabArt, Object... objects) throws AbandonException {

		TableDesCommandes53 tabCde = (TableDesCommandes53) objects[0];
		ES.affiche(tabArt.toString());
		int code = ES.saisie("\nQuel code d'article voulez-vous supprimer ? ", 1);
		ArticleAbstrait53 art = tabArt.retourner(code);
		if (art != null) {
			Boolean sur = ES.saisieOuiNon("ÃŠtes-vous certain de vouloir supprimer? \nCette action est irreÌ�versible (O/N): ");
			if (!sur) throw new AbandonException();
			tabArt.supprimer(code);
			tabCde.purge(code);
		} else
			ES.affiche("\nCet article n'existe pas\n");
	
	}
	

	/**
	 * Modifie (Ã©crase en l'occurence) un article s'il existe
	 * Un article peut devenir un article normal, en promotion ou brader en lot
	 * 
	 * @param tabArt
	 */
	public void modifier(TableDesArticles53 tabArt, Object ...objects) throws AbandonException {
		TableDesCommandes53 tabCde = (TableDesCommandes53)objects[0];
		ES.affiche(tabArt.toString());
		try {
		ArticleAbstrait53 a1 = saisieModification(tabArt); 
		if (a1 != null)
			tabArt.modifier(a1);
		else
			ES.affiche("\nCet article n'existe pas\n");
		}catch (AbandonException e) {
			menuGeneral(tabArt, tabCde);
		}
		
	} 

	public void afficher(TableDesArticles53 tabArt) throws AbandonException {
		// Laisser vide pour remplir contrat interface
	}
	public void afficherTous(TableDesArticles53 tabArt) {
		ES.affiche(tabArt.toString() + "\n\n");
	}
	
	public void affichePromo(TableDesArticles53 tabArt) {
		ES.affiche(tabArt.toStringPromo() + "\n\n");
		
	}
	
	public void afficheLot(TableDesArticles53 tabArt) {
		ES.affiche(tabArt.toStringLot() + "\n\n");
	}

	/**
	 * sauvegarde dans un fichier
	 * @param tabArt
	 */
	public void sauvegarder(TableDesArticles53 tabArt) {
		fichArt.ecrire(tabArt);
	}
	
	
	/**
	 * lis le fichier et retourne la table
	 * @return
	 */
	public TableDesArticles53 recuperer() {
		TableDesArticles53 tabArt = fichArt.lire();
		if(tabArt == null) 
			tabArt = new TableDesArticles53();
		return tabArt;	
	}


	
}