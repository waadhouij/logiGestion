package Serie53;

import java.io.Serializable;

public abstract class ArticleAbstrait53 implements Serializable{
	private static final long serialVersionUID = 1L;
	private int code;
	private String designation;
	private float pu;

	public ArticleAbstrait53(int code, String designation, float pu) {
		this.code = code;
		this.designation = designation;
		this.pu = pu;
	}

	public ArticleAbstrait53() {
	}

	public abstract String toString();
	public abstract float prixFacture(int quantite);
	public abstract String facturer(int quantite);

	public int getCode() {
		return code;
	}

	public String getDesignation() {
		return designation;
	}

	public float getPu() {
		return pu;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public void setPu(float pu) {
		this.pu = pu;
	}

}
