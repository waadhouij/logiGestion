package Serie53;

import java.io.Serializable;

public class LigneDeCommande53 implements Serializable{
	private static final long serialVersionUID = 1L;
	private int code;
	private int quantite;

	public LigneDeCommande53(int code, int quantite) {
		this.code = code;
		this.quantite = quantite;
	}

	public LigneDeCommande53() {}
	
	public String facturer(TableDesArticles53 tabArt) {
		ArticleAbstrait53 art = tabArt.retourner(code);
		return art.facturer(quantite);
	}
	
	public float prixTotal(TableDesArticles53 tabArt) {
		ArticleAbstrait53 art = tabArt.retourner(code);
		if (art != null) return art.prixFacture(quantite); // exemple de liaison Dynamique
		return 0;
	}
	
	public String toString() {
		return code +"\t" + quantite;
	}

	public int getCode() {
		return code;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}



}
