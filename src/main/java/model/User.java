package model;

import java.math.BigDecimal;
import java.math.MathContext;

public class User {
	private int userId;
	private String userName;
	private BigDecimal PLN;
	private BigDecimal bitcoins;

	public User(int userId, String userName, double dollars, double bitcoins) {
		System.out.println("user konstruktor");
		this.userId = userId;
		this.userName = userName;
		this.PLN = new BigDecimal(dollars,MathContext.DECIMAL64).stripTrailingZeros();
		this.bitcoins = new BigDecimal(bitcoins,MathContext.DECIMAL64).stripTrailingZeros();
		System.out.println("user koniec konstruktora");
	}

	public void setDollars(BigDecimal PLN) {
		this.PLN = PLN;
	}

	public void setBitcoins(BigDecimal bitcoins) {
		this.bitcoins = bitcoins;
	}

	public int getUserId() {
		return userId;
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

	public void setPLN(BigDecimal PLN) {
		this.PLN = PLN;
	}
}
