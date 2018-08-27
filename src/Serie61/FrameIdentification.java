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

public class FrameIdentification extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	ES ES = new ES();
	JTextField user = new JTextField(15);
	JPasswordField pwd = new JPasswordField(10);
	JButton valider = new JButton("VALIDER");
	JButton fin  = new JButton("FIN");
	
	public FrameIdentification() {
		this.setLayout(new GridLayout(4, 2, 0, 0));
		this.setSize(400, 300);
		this.setTitle("FRAME IDENTIFICATION");
		
		ImageIcon image = recup("../../ressources/img/logo.png");
		JLabel lab1 = new JLabel(image);
		JPanel pan0 = new JPanel();
		pan0.add(lab1);
		this.add(pan0);
		
		JPanel pan1 = new JPanel();
		JLabel username = new JLabel("Username");
		user.addActionListener(this);
		pan1.add(username);
		pan1.add(user);
		this.add(pan1);
		
		JPanel pan2 = new JPanel();
		JLabel mdp = new JLabel("Password");
		pwd.addActionListener(this);
		pan2.add(mdp);
		pan2.add(pwd);
		this.add(pan2);
		
		JPanel pan3 = new JPanel();
		valider.addActionListener(this);
		fin.addActionListener(this);
		pan3.add(valider);
		pan3.add(fin);
		this.add(pan3);
		
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
