package repository;

import java.sql.*;

interface DataBase {
    void getUserData(int idUser);
    void checkUser(String username, String password);
    void createUser(String username, String password);
    void getUserBitcoins(String userId);
    void getUserDollars(String userId);
    void getUserBitcoinsAndDolars(String userId);
    void addUserDolars(String userId, double amount);
    void userBuyBitcoins(String userId, double amountBitcoins, double amountDolars);
    void userSellBitcoins(String userId, double amountBitcoins, double amountDolars);

}

public class DataBaseJDBC implements DataBase {
    private static String JDBCDriver = "com.mysql.jdbc.Driver";
    private static String DBurl = "jdbc:mysql://localhost";
    private static String DBUser = "";
    private static String DBPassword = "";

    private Connection conn = null;
    private Statement stmnt = null;
    public DataBaseJDBC(){

    }
    private void openConnection(){
        try{
            Class.forName(JDBCDriver);
            //łączenie z bazą
            this.conn = DriverManager.getConnection(DBurl, DBUser, DBPassword);

        }
        catch(SQLException se){
            se.printStackTrace();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    private void closeConnection(){
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getUserData(int idUser){
        this.openConnection();
        try{
            this.stmnt = this.conn.createStatement();
            String sql;
            sql = "SELECT id, username FROM users";
            ResultSet result = this.stmnt.executeQuery(sql);



            this.stmnt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        this.closeConnection();

        //return Array();
    }

    public void checkUser(String username, String password){
        //int >=0 sukces; < 0 - porażka
        return userId;
    }
    public void createUser(String username, String password){
        //int >=0 sukces; < 0 - porażka
        return userId;
    }
    public void getUserBitcoins(String userId){
        //double
        return bitcoins;
    }
    public void getUserDollars(String userId){
        //double
        return dolars;
    }
    public void getUserBitcoinsAndDolars(String userId){
        //tablicy double[2]
        double[] ret = {bitcoins, dolars};
        return ret;
    }
    public void addUserDolars(String userId, double amount){
        //double
        return dolars;
    }
    public void userBuyBitcoins(String userId, double amountBitcoins, double amountDolars){
        //tablicy double[2]
        double[] ret = {bitcoins, dolars};
        return ret;
    }
    public void userSellBitcoins(String userId, double amountBitcoins, double amountDolars){
        //tablicy double[2]
        double[] ret = {bitcoins, dolars};
        return ret;
    }
}
