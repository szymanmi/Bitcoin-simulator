package model;

public class Buy {

	public static void buy(int userId, double BTC, double PLN) {
		DataBaseJDBC baza = new DataBaseJDBC();
		baza.userUpdateAccountState(userId, BTC, PLN);
	}
}
