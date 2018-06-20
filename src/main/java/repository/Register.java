package repository;

public class Register {
	public static int registerUser(String userName, String password){
		DataBaseJDBC baza = new DataBaseJDBC();
		return baza.createUser(userName, password);

	}
}
