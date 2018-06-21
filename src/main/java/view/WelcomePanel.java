package view;

import model.Login;
import model.Register;
import model.User;
import model.Wallet;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static model.Bitcoin.getBitcoinValue;

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


		JLabel BTCValueLabel = new JLabel("      Aktualna wartość BTC w PLN: " + String.valueOf(getBitcoinValue()));
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
			if (Login.isLoginDataCorrect(login.getText(), password.getText()) > 0) {
				int userId = Login.isLoginDataCorrect(login.getText(), password.getText());
				this.loggedUser = new User(userId, login.getText(), Wallet.getDollarsFromDatabase(userId), Wallet.getBitcoinsFromDatabase(userId));
				this.userCurrentlyLoggedIn = true;
				//System.out.println(loggedUser.getDollars());
				//System.out.println(Wallet.getDollarsFromDatabase(userId));
			} else {
				System.out.println("wprowadziels bledne dane");
			}

		} else {
			System.out.println("anulowales wpisywanie danych");
		}

	}

	private void register() {
		JTextField login = new JTextField();
		JPasswordField password = new JPasswordField();
		final JComponent[] inputs = new JComponent[]{
				new JLabel("Login: "), login,
				new JLabel("Hasło: "), password
		};
		int result = JOptionPane.showConfirmDialog(null, inputs, "Zarejestruj się", JOptionPane.PLAIN_MESSAGE);
		if (result == JOptionPane.OK_OPTION) {
			if(Register.registerUser(login.getText(), password.getText()) > 0)
				System.out.println("rejestracja udana");
		}
	}

	private void exit() {
		System.exit(0);
	}
}
