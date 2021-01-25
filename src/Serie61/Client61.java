package Serie61;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import Serie53.*;
import iPane.ES;
import java.awt.Font;
import java.awt.Color;

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
		this.setSize(300,200);
		this.setTitle("MENU DE LA SUPERETTE");
		getContentPane().setLayout(null);
		gestArt.setBounds(0, 0, 300, 32);
		gestArt.setBackground(Color.RED);
		gestArt.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		getContentPane().add(gestArt);
		gestArt.addActionListener(this);
		gestCde.setBounds(0, 35, 284, 32);
		gestCde.setBackground(Color.RED);
		gestCde.setFont(new Font("Tahoma", Font.PLAIN, 18));
		getContentPane().add(gestCde);
		gestCde.addActionListener(this);
		gestCli.setBounds(0, 64, 284, 32);
		gestCli.setBackground(Color.RED);
		gestCli.setFont(new Font("Tahoma", Font.PLAIN, 18));
		getContentPane().add(gestCli);
		gestCli.addActionListener(this);
		gestFact.setBounds(0, 96, 284, 32);
		gestFact.setBackground(Color.RED);
		gestFact.setFont(new Font("Tahoma", Font.PLAIN, 18));
		getContentPane().add(gestFact);
		gestFact.addActionListener(this);
		fin.setBounds(0, 128, 284, 32);
		fin.setFont(new Font("Tahoma", Font.PLAIN, 18));
		fin.setBackground(Color.RED);
		getContentPane().add(fin);
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
