package MesInterfaces;

import java.io.Serializable;
import java.util.Set;

public interface InterfaceStructure <Metier, TypeDeLaCle> extends Serializable{

	public abstract Metier retourner(TypeDeLaCle cle);
	public abstract void ajouter(Metier metier) ;
	public abstract void supprimer(TypeDeLaCle cle);
	public abstract int taille();
	public Set<TypeDeLaCle> cle();
}
