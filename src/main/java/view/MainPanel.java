package view;

import repository.User;

import javax.swing.*;
import java.io.IOException;

import static repository.Bitcoin.getBitcoinValue;

class MainPanel extends JPanel {
	private User loggedUser;
	private JLabel userInfoLabel;

	MainPanel(User loggedUser) throws IOException {
		this.loggedUser = loggedUser;
		//setLayout(new GridLayout());

		userInfoLabel = new JLabel("Username: " + loggedUser.getUserName()
				+ " Dolary: " + String.valueOf(loggedUser.getDollars())
				+ " Bitcoiny: " + String.valueOf(loggedUser.getBitcoins()));
		System.out.println("aaa");

		String BTCPrice = String.valueOf(getBitcoinValue());
		JLabel currentBTCPriceLabel = new JLabel("Aktualna wartość BTC: " + BTCPrice);

		JButton buyButton = new JButton("Kup BTC");
		buyButton.addActionListener(event -> {
			try {
				buy();
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		JButton sellButton = new JButton("Sprzedaj BTC");
		sellButton.addActionListener(event -> {
			try {
				sell();
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		add(userInfoLabel);
		add(currentBTCPriceLabel);
		add(buyButton);
		add(sellButton);
	}

	private void buy() throws IOException {
		String amountToBuy = JOptionPane.showInputDialog(null, "Ile chcesz kupić?");
		double value = Double.parseDouble(amountToBuy);
		if (value * getBitcoinValue() <= loggedUser.getDollars()) {
			loggedUser.setDollars(loggedUser.getDollars() - value * getBitcoinValue());
			loggedUser.setBitcoins(loggedUser.getBitcoins() + value);
			refreshUserInfo(userInfoLabel);
		}

	}

	private void sell() throws IOException {
		String amountToSell = JOptionPane.showInputDialog(null, "Ile chcesz sprzedać?");
		double value = Double.parseDouble(amountToSell);
		if (value <= loggedUser.getBitcoins()){
			loggedUser.setDollars(loggedUser.getDollars() + value * getBitcoinValue());
			loggedUser.setBitcoins(loggedUser.getBitcoins() - value);
			refreshUserInfo(userInfoLabel);
		}
	}
	private void refreshUserInfo(JLabel info) {
		info.setText("Username: " + loggedUser.getUserName()
				+ " Dolary: " + String.valueOf(loggedUser.getDollars())
				+ " Bitcoiny: " + String.valueOf(loggedUser.getBitcoins()));
	}
}
