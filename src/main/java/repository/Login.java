package repository;

public class Login {
    private static DataBase dataBase = new DataBaseJDBC();

    public static User registry(String userName, String password) {
        int useerId = dataBase.createUser(userName,password);
        return new User(userName,0,0);
    }
}
