package model;

public interface DataBase {
    int createUser(String username, String password);

    double getUserBitcoins(int userId);

    double getUserPLN(int userId);

    double[] getUserBitcoinsAndPLN(int userId);

    double addUserPLN(int userId, double valueToAdd);
}
