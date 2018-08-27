package MesInterfaces;

import mesExceptions.AbandonException;

public interface InterfaceES {
	public void affiche(String mes);
	public int saisie(String mes, Integer ...objects) throws Exception;
	public float saisie(String mes, Float ...objects) throws Exception;
	// on met pas ça car le retour est pas pris dans la signature donc il faut distinguer les paramètres:
	//	public float saisie(String mes, Object ...objects) throws Exception;
	public String saisie(String mes) throws AbandonException;
	public boolean saisieOuiNon(String mes);
}
