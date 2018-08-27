package Serie53;

/**
 * Article en promotion pour une quantité minimum. 
 * Pas de notion de période.
 */
public class ArticlePromo53 extends ArticleAbstrait53{
	private static final long serialVersionUID = 1L;
	private int quantiteMini;
	private float reduction;

	public ArticlePromo53(int code, String designation, float pu, int quantiteMini, float reduction) {
		super(code, designation, pu);
		this.quantiteMini = quantiteMini;
		this.reduction = reduction;
	}

	public ArticlePromo53() {
	}

	public String toString() {
		return "Code: " + getCode() + "\t\tDésignation: " + getDesignation() + "\t\tP.U.: " + getPu() + "\t* PROMO * Reduction: " + reduction + "\tQtite Mini:" + quantiteMini;
	}

	public float prixFacture(int quantite) {
		if (quantite < quantiteMini)
			return getPu() * quantite;
		else 
			return (getPu() * quantite)*(1-reduction/100) ;
	}
	
	public String facturer(int quantite) {
		return " " + getCode() 
			+ "\t"+ getDesignation() 
			+ "\t\t" + quantite 
			+"\t" + getPu() 
			+"\t" + (int) (getPu() * quantite) 
			+"\t" + (int)(prixFacture(quantite) * 100) / 100f 
			+ "\n\t\t\t\t\t\tArticle en Promo (remise de : " + reduction + "% pour une qtite mini de " + quantiteMini +")";
		
	}

	public int getQuantiteMini() {
		return quantiteMini;
	}

	public float getReduction() {
		return reduction;
	}

	public void setQuantiteMini(int quantiteMini) {
		this.quantiteMini = quantiteMini;
	}

	public void setReduction(float reduction) {
		this.reduction = reduction;
	}
	
	

}
