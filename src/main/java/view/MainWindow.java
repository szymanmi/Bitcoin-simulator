package view;

import com.sun.xml.internal.bind.v2.TODO;
import repository.Login;

import javax.swing.*;


import static repository.Bitcoin.getBitcoinValue;

public class MainWindow extends JFrame {
	private volatile boolean userCurrentlyLoggedIn = false;
	public MainWindow() {
		super("janekcoin na zaliczenie");
		setSize(350, 350);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);


		welcomeWindow();
		//removeAll();
		System.out.println("elo");
		String price = String.valueOf(getBitcoinValue());
		JLabel pricelbl = new JLabel("Aktualna cena janka to: " + price);
		JPanel pane = new JPanel();

		JButton buyButton = new JButton("Kup Janka");
		pane.add(buyButton);
		JButton sellJanek = new JButton("Sprzedaj Janka");
		pane.add(sellJanek);
		ImageIcon ii = new ImageIcon("src/main/resources/janek.png");
		JLabel lbl = new JLabel(ii);
		pane.add(pricelbl);
		pane.add(lbl);
		add(pane);


		setVisible(true);
	}

	private void welcomeWindow() {
		JPanel panel = new JPanel();
		JLabel welcomeMessage = new JLabel("Witaj w BitJanku (nazwa tymczasowa)");
		JButton loginButton = new JButton("Zaloguj");
		JButton registerButton = new JButton("Zarejestruj");
		panel.add(welcomeMessage);
		panel.add(loginButton);
		panel.add(registerButton);

		loginButton.addActionListener(event -> login());
		registerButton.addActionListener(event -> register());
		add(panel);
		setVisible(true);

		//wait untill user is not logged
		while(!this.userCurrentlyLoggedIn){
			try {
				Thread.sleep(200);
			} catch(InterruptedException ignored) { }
		}
		remove(panel);
		setVisible(true);
	}

	private String getLogin() {
		return JOptionPane.showInputDialog(null, "Username (janek):");
	}

	private String getPassword() {
		return JOptionPane.showInputDialog(null, "Password (janek):");
	}

	private String register() {
		/*
		TODO
		no ogarnąć tę funkcję żeby coś konkretnego robiła
		 */
		return JOptionPane.showInputDialog(null, "rejestracja");
	}

	private void login() {
		String login = getLogin();
		String password = getPassword();
		if(Login.isLoginDataCorrect(login, password))
			this.userCurrentlyLoggedIn = true;

	}


}
