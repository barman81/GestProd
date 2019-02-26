package Affichage;

import Controleur.ControleurAchatVente;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import javax.swing.*;

public class FenetreVente extends JFrame implements ActionListener {

	private JButton btVente;
	private JTextField txtQuantite;
	private JComboBox<String> combo;

	public FenetreVente(String[] lesProduits) {
		setTitle("Vente");
		setBounds(500, 500, 200, 125);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		btVente = new JButton("Vente");
		txtQuantite = new JTextField(5);
		txtQuantite.setText("0");

		combo = new JComboBox<String>(lesProduits);
		combo.setPreferredSize(new Dimension(100, 20));
		contentPane.add(new JLabel("Produit"));
		contentPane.add(combo);
		contentPane.add(new JLabel("Quantité vendue"));
		contentPane.add(txtQuantite);
		contentPane.add(btVente);

		btVente.addActionListener(this);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		this.dispose();
		int qte = Integer.parseInt(txtQuantite.getText());
		try {
			ControleurAchatVente.vendreProduit(combo.getSelectedItem().toString(), qte);
		} catch (SQLException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	}

}
