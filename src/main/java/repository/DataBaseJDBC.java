package repository;

import java.sql.*;

public class DataBaseJDBC extends DataBase {
    private static String JDBCDriver = "com.mysql.jdbc.Driver";
    private static String DBurl = "jdbc:mysql://atypowa.cba.pl/atypowa_cba_pl?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static String DBUser = "atypowa";
    private static String DBPassword = "Atyp123@";

    private Connection conn = null;
    private PreparedStatement stmnt = null;
    public DataBaseJDBC(){

    }
    private void openConnection(String SQL) throws SQLException {
        //Class.forName(this.JDBCDriver);
        //łączenie z bazą
        this.conn = DriverManager.getConnection(DBurl, DBUser, DBPassword);
        this.stmnt = this.conn.prepareStatement(SQL);
    }

    private void closeConnection() throws SQLException {
        this.stmnt.close();
        this.conn.close();
    }

    public String getUserName(int idUser){
        /**
         * @return: (String) username if success, or null if fail
         */
        String username = null;
        int amount = 0;
        try{
            String sql;
            sql = "SELECT username FROM java_users WHERE id = ?";
            this.openConnection(sql);
            this.stmnt.setInt(1, idUser);

            ResultSet result = this.stmnt.executeQuery();

            while(result.next()){
                username = result.getString("username");
                amount++;
            }

            this.closeConnection();
        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println(e.getMessage());
        }

        if(amount == 1)
            return username;
        else
            return null;
    }

    public int checkUser(String username, String password){
        /**
         * @return: (int) id user >= 0 success; < 0 fail
         */
        int userId = -1, amount = 0;

        try{
            String sql;
            sql = "SELECT id FROM java_users WHERE LOWER(username) = ? AND password = ?";
            this.openConnection(sql);
            this.stmnt.setString(1, username.toLowerCase());
            this.stmnt.setString(2, password);
            ResultSet result = this.stmnt.executeQuery();

            while(result.next()){
                userId = result.getInt("id");
                amount++;
            }

            this.closeConnection();
        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println(e.getMessage());
        }
        if(amount == 1)
            return userId;
        else
            return -1;
    }
    public int createUser(String username, String password){
        /**
         * @return: (int) id user >= 0 success; < 0 fail (-2 - username exist)
         */
        int userId = -1, amount = 0;

        try{
            String sql;
            sql = "SELECT id FROM java_users WHERE LOWER(username) = ?";
            this.openConnection(sql);
            this.conn.setAutoCommit(false);
            this.stmnt.setString(1, username.toLowerCase());
            ResultSet result = this.stmnt.executeQuery();

            while(result.next()){
                userId = result.getInt("id");
                amount++;
            }
            if(amount > 0){
                this.conn.rollback();
                this.closeConnection();
                return -2;
            }
            sql = "INSERT INTO java_users (username, password) VALUES (?, ?)";
            this.stmnt = this.conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            this.stmnt.setString(1, username);
            this.stmnt.setString(2, password);
            this.stmnt.executeUpdate();
            result = this.stmnt.getGeneratedKeys();
            if(result.next())
                userId = result.getInt(1);

            sql = "INSERT INTO java_account_state (user_id, dollars, bitcoins) VALUES (?, 0, 0)";
            this.stmnt = this.conn.prepareStatement(sql);
            this.stmnt.setInt(1, userId);
            this.stmnt.executeUpdate();

            this.conn.commit();
            this.closeConnection();
        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println(e.getMessage());
        }
        if(userId > 0)
            return userId;
        else
            return -1;
    }
    public double getUserBitcoins(int userId){
        /**
         * @return: (double) amount of bitcoins if false is -1
         */
        int amount = 0;
        double bitcoins = -1;

        try{
            String sql;
            sql = "SELECT bitcoins FROM java_account_state WHERE user_id = ?";
            this.openConnection(sql);
            this.stmnt.setInt(1, userId);
            ResultSet result = this.stmnt.executeQuery();

            while(result.next()){
                bitcoins = result.getInt("bitcoins");
                amount++;
            }

            this.closeConnection();
        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println(e.getMessage());
        }
        if(amount == 1)
            return bitcoins;
        else
            return -1;
    }
    public double getUserDollars(int userId){
        //double
        /**
         * @return: (double) amount of bitcoins if false is -1
         */
        int amount = 0;
        double dollars = -1;

        try{
            String sql;
            sql = "SELECT dollars FROM java_account_state WHERE user_id = ?";
            this.openConnection(sql);
            this.stmnt.setInt(1, userId);
            ResultSet result = this.stmnt.executeQuery();

            while(result.next()){
                dollars = result.getInt("dollars");
                amount++;
            }

            this.closeConnection();
        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println(e.getMessage());
        }
        if(amount == 1)
            return dollars;
        else
            return -1;
    }
    public double[] getUserBitcoinsAndDolars(int userId){
        /**
         * @return: (double[2]) {amount of bitcoins, amount of dollars} if fail {-1, -1}
         */
        int amount = 0;
        double dollars = -1, bitcoins = -1;

        try{
            String sql;
            sql = "SELECT bitcoins, dollars FROM java_account_state WHERE user_id = ?";
            this.openConnection(sql);
            this.stmnt.setInt(1, userId);
            ResultSet result = this.stmnt.executeQuery();

            while(result.next()){
                dollars = result.getInt("dollars");
                bitcoins = result.getInt("bitcoins");
                amount++;
            }

            this.closeConnection();
        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println(e.getMessage());
        }
        double ret[] = {bitcoins, dollars};
        if(amount == 1)
            return ret;
        else {
            ret[0] = -1;
            ret[1] = -1;
            return ret;
        }
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
