package Serie53;

import java.io.Serializable;

import Utils.DateUser;

public class Facture53 <TypeDeNumero> implements Serializable{
	private static final long serialVersionUID = 1L;
	private TypeDeNumero numFacture;
	private DateUser dateFacture;
	private String numClient;
	private String facture;
	
	public Facture53(TypeDeNumero numFacture, DateUser dateFacture, String numClient, String facture) {
		this.numFacture = numFacture;
		this.dateFacture = dateFacture;
		this.numClient = numClient;
		this.facture = facture;
	}

	public Facture53() {}
	
	public String toString() {
		String st = "_______________________________________________________________________________________________________________________________\n"
					+ "\n\n\t *** Client N° " + numClient + "\tFACTURE N° "+ numFacture + "\t Date de la Facture : "+ dateFacture + "    ***\n" + facture;
		return st;
		
	}
	
	public TypeDeNumero getNumFacture() {
		return numFacture;
	}
	public DateUser getDateFacture() {
		return dateFacture;
	}
	public String getNumClient() {
		return numClient;
	}
	public String getFacture() {
		return facture;
	}
	public void setNumFacture(TypeDeNumero numFacture) {
		this.numFacture = numFacture;
	}
	public void setDateFacture(DateUser dateFacture) {
		this.dateFacture = dateFacture;
	}
	public void setNumClient(String numClient) {
		this.numClient = numClient;
	}
	public void setFacture(String facture) {
		this.facture = facture;
	}

}
