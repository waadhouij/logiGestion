package Serie53;

import java.io.Serializable;

import Utils.DateUser;

public class ClientS <TypeDeNumero> implements Serializable{
	private static final long serialVersionUID = 1L;
	private TypeDeNumero codeClient;
	private String nomPrenom;
	private String localite;
	private float caREalise = 0f;
	private int nbCdes = 0;
	private DateUser dateCreation = new DateUser();
	
	public ClientS(TypeDeNumero codeClient, String nomPrenom, String localite) {
		this.codeClient = codeClient;
		this.nomPrenom = nomPrenom.toUpperCase();
		this.localite = localite.toUpperCase();
	}
	
	public String toString() {
		return "Client: " + nomPrenom + "\nCode : " + codeClient + "\n\tLocalité : " + localite + "\n\tChiffre d'affaire réalisé : " + caREalise + "\n\tNb de commandes passées : " + nbCdes + "\n\tdate de création du client : " + dateCreation + "\n________________________________________________\n";
	}

	public TypeDeNumero getCodeClient() {
		return codeClient;
	}

	public String getNomPrenom() {
		return nomPrenom;
	}

	public String getLocalite() {
		return localite;
	}

	public float getCaREalise() {
		return caREalise;
	}

	public int getNbCdes() {
		return nbCdes;
	}


	public void setCodeClient(TypeDeNumero codeClient) {
		this.codeClient = codeClient;
	}

	public void setNomPrenom(String nomPrenom) {
		this.nomPrenom = nomPrenom;
	}

	public void setLocalite(String localite) {
		this.localite = localite;
	}

	public void setCaREalise(float caREalise) {
		this.caREalise = caREalise;
	}

	public void setNbCdes(int nbCdes) {
		this.nbCdes = nbCdes;
	}

	public DateUser getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(DateUser dateCreation) {
		this.dateCreation = dateCreation;
	}

	
	
	

}
