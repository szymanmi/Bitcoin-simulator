package model;

public class Login {

    public static int isLoginDataCorrect(String userName, String password) {
        DataBaseJDBC baza = new DataBaseJDBC();

        return baza.checkUser(userName, password);
    }
}
