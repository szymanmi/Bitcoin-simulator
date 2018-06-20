package repository;

import java.sql.*;

public class DataBase {
    public String getUserData(int idUser){ return null;}
    public int getUserName(String username, String password){ return 0;}
    public int createUser(String username, String password){ return 0;}
    public double getUserBitcoins(int userId){ return 0;}
    public double getUserDollars(int userId){ return 0;}
    public double[] getUserBitcoinsAndDolars(int userId){ double ret[] = {0, 0}; return ret;}
    public double addUserDolars(int userId, double amount){ return 0;}
    public double[] userBuyBitcoins(int userId, double amountBitcoins, double amountDolars){ double ret[] = {0, 0}; return ret;}
    public double[] userSellBitcoins(int userId, double amountBitcoins, double amountDolars){double ret[] = {0, 0}; return ret;}
    //void getUserHistorry(St)

}
