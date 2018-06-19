package view;

import repository.Login;
import repository.User;

import javax.swing.*;

public class WelcomePanel extends JPanel {
	private JButton loginButton = new JButton("Zaloguj");
	private JButton registerButton = new JButton("Zarejestruj");
	private User loggedUser;

	private volatile boolean userCurrentlyLoggedIn = false;

	public WelcomePanel() {
		add(loginButton);
		add(registerButton);

		loginButton.addActionListener(event -> login());
		registerButton.addActionListener(event -> register());
	}

	public User getLoggedUser() {
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
