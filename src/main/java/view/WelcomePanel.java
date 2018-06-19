package view;

import repository.Login;
import repository.User;

import javax.swing.*;
import java.awt.*;

class WelcomePanel extends JPanel {
	private User loggedUser;
	private volatile boolean userCurrentlyLoggedIn = false;

	WelcomePanel() {
		setLayout(new BorderLayout());

		ImageIcon loginIcon = new ImageIcon("src/main/resources/loginImage.gif");
		ImageIcon registerIcon = new ImageIcon("src/main/resources/registerImage.gif");
		ImageIcon exitIcon = new ImageIcon("src/main/resources/exitImage.gif");
		JButton loginButton = new JButton("Zaloguj", loginIcon);
		JButton registerButton = new JButton("Zarejestruj", registerIcon);
		JButton exitButton = new JButton("Wyjdź", exitIcon);
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.add(loginButton);
		buttonsPanel.add(registerButton);
		buttonsPanel.add(exitButton);
		add(buttonsPanel, BorderLayout.NORTH);


		loginButton.addActionListener(event -> login());
		registerButton.addActionListener(event -> register());
	}

	User getLoggedUser() {
		while (!this.userCurrentlyLoggedIn) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException ignored) {
			}
		}
		return loggedUser;
	}

	private void login() {
		String login = JOptionPane.showInputDialog(null, "Username (janek):");
		String password = JOptionPane.showInputDialog(null, "Password (janek):");
		if (Login.isLoginDataCorrect(login, password)) {
			this.userCurrentlyLoggedIn = true;
			this.loggedUser = new User(login, User.getDollarsFromDatabase(), User.getBitcoinsFromDatabase());
		}
	}

	private String register() {
		/*
		TODO
		no ogarnąć tę funkcję żeby coś konkretnego robiła
		 */
		return JOptionPane.showInputDialog(null, "rejestracja");
	}
}
