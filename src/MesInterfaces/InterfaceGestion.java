package MesInterfaces;


public interface InterfaceGestion <TypeStructure, Metier>{
	
	public abstract void menuGeneral(TypeStructure tab, Object ...objects) throws Exception;
	// solution 2 : on oblige à passer en paramètre un Objet qui implémente l'interface InterfaceStructure
	//	public abstract void menuGeneral(TypeStructure tab, InterfaceStructure<?, ?> ...tab1) throws AbandonException;
	public abstract int menuChoix(Object ...objects) throws Exception;
	public abstract void creer(TypeStructure tab, Object ...objects) throws Exception;
	public abstract void supprimer(TypeStructure tab, Object ...objects) throws Exception;
	public abstract void afficher(TypeStructure tab) throws Exception;
	public abstract Metier saisie(TypeStructure tab, Object ...objects) throws Exception;;
	public abstract void modifier(TypeStructure tab, Object ...objects) throws Exception;
	public  void afficherTous(TypeStructure tabCde);

	public abstract void sauvegarder(TypeStructure tab);
	public abstract TypeStructure recuperer();

}
