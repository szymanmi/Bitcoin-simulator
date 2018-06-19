package repository;

public class Login {
	public static boolean isLoginDataCorrect(String userName, String password) {
		/*
		TODO
		-sprawdzanie powinno wykorzystywać informacje z bazy danych
		 */
		return userName.length() > 3;
	}
}
