package Utils;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateUser implements Serializable{
	private static final long serialVersionUID = 1L;
	private int jour;
	private int mois;
	private int annee;

	public DateUser(int jour, int mois, int annee) {
		this.jour = jour;
		this.mois = mois;
		this.annee = annee;
	}

	/**
	 * Initialise la Date à la date du jour de votre système. Le mois commence à 0
	 * et fini à 13 d'où la correction avec le if else
	 */
	public DateUser() {
		Calendar d = Calendar.getInstance();
		jour = d.get(Calendar.DAY_OF_MONTH);
		if (d.get(Calendar.MONTH) == 12)
			mois = d.get(Calendar.MONTH) + 2;
		else
			mois = d.get(Calendar.MONTH) + 1;
		annee = d.get(Calendar.YEAR);
	}
	public DateUser(DateUser user) {
		this.jour = user.getJour();
		this.mois = user.getMois();
		this.annee = user.annee;
	}
	
	/**
	 * si this est avant dateComparee résultat = -1
	 * si this est après dateComparee résultat = 1
	 * si this est egal à dateComparee résultat = 0
	 * @param dat
	 * @return O pour egal, 1 pour après, -1 pour avant
	 */
	public int compareTo(DateUser dateComparee) {
		Calendar thisDate = new GregorianCalendar(this.annee,this.mois,this.jour);
		Calendar lautre = new GregorianCalendar(dateComparee.annee, dateComparee.mois, dateComparee.jour);
		return thisDate.compareTo(lautre);
	}
	
	public DateUser ajouter(int nbjours) {
		if (nbjours > 0) {
			jour += nbjours;
			while (jour > nbMaxJours(mois, annee)) {
				jour -= nbMaxJours(mois, annee);
				mois += 1;
				if (mois > 12) {
					mois = 1;
					annee -= 1;
				}
			}
		}
		return this;
	}
	

	/**
	 * 
	 * @param d
	 * @return true Si this est antérieur à la date en paramètre 
	 */
	public boolean avant(DateUser d) {
		return (annee <= d.getAnnee() && mois <= d.getMois() && jour <= d.getJour());
	}
	/**
	 * Donne l'âge, à ce jour, en fonction de l’année d’une date.
	 * @return l'age
	 */
	public int age() {
		DateUser today = new DateUser();
		return today.getAnnee() - annee;
	}
	
	/**
	 * donne le jour de la semaine en fonction d'une date.
	 * C'est une application de la formule de Zeller
	 * @return
	 */
	public String jourDeSemaine() {
		int ent = annee;
		int mZ = mois -2; // car l'année commence en mars avec la formule de Zeller
		int jj = jour ;
		int aZ = ent % 100;
		int sZ = ent / 100 ;
		int Z = (int) ((2.6 * mZ - 0.2) + jj + aZ + (aZ/4) + (sZ/4) - 2*sZ) %7;
		return numeroToJour(Z);
	}
	
	/**
	 * 
	 * @return le jour correspondant à son numéro
	 */
	private String numeroToJour(int numJ) {
		switch (numJ) {
		case 0 : return "Dimanche";
		case 1 : return "Lundi";
		case 2 : return "Mardi";
		case 3 : return "Mercredi";
		case 4 : return "Jeudi";
		case 5 : return "Vendredi";
		case 6 : return "Samedi";
		default:
			return "jour inconnu";
		}
	}
	
	
	/**
	 * on change la date pointée par this à celle d'hier
	 */
	public void setToHier() {
		if (jour - 1 == 0) {
			jour = 31;
			mois--;
			if (mois == 0) {
				annee--;
				mois = 12;
			}
			while (!validDate(jour, mois, annee)) {
				jour--;
			}
		} else {
			jour--;
		}
	}

	/**
	 * On renvoi une date qui est celle du jour avant this.date
	 * 
	 * @return DateUser
	 */
	public DateUser getHier() {
		DateUser hier = new DateUser(jour, mois, annee);
		hier.setToHier();
		return hier;
	}

	/**
	 * On renvoi une date qui est celle du jour d'après this.date
	 * 
	 * @return DateUser
	 */
	public DateUser getLendemain() {
		DateUser dat2 = new DateUser(jour, mois, annee);
		dat2.lendemain();
		return dat2;
	}

	/**
	 * C'est la même méthode que lendemain() mais ma version
	 * on change la date pointée par this à celle du lendemain
	 */
	public void setToLendemain() {
		if (!validDate(jour + 1, mois, annee)) {
			jour = 1;
			if (mois == 12) {
				annee++;
				mois = 1;
			} else {
				mois++;
			}
		} else {
			jour++;
		}
	}

	/**
	 * on change la date pointée par this à celle du lendemain méthode du prof
	 */
	public void lendemain() {
		jour++;
		if (jour > nbMaxJours(mois, annee)) {
			jour = 1;
			mois++;
			if (mois > 12) {
				mois = 1;
				annee++;
			}
		}
	}

	/**
	 * Renvoi le nombre de jour selon l'année et le mois
	 * 
	 * @param mois
	 * @param annee
	 * @return
	 */
	public static int nbMaxJours(int mois, int annee) {
		switch (mois) {
		case 4:
		case 6:
		case 9:
		case 11:
			return 30;
		case 2:
			if (bissextile(annee))
				return 29;
			else
				return 28;
		default:
			return 31;
		}
	}

	/**
	 * Vérifie que la date passée en paramète est valide
	 * 
	 * @param jour
	 * @param mois
	 * @param annee
	 * @return true si la date est valide
	 */
	public static boolean validDate(int jour, int mois, int annee) {
		return jour <= nbMaxJours(mois, annee);
	}

	/**
	 * Vérifie que l'année soit bissextile
	 * 
	 * @param annee
	 * @return true si l'année est bissextile
	 */
	public static boolean bissextile(int annee) {
		return (annee % 400 == 0) || (annee % 100 != 0) && (annee % 4 == 0);
	}

	public int getJour() {
		return jour;
	}

	public int getMois() {
		return mois;
	}

	public int getAnnee() {
		return annee;
	}

	public void setJour(int jour) {
		this.jour = jour;
	}

	public void setMois(int mois) {
		this.mois = mois;
	}

	public void setAnnee(int annee) {
		this.annee = annee;
	}

	public String toString() {
		return (jour + "/" + mois + "/" + annee);
	}
}
