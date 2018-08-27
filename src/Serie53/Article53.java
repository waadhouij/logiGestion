package Serie53;

public class Article53 extends ArticleAbstrait53 {
	private static final long serialVersionUID = 1L;

	public Article53(int code, String designation, float pu) {
		super(code, designation, pu);
	}

	public Article53() {
	}

	public String toString() {
		return "Code: " + getCode() + "\t\tDésignation: " + getDesignation() + "\t\tP.U.: " + getPu();
	}
	
	public float prixFacture(int quantite) {
		return getPu() * quantite;
	}
	
	public String facturer(int quantite) {
		return " " + getCode() + "\t"+ getDesignation() + "\t\t" + quantite + "\t" + getPu() +"\t" + (int)(prixFacture(quantite) * 100) / 100f;
	}

}
