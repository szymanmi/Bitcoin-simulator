package view;

import repository.Login;

import javax.swing.*;

import static repository.Bitcoin.getBitcoinValue;

public class MainWindow extends JFrame {

	public MainWindow() {
		super("janekcoin na zaliczenie");
		setSize(350, 350);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		String userName, password;
		do {
			userName = getLogin();
			password = getPassword();
		}
		while (!Login.isLoginDataCorrect(userName, password));




		String price = String.valueOf(getBitcoinValue());
		JLabel pricelbl = new JLabel("Aktualna cena janka to: " + price);
		JPanel pane = new JPanel();
		JButton buyJanek = new JButton("Kup Janka");
		pane.add(buyJanek);
		JButton sellJanek = new JButton("Sprzedaj Janka");
		pane.add(sellJanek);
		ImageIcon ii = new ImageIcon("src/main/resources/janek.png");
		JLabel lbl = new JLabel(ii);
		pane.add(pricelbl);
		pane.add(lbl);
		add(pane);

		setVisible(true);
	}

	private String getLogin() {
		return JOptionPane.showInputDialog(null, "Username (janek):");
	}
	private String getPassword() {
		return JOptionPane.showInputDialog(null, "Password (janek):");
	}
}
