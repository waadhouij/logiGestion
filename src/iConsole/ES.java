package iConsole;

import java.util.InputMismatchException;
import java.util.Scanner;

import MesInterfaces.InterfaceES;
import mesExceptions.*;

public class ES implements InterfaceES {
	private Scanner sc = new Scanner(System.in);

	public void affiche(String mes) {
		System.out.println(mes);
	}

	public int saisie(String mes, Integer ...objects) throws AbandonException {
		int inf = Integer.MIN_VALUE;
		int sup = Integer.MAX_VALUE;
		int nbParam = objects.length;
		if (nbParam == 1) {
			inf = (int) objects[0];
		}
		if (nbParam == 2) {
			inf = (int) objects[0];
			sup = (int) objects[1];
		}
		
		do {
			affiche(mes);
			try {
				int data = sc.nextInt();
				sc.nextLine(); 
				// on duplique la ligne à cause du buffer sinon on aurait une ligne vide qui
				// correspond au retour chariot du user qui fait entré pour valider sa saisie.
				if (data >= inf && data <= sup)
					return data;
				throw new AbandonException();
			} catch (InputMismatchException e) {
				affiche(" \nSaisie non numérique,\n" +"Ressaisissez un nombre dans l'intervalle suivant : " + "[" + inf + ":" + sup + "] \n");
				sc.nextLine(); 
			} catch (AbandonException abe) {
				boolean reponse = saisieOuiNon("\n Saisie hors intervalle. \n Voulez vous abandonner ? (Oui/ Non) :\n");
				if (reponse) {
					throw abe; 
					// ça va propager l'exception et donc si elle est pas chopée va arrêter le programme via le catch dans le main
				}else {
					affiche("Ressaisissez un nombre dans l'intervalle suivant : " + "[" + inf + ":" + sup + "] \n");
				}
			}
		} while (true);
	}

	public String saisie(String mes) {
		affiche(mes);
		return sc.nextLine();
	}

	public boolean saisieOuiNon(String mes) {
		affiche(mes);
		return (sc.next().toLowerCase().charAt(0) == 'o');
	}

	public float saisie(String mes, Float ...objects) throws AbandonException {
		float inf = Float.MIN_VALUE;
		float sup = Float.MAX_VALUE;
		int nbParam = objects.length;
		if (nbParam == 1) {
			inf = (float) objects[0];
		}
		if (nbParam == 2) {
			inf = (float) objects[0];
			sup = (float) objects[1];
		}
		
		affiche(mes);
			do {
				try {
					float data = sc.nextFloat();
					sc.nextLine(); 
					// on duplique la ligne à cause du buffer sinon on aurait une ligne vide qui
					// correspond au retour chariot du user qui fait entré pour valider sa saisie.
					if (data >= inf && data <= sup)
						return data;
					throw new AbandonException();
				} catch (InputMismatchException e) {
					affiche(" \n saisie non numérique,\n" +"Ressaisissez un nombre dans l'intervalle suivant : " + "[" + inf + ":" + sup + "] \n");
					sc.nextLine(); // permet de vider le buffer et donc de ne pas avoir une boucle infinie.
				} catch (AbandonException abe) {
					boolean reponse = saisieOuiNon("\n Saisie hors intervalle. \n Voulez vous abandonner ? (Oui/ Non) :\n");
					if (reponse) {
						throw abe; 
						// ça va propager l'exception et donc si elle est pas chopée va arrêter le programme via le catch dans le main
					}else {
						affiche("Ressaisissez un nombre : ");
					}
				}
			} while (true);
		}
}

