package view;

import model.User;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

import static model.Bitcoin.getBitcoinValue;

class MainPanel extends JPanel {
	private User loggedUser;
	private JLabel[] userInfoLabel;

	MainPanel(User loggedUser) throws IOException {
		this.loggedUser = loggedUser;

		JPanel userInfoPanel = new JPanel();
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;

		userInfoPanel.setLayout(new GridBagLayout());
		userInfoLabel = new JLabel[3];
		userInfoLabel[0] = new JLabel("Username: " + loggedUser.getUserName());
		userInfoLabel[1] = new JLabel("Dolary: " + loggedUser.getDollars().setScale(2, RoundingMode.DOWN).toPlainString());
		userInfoLabel[2] = new JLabel("Bitcoiny: " + loggedUser.getBitcoins().toPlainString());

		for (int i = 0; i < 3; i++) {
			userInfoLabel[i].setFont(new Font("Sans Serif", 0, 16));
			c.gridy = i;
			userInfoPanel.add(userInfoLabel[i], c);
		}

		String BTCPrice = String.valueOf(getBitcoinValue());
		JLabel currentBTCPriceLabel = new JLabel("Aktualna wartość BTC: " + BTCPrice);
		currentBTCPriceLabel.setFont(new Font("Sans Serif", 0, 18));

		ImageIcon buyIcon = new ImageIcon("src/main/resources/buyImage.gif");
		ImageIcon sellIcon = new ImageIcon("src/main/resources/sellImage.gif");
		ImageIcon exitIcon = new ImageIcon("src/main/resources/exitImage.gif");
		JButton buyButton = new JButton("Kup BTC", buyIcon);
		JButton sellButton = new JButton("Sprzedaj BTC", sellIcon);
		JButton exitButton = new JButton("Wyjdź", exitIcon);
		exitButton.addActionListener(event -> exit());
		buyButton.addActionListener(event -> {
			try {
				buy();
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		sellButton.addActionListener(event -> {
			try {
				sell();
			} catch (IOException e) {
				e.printStackTrace();
			}
		});


		add(userInfoPanel);
		add(buyButton);
		add(sellButton);
		add(currentBTCPriceLabel);
		add(exitButton);
	}

	private void buy() throws IOException {
		String amountToBuy = JOptionPane.showInputDialog(null, "Ile chcesz kupić?");
		double value = Double.parseDouble(amountToBuy);
		BigDecimal temp = new BigDecimal(value);
		BigDecimal temp2 = loggedUser.getDollars();
		temp = temp.multiply(new BigDecimal(getBitcoinValue()));

		if (temp.doubleValue() <= temp2.doubleValue()) {
			loggedUser.setDollars(temp2.subtract(temp));
			temp = loggedUser.getBitcoins();
			temp = temp.add(new BigDecimal(value));
			loggedUser.setBitcoins(temp);
			refreshUserInfo();
		}

	}

	private void sell() throws IOException {
		String amountToSell = JOptionPane.showInputDialog(null, "Ile chcesz sprzedać?");
		double value = Double.parseDouble(amountToSell);
		BigDecimal first = new BigDecimal(value);

		if (first.doubleValue() <= loggedUser.getBitcoins().doubleValue()) {
			first = first.multiply(new BigDecimal(getBitcoinValue()));
			first = first.add(loggedUser.getDollars());
			loggedUser.setDollars(first);

			first = loggedUser.getBitcoins();
			System.out.println(first);
			first = first.subtract(new BigDecimal(value));
			loggedUser.setBitcoins(first);
			refreshUserInfo();
		}
	}

	private void refreshUserInfo() {
		userInfoLabel[0].setText("Username: " + loggedUser.getUserName());
		userInfoLabel[1].setText("Dolary: " + loggedUser.getDollars().setScale(2, RoundingMode.DOWN).toPlainString());
		userInfoLabel[2].setText(" Bitcoiny: " + loggedUser.getBitcoins().toPlainString());
	}

	private void exit() {
		System.exit(0);
	}
}
