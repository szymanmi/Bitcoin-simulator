package repository;

import java.sql.*;

public class DataBase {
    public String getUserData(int idUser){ return null;}
    public int getUserName(String username, String password){ return -1;}
    public int createUser(String username, String password){ return -1;}
    public double getUserBitcoins(int userId){ return -1;}
    public double getUserDollars(int userId){ return -1;}
    public double[] getUserBitcoinsAndDolars(int userId){ double ret[] = {-1, -1}; return ret;}
    public double addUserDolars(int userId, double valueToAdd){ return -1;}
    public double payOutUserDolars(int userId, double valueToAdd){ return -1;}
    public double[] userUpdateAccountState(int userId, double amountBitcoins, double amountDolars){ double ret[] = {-1, -1}; return ret;}
    //void getUserHistorry(St)

}
