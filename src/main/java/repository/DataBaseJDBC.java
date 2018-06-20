package repository;

import java.sql.*;

public class DataBaseJDBC extends DataBase {
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

    public int getUserData(int idUser){
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
        return 0;
    }

    public int checkUser(String username, String password){
        /**
         * @return
         */
        //int >=0 sukces; < 0 - porażka
        int userId = 1;
        return userId;
    }
    public int createUser(String username, String password){
        //int >=0 sukces; < 0 - porażka
        int userId = 1;
        return userId;
    }
    public double getUserBitcoins(int userId){
        //double
        double bitcoins = 1;
        return bitcoins;
    }
    public double getUserDollars(int userId){
        //double
        double dolars = 1;
        return dolars;
    }
    public double[] getUserBitcoinsAndDolars(int userId){
        //tablicy double[2]
        double bitcoins = 1;
        double dolars = 1;
        double[] ret = {bitcoins, dolars};
        return ret;
    }
    public double addUserDolars(int userId, double amount){
        //double
        double dolars = 1;
        return dolars;
    }
    public double[] userBuyBitcoins(int userId, double amountBitcoins, double amountDolars){
        //tablicy double[2]
        double bitcoins = 1;
        double dolars = 1;
        double[] ret = {bitcoins, dolars};
        return ret;
    }
    public double[] userSellBitcoins(int userId, double amountBitcoins, double amountDolars){
        //tablicy double[2]
        //bleble ble
        double bitcoins = 1;
        double dolars = 1;
        double ret[] = {bitcoins, dolars};
        return ret;
    }
}
