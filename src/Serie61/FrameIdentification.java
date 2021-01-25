package Serie61;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import iPane.ES;
import java.awt.Color;
import java.awt.Font;

public class FrameIdentification extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	ES ES = new ES();
	JTextField user = new JTextField(15);
	JPasswordField pwd = new JPasswordField(10);
	JButton valider = new JButton("VALIDER");
	JButton fin  = new JButton("FIN");
	
	public FrameIdentification() {
		getContentPane().setLayout(new GridLayout(4, 2, 0, 0));
		this.setSize(400, 300);
		this.setTitle("FRAME IDENTIFICATION");
		
		ImageIcon image = recup("../../ressources/img/logo.png");
		JLabel lab1 = new JLabel(image);
		JPanel pan0 = new JPanel();
		pan0.add(lab1);
		getContentPane().add(pan0);
		
		JPanel pan1 = new JPanel();
		pan1.setBackground(Color.RED);
		user.setBounds(170, 6, 126, 20);
		user.addActionListener(this);
		pan1.setLayout(null);
		pan1.add(user);
		getContentPane().add(pan1);
		JLabel username = new JLabel("Username");
		username.setBounds(77, 2, 78, 22);
		pan1.add(username);
		username.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JPanel pan2 = new JPanel();
		pan2.setBackground(Color.RED);
		JLabel mdp = new JLabel("Password");
		mdp.setFont(new Font("Tahoma", Font.PLAIN, 18));
		pwd.addActionListener(this);
		pan2.add(mdp);
		pan2.add(pwd);
		getContentPane().add(pan2);
		
		JPanel pan3 = new JPanel();
		valider.setBackground(Color.GREEN);
		valider.setFont(new Font("Tahoma", Font.PLAIN, 16));
		valider.addActionListener(this);
		fin.setFont(new Font("Tahoma", Font.PLAIN, 17));
		fin.addActionListener(this);
		pan3.add(valider);
		pan3.add(fin);
		getContentPane().add(pan3);
		
		this.setLocationRelativeTo(null);//centre la fenêtre dans l'ordinateur
		
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent evt) {
		if (evt.getSource() == valider || evt.getSource() == pwd) {
			ES.affiche("CONTROLE DU MDP");
			String userS = user.getText();
			char[] mdpS = pwd.getPassword();
			String mdpR = recup(mdpS);
			if(verif(userS, mdpR)) {
				ES.affiche("OK");
				new Client61();
			}	else {
				ES.affiche("PB ressaisissez");
				raz();//remise à zéro
			}
		}
		if(evt.getSource() ==fin) {
			ES.affiche("AU REVOIR A BIENTOT ");
			System.exit(0);//peu importe le chiffre !
		}
	}

	private void raz() {
		user.setText("");
		pwd.setText("");
	}

	private boolean verif(String userS, String pwd) {
		return (userS.equals(pwd));
	}

	private String recup(char[] tab) {
		String mes = "";
		for (int i = 0 ; i < tab.length ; i++) {
			mes += tab[i];
		}
		return mes;
	}
	
	public ImageIcon recup(String chemin) {
		return new ImageIcon(ImageIcon.class.getResource(chemin));
	}
	
}
