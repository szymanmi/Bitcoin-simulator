package model;

import java.math.BigDecimal;
import java.math.MathContext;

public class User {
	private String userName;
	private BigDecimal PLN;
	private BigDecimal bitcoins;

	public User(String userName, double dollars, double bitcoins) {
		this.userName = userName;
		this.PLN = new BigDecimal(dollars,MathContext.DECIMAL64).stripTrailingZeros();
		this.bitcoins = new BigDecimal(bitcoins,MathContext.DECIMAL64).stripTrailingZeros();
	}

	public void setDollars(BigDecimal PLN) {
		this.PLN = PLN;
	}

	public void setBitcoins(BigDecimal bitcoins) {
		this.bitcoins = bitcoins;
	}

	public String getUserName() {
		return userName;
	}

	public BigDecimal getDollars() {
		return PLN;
	}

	public BigDecimal getBitcoins() {
		return bitcoins;
	}

	public static double getDollarsFromDatabase() {
		return 50000;
	}

	public static double getBitcoinsFromDatabase() {
		return 0.3;
	}


	/*
	TODO:
	metoda która zmieni wartości dollars oraz bitcoins podczas zakupu
	 */
}
