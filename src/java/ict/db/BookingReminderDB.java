package ict.db;

import ict.bean.BookingReminder;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class BookingReminderDB {

    private String dbUrl = "";
    private String dbUser = "";
    private String dbPassword = "";

    public BookingReminderDB(String dbUrl, String dbUser, String dbPassword) {
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }

    public Connection getConnection() throws SQLException, IOException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void createBookingReminderTable() {
        Connection cnnct;
        Statement stmnt = null;

        try {
            cnnct = getConnection();
            stmnt = cnnct.createStatement();
            String sql = "create table if not exists bookingReminder ("
                    + "id int not null auto_increment,"
                    + "receiverId int not null,"
                    + "message varchar(255) not null,"
                    + "isRead boolean default 0,"
                    + "date datetime default current_timestamp,"
                    + "primary key (id)"
                    + ")";
            stmnt.execute(sql);
            stmnt.close();
            cnnct.close();
        } catch (SQLException e) {
            while (e != null) {
                e.printStackTrace();
                e = e.getNextException();
            }
        } catch (IOException e) {
            while (e != null) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<BookingReminder> getAllBookingReminders(int receiverId) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList<BookingReminder> bookingReminders = new ArrayList();
        try {
            cnnct = getConnection();
            String preQueryStatment = "select * from bookingReminder where receiverId = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatment);
            pStmnt.setInt(1, receiverId);
            ResultSet rs = pStmnt.executeQuery();
            while (rs.next()) {
                BookingReminder bookingReminder = new BookingReminder();
                bookingReminder.setId(rs.getInt(1));
                bookingReminder.setUserId(rs.getInt(2));
                bookingReminder.setMessage(rs.getString(3));
                bookingReminder.setIsRead(rs.getBoolean(4));
                bookingReminder.setDate(rs.getTimestamp(5));
                bookingReminders.add(bookingReminder);
            }
            pStmnt.close();
            cnnct.close();
        } catch (SQLException e) {
            while (e != null) {
                e.printStackTrace();
                e = e.getNextException();
            }
        } catch (IOException e) {
            while (e != null) {
                e.printStackTrace();
            }
        }
        return bookingReminders;
    }

    public boolean addBookingReminder(int receiverId, String message) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;

        try {
            cnnct = getConnection();
            String preQueryStatement = "insert into bookingReminder (receiverId, message) values (?,?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, receiverId);
            pStmnt.setString(2, message);
            int rowCount = pStmnt.executeUpdate();
            if (rowCount >= 1) {
                isSuccess = true;
            }
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return isSuccess;
    }

    public boolean editBookingReminder(int receiverId) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            String preQueryStatment = "update bookingReminder set isRead = '1' where receiverId=?";
            pStmnt = cnnct.prepareStatement(preQueryStatment);
            pStmnt.setInt(1, receiverId);
            int rowCount = pStmnt.executeUpdate();
            if (rowCount >= 1) {
                isSuccess = true;
            }
            pStmnt.close();
            cnnct.close();
        } catch (SQLException e) {
            while (e != null) {
                e.printStackTrace();
                e = e.getNextException();
            }
        } catch (IOException e) {
            while (e != null) {
                e.printStackTrace();
            }
        }
        return isSuccess;
    }

    public boolean deleteBookingReminder(int id) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            String preQueryStatement = "delete from bookingReminder where id=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, id);
            int rowCount = pStmnt.executeUpdate();
            if (rowCount >= 1) {
                isSuccess = true;
            }
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return isSuccess;
    }
}
