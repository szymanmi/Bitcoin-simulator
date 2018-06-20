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

            sql = "INSERT INTO java_account_state (user_id, PLN, bitcoins) VALUES (?, 0, 0)";
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
    public double getUserPLN(int userId){
        //double
        /**
         * @return: (double) amount of bitcoins if false is -1
         */
        int amount = 0;
        double PLN = -1;

        try{
            String sql;
            sql = "SELECT PLN FROM java_account_state WHERE user_id = ?";
            this.openConnection(sql);
            this.stmnt.setInt(1, userId);
            ResultSet result = this.stmnt.executeQuery();

            while(result.next()){
                PLN = result.getInt("PLN");
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
    public double addUserDolars(int userId, double valueToAdd){
        /**
         * @return: amount of dollars if fail return -1
         */
        try{
            String sql;
            sql = "UPDATE java_account_state SET dollars = dollars + ? WHERE user_id = ?";
            this.openConnection(sql);
            this.conn.setAutoCommit(true);
            this.stmnt.setDouble(1, valueToAdd);
            this.stmnt.setInt(2, userId);
            this.stmnt.execute();

            this.closeConnection();
        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println(e.getMessage());
            return -1;
        }
        return this.getUserDollars(userId);
    }
    public double payOutUserDolars(int userId, double valueToAdd){
        /**
         * @return: amount of dollars if fail return -1
         */
        try{
            String sql;
            sql = "UPDATE java_account_state SET dollars = dollars - ? WHERE user_id = ?";
            this.openConnection(sql);
            this.conn.setAutoCommit(true);
            this.stmnt.setDouble(1, valueToAdd);
            this.stmnt.setInt(2, userId);
            this.stmnt.execute();

            this.closeConnection();
        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println(e.getMessage());
            return -1;
        }
        return this.getUserDollars(userId);
    }

    public double[] userUpdateAccountState(int userId, double amountBitcoins, double amountDollars){
        /**
         * userId (int): id user, to execut in database
         * amountBitcoins - new value of bitcoins
         * amountDollars - new value of dollars
         * @return: (double[2]) {amount of bitcoins, amount of dollars} if fail {-1, -1}
         */
        //tablicy double[2]
        int amount = 0;
        double ret[] = {-1, -1};

        ret = this.getUserBitcoinsAndDolars(userId);

        try{
            String sql;
            sql = "UPDATE java_account_state SET bitcoins = ?, dollars = ? WHERE user_id = ?";
            this.openConnection(sql);
            this.conn.setAutoCommit(false);
            this.stmnt.setDouble(1, amountBitcoins);
            this.stmnt.setDouble(2, amountDollars);
            this.stmnt.setInt(3, userId);
            this.stmnt.execute();

            sql = "INSERT INTO java_transaction_history (user_id, bitcoins, dollars, dollars_old, bitcoins_old) VALUES (?, ?, ?, ?, ?)";
            this.stmnt = this.conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            this.stmnt.setInt(1, userId);
            this.stmnt.setDouble(2, amountBitcoins);
            this.stmnt.setDouble(3, amountDollars);
            this.stmnt.setDouble(4, ret[1]);
            this.stmnt.setDouble(5, ret[0]);
            this.stmnt.execute();

            this.conn.commit();

            this.closeConnection();
        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println(e.getMessage());
            amount = 0;
        }

        ret = this.getUserBitcoinsAndDolars(userId);
        if(amount == 1)
            return ret;
        else {
            ret[0] = -1;
            ret[1] = -1;
            return ret;
        }
    }
}
