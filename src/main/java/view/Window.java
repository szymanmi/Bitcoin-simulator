package view;

import repository.Login;
import repository.User;

import javax.swing.*;


import static repository.Bitcoin.getBitcoinValue;

public class Window extends JFrame {
	private volatile boolean userCurrentlyLoggedIn = false;
	private User loggedUser;
	public Window() {
		super("janekcoin na zaliczenie");
		setSize(350, 350);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		welcomeWindow();
		mainWindow();

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

		//wait until the user logs in
		while(!this.userCurrentlyLoggedIn){
			try {
				Thread.sleep(200);
			} catch(InterruptedException ignored) { }
		}
		remove(panel);

		setVisible(true);
	}

	private void mainWindow(){
		JPanel pane = new JPanel();
		JLabel userInfolbl = new JLabel("Username: " + loggedUser.getUserName()
				+ " Dolary: " + String.valueOf(loggedUser.getDollars())
				+ " Bitcoiny: " + String.valueOf(loggedUser.getBitcoins()));
		pane.add(userInfolbl);

		String price = String.valueOf(getBitcoinValue());
		JLabel pricelbl = new JLabel("Aktualna cena janka to: " + price);
		pane.add(pricelbl);
		JButton buyButton = new JButton("Kup Janka");
		buyButton.addActionListener(event -> buy(userInfolbl));
		pane.add(buyButton);
		JButton sellButton = new JButton("Sprzedaj Janka");
		sellButton.addActionListener(event -> sell(userInfolbl));
		pane.add(sellButton);
		ImageIcon ii = new ImageIcon("src/main/resources/janek.png");
		JLabel lbl = new JLabel(ii);
		pane.add(lbl);
		add(pane);

		setVisible(true);

	}

	private void refreshUserInfo(JLabel info){
		info.setText("Username: " + loggedUser.getUserName()
				+ " Dolary: " + String.valueOf(loggedUser.getDollars())
				+ " Bitcoiny: " + String.valueOf(loggedUser.getBitcoins()));
	}

	private void buy(JLabel info){
		String amountToBuy = JOptionPane.showInputDialog(null, "Ile chcesz kupić?");
		double value = Double.parseDouble(amountToBuy);
		if (value * getBitcoinValue() <= loggedUser.getDollars()){
			loggedUser.setDollars(loggedUser.getDollars() - value * getBitcoinValue());
			loggedUser.setBitcoins(loggedUser.getBitcoins() + value);
			refreshUserInfo(info);
		}

	}

	private void sell(JLabel info){
		String amountToSell = JOptionPane.showInputDialog(null, "Ile chcesz sprzedać?");
		double value = Double.parseDouble(amountToSell);
		if (value <= loggedUser.getBitcoins()){
			loggedUser.setDollars(loggedUser.getDollars() + value * getBitcoinValue());
			loggedUser.setBitcoins(loggedUser.getBitcoins() - value);
			refreshUserInfo(info);
		}
	}

	private String register() {
		/*
		TODO
		no ogarnąć tę funkcję żeby coś konkretnego robiła
		 */
		return JOptionPane.showInputDialog(null, "rejestracja");
	}

	private void login() {
		String login = JOptionPane.showInputDialog(null, "Username (janek):");
		String password = JOptionPane.showInputDialog(null, "Password (janek):");
		if(Login.isLoginDataCorrect(login, password)) {
			this.userCurrentlyLoggedIn = true;
			this.loggedUser = new User(login, User.getDollarsFromDatabase(), User.getBitcoinsFromDatabase());
		}
	}


}
