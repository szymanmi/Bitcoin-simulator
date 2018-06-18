package repository;

import java.sql.*;

interface DataBase {
    void getUserData(int idUser);
    void checkUser(String username, String password);
    void createUser(String username, String password);
    void getUserBitcoind(String userId);
    void getUserDollars(String userId);
    void addUserDolars(String userId, float amount);
    void userBuyBitcoins(String userId, float amountBitcoins, float amountDolars);
    void userSelBitcoins(String userId, float amountBitcoins, float amountDolars);

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
}
