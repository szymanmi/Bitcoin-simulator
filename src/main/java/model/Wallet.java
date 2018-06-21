package model;

public class Wallet {
	public static double getDollarsFromDatabase(int userId) {
		DataBaseJDBC baza = new DataBaseJDBC();
		return baza.getUserPLN(userId);

	}
	public static double getBitcoinsFromDatabase(int userId) {
		DataBaseJDBC baza = new DataBaseJDBC();
		return baza.getUserBitcoins(userId);

	}

	public static void setUpdatedPLN(int userId, double PLNToAdd) {
		DataBaseJDBC baza = new DataBaseJDBC();
		baza.addUserPLN(userId, PLNToAdd);
	}

	public static void sell(int userId, double BTC, double PLN) {
		DataBaseJDBC baza = new DataBaseJDBC();
		baza.userUpdateAccountState(userId, BTC, PLN);
	}
}
