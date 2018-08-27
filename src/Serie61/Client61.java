package Serie61;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import Serie53.*;
import iPane.ES;

public class Client61 extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	ES ES = new ES();
	TableDesArticles53 tabArt = new TableDesArticles53();
	TableDesClientsS tabCli = new TableDesClientsS();
	TableDesCommandes53 tabCde = new TableDesCommandes53();
	TableDesFactures53 tabFact = new TableDesFactures53();
	JButton gestArt = new JButton("GESTION DES ARTICLES");
	JButton gestCde = new JButton("GESTION DES COMMANDES");
	JButton gestCli = new JButton("GESTION DES CLIENTS");
	JButton gestFact = new JButton("GESTION DES FACTURES");
	JButton fin = new JButton("FIN");
	
	public static void main(String[] args) {
		new FrameIdentification();
	}
	
	public Client61() {
		this.setLayout(new GridLayout(5,1,0,0));
		this.setSize(300,200);
		this.setTitle("MENU DE LA SUPERETTE");
		
		this.add(gestArt);
		gestArt.addActionListener(this);
		this.add(gestCde);
		gestCde.addActionListener(this);
		this.add(gestCli);
		gestCli.addActionListener(this);
		this.add(gestFact);
		gestFact.addActionListener(this);
		this.add(fin);
		fin.addActionListener(this);
		
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		if(evt.getSource() == gestArt) {
			new GestionTableDesArticles("fichArt", tabArt, tabCde);
		}else if (evt.getSource() == gestCde){
			ES.affiche("Vous êtes dans le menu commandes");;
		} else if (evt.getSource() == gestCli){
			ES.affiche("Vous êtes dans le menu Client");
		} else if (evt.getSource() == gestFact){
			ES.affiche("Vous êtes dans le menu Facture");
		}else if(evt.getSource() == fin) {
			setVisible(false);
		}
		
	}
}
