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

	public void setDollars(double dollars) {
		this.dollars = dollars;
	}

	public void setBitcoins(double bitcoins) {
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

	public static double getDollarsFromDatabase(){
		return 50000;
	}

	public static double getBitcoinsFromDatabase(){
		return 0.3;
	}


	/*
	TODO:
	metoda która zmieni wartości dollars oraz bitcoins podczas zakupu
	 */
}
