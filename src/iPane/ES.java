package iPane;

import javax.swing.*;

import MesInterfaces.InterfaceES;
import mesExceptions.AbandonException;
import mesExceptions.ErreurSaisie;

public class ES implements InterfaceES{

	public void affiche(String msg) {
		JTextArea text = new JTextArea();
		text.setText(msg);
		JOptionPane.showMessageDialog(null, text);
	}

	public int saisie(String msg, Integer... objects) throws AbandonException {
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

		JTextArea text = new JTextArea();
		text.setText(msg);
		String data = "";
		do {
			try {
				data = JOptionPane.showInputDialog(text);
				if (data == null)
					throw new AbandonException();
				int saisie = Integer.parseInt(data);
				if (saisie >= inf && saisie <= sup)
					return saisie;
				throw new ErreurSaisie();
			} catch (ErreurSaisie es) {
				affiche("Saisie hors intervalle\n");
			} catch (NumberFormatException nfe) {
				if (data.equals(""))
					affiche("Il faut saisir qlqch");
				else
					affiche("Saisie non numérique");
			} catch (AbandonException abe) {
				boolean abandon = saisieOuiNon("Voulez vous abandonner ? ");
				if (abandon)
					throw abe;
			}
		} while (true);
	}

	public boolean saisieOuiNon(String message) {
		return JOptionPane.showConfirmDialog(null, message) == 0;
	}

	public String saisie(String message) throws AbandonException {
		String data = "";
		do {
			try {
				data = JOptionPane.showInputDialog(message);
				if (data == null) // veux dire que j'a cliqué sur la croix pour fermer la fenêtre
					throw new AbandonException();
				return data;
			} catch (AbandonException abe) {
				boolean abandon = saisieOuiNon("Voulez vous abandonner ? ");
				if (abandon)
					throw abe;
			}
		} while (true);

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
		
		String data = "";
		do {
			try {
				data = JOptionPane.showInputDialog(mes);
				if (data == null)
					throw new AbandonException();
				float saisie = Float.parseFloat(data);
				if (saisie >= inf && saisie <= sup)
					return saisie;
				throw new ErreurSaisie();
			} catch (ErreurSaisie es) {
				affiche("Saisie hors intervalle\n");
			} catch (NumberFormatException nfe) {
				if (data.equals(""))
					affiche("Il faut saisir qlqch");
				else
					affiche("Saisie non numérique");
			} catch (AbandonException abe) {
				boolean abandon = saisieOuiNon("Voulez vous abandonner ? ");
				if (abandon)
					throw abe;
			}
		} while (true);
	}
	
	
/**
 * Utilisé dans GestionTableArticle pour saisieModification() afin de pouvoir garder en mémoire le prix Unitaire
 * @param msg
 * @param inf
 * @param obj
 * @return
 * @throws AbandonException
 */
	public float saisieModification(String msg, float inf, Object... obj) throws AbandonException {
		float sup = Float.MAX_VALUE;
		if (obj.length == 1)
			sup = (float) obj[0];

		String data = "";
		do {
			try {
				data = JOptionPane.showInputDialog(msg);
				if (data == null)
					throw new AbandonException();
				float saisie = Float.parseFloat(data);
				if (saisie >= inf && saisie <= sup)
					return saisie;
				throw new ErreurSaisie();
			} catch (ErreurSaisie es) {
				affiche("Saisie hors intervalle\n");
			} catch (NumberFormatException nfe) {
				if (data.isEmpty())
					return 0;
				else
					affiche("Saisie non numérique");
			} catch (AbandonException abe) {
				boolean abandon = saisieOuiNon("Voulez vous abandonner ? ");
				if (abandon)
					throw abe;
			}
		} while (true);
	}
	
/**
 * Utilisé dans GestionTableArticle pour saisieModification() afin de pouvoir garder en mémoire le prix Unitaire
 * @param msg
 * @param inf
 * @param obj
 * @return
 * @throws AbandonException
 */
	public int saisieModification(String msg, int inf, Object... obj) throws AbandonException {
		int sup = Integer.MAX_VALUE;
		int nbparam = obj.length;
		if (nbparam == 1)
			sup = (int) obj[0];

		String data = "";
		do {
			try {
				data = JOptionPane.showInputDialog(msg);
				if (data == null)
					throw new AbandonException();
				int saisie = Integer.parseInt(data);
				if (saisie >= inf && saisie <= sup)
					return saisie;
				throw new ErreurSaisie();
			} catch (ErreurSaisie es) {
				affiche("Saisie hors intervalle\n");
			} catch (NumberFormatException nfe) {
				if (data.isEmpty())
					return 0;
				else
					affiche("Saisie non numérique");
			} catch (AbandonException abe) {
				boolean abandon = saisieOuiNon("Voulez vous abandonner ? ");
				if (abandon)
					throw abe;
			}
		} while (true);
	}

}
