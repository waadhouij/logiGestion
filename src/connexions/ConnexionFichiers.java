package connexions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import iPane.ES;

public class ConnexionFichiers<TypeTable> {
	private String nomPhysique;
	private ES es = new ES();

	public ConnexionFichiers(String nom) {
		this.nomPhysique = nom;
	}

	public TypeTable lire() {
		TypeTable tab = null;
		try {
			FileInputStream fis = new FileInputStream(nomPhysique);
			ObjectInputStream ois = new ObjectInputStream(fis);
			tab = (TypeTable) ois.readObject();
			ois.close();
		} catch (FileNotFoundException fnfe) {
			es.affiche("!!!   UN NOUVEAU FICHIER "+ nomPhysique + " EST CREE A LA RACINE DU PROJET   !!!");
		} catch (IOException ioe) {
			es.affiche("!!!   PB PHYSIQUE   !!!");
			System.out.println(ioe.getMessage());

		} catch (ClassNotFoundException cnfe) {
			es.affiche("!!!   LA TABLE METIER EST NULL   !!!");
		}
		return tab;
	}

	public void ecrire(TypeTable tab) {
		try {
			FileOutputStream fos = new FileOutputStream(nomPhysique);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(tab);
			oos.close();
		} catch (FileNotFoundException fnfe) {
			es.affiche("!!!   UN NOUVEAU FICHIER "+ nomPhysique + " EST CREER A LA RACINE DU PROJET   !!!");
		} catch (IOException ioe) {
			es.affiche("!!!   PB PHYSIQUE   !!!");
			System.out.println(ioe.getMessage());
		}
	}

}
