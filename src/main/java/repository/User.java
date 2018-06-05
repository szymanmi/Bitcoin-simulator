package repository;

public class User {
	private String userName;
	private double dollars;
	private double bitcoins;

	public User(String userName, double dollars, double bitcoins) {
		this.userName = userName;
		this.dollars = dollars;
		this.bitcoins = bitcoins;

	}

	public String getUserName() {
		return userName;
	}

	public double getDollars() {
		return dollars;
	}

	public double getBitcoins() {
		return bitcoins;
	}

	/*
	TODO:
	metoda która zmieni wartości dollars oraz bitcoins podczas zakupu
	 */
}
