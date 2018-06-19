package view;

import repository.Login;
import repository.User;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static repository.Bitcoin.getBitcoinValue;

class WelcomePanel extends JPanel {
	private User loggedUser;
	private volatile boolean userCurrentlyLoggedIn = false;

	WelcomePanel() throws IOException {
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


		JLabel BTCValueLabel = new JLabel("  Aktualna wartość BTC w PLN: " + String.valueOf(getBitcoinValue()));
		BTCValueLabel.setFont(new Font("Sans Serif", 0, 16));
		add(BTCValueLabel, BorderLayout.CENTER);
		exitButton.addActionListener(event -> exit());
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
		JTextField login = new JTextField();
		JPasswordField password = new JPasswordField();
		final JComponent[] inputs = new JComponent[]{
				new JLabel("Login: "), login,
				new JLabel("Hasło: "), password
		};

		int result = JOptionPane.showConfirmDialog(null, inputs, "Zaloguj się", JOptionPane.PLAIN_MESSAGE);
		if (result == JOptionPane.OK_OPTION) {
			if (Login.isLoginDataCorrect(login.getText(), password.getText())) {
				this.userCurrentlyLoggedIn = true;
				this.loggedUser = new User(login.getText(), User.getDollarsFromDatabase(), User.getBitcoinsFromDatabase());
			}
			else{
				System.out.println("wprowadziels bledne dane");
			}

		} else {
			System.out.println("anulowales wpisywanie danych");
		}

	}

	private String register() {
		/*
		TODO
		no ogarnąć tę funkcję żeby coś konkretnego robiła
		 */
		return JOptionPane.showInputDialog(null, "rejestracja");
	}

	private void exit(){
		System.exit(0);
	}
}
