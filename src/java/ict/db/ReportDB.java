package ict.db;

import ict.bean.Booking;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ReportDB {

    private String dbUrl = "";
    private String dbUser = "";
    private String dbPassword = "";

    public ReportDB(String dbUrl, String dbUser, String dbPassword) {
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

    public ArrayList<Booking> getBookingsById(int productId) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList<Booking> bookings = new ArrayList();
        try {
            cnnct = getConnection();
            String preQueryStatment = "select * from booking where productId = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatment);
            pStmnt.setInt(1, productId);
            ResultSet rs = pStmnt.executeQuery();
            while (rs.next()) {
                Booking booking = new Booking();
                booking.setId(rs.getInt(1));
                booking.setCustId(rs.getInt(2));
                booking.setProductId(rs.getInt(3));
                booking.setBookingType(rs.getString(4));
                booking.setCustName(rs.getString(5));
                booking.setCustEmail(rs.getString(6));
                booking.setCustTel(rs.getString(7));
                booking.setProductName(rs.getString(8));
                booking.setProductLoc(rs.getString(9));
                booking.setBookingDate(rs.getString(10));
                booking.setTimeSlot(rs.getString(11));
                booking.setStatus(rs.getString(12));
                booking.setPrice(rs.getDouble(13));
                booking.setOrderDate(rs.getDate(14));
                bookings.add(booking);
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
        return bookings;
    }

    public HashMap<Integer, Double> getMonthlyIncomes(int productId, String year) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        HashMap<Integer, Double> monthlyIncomes = new HashMap<>();
        try {
            cnnct = getConnection();
            String preQueryStatment = "select month(orderDate) as month, sum(price) as income "
                    + "from booking "
                    + "where productId = ? and year(orderDate) = ? and status = 'confirmed'  "
                    + "group by month(orderDate) "
                    + "order by month(orderDate) ";
            pStmnt = cnnct.prepareStatement(preQueryStatment);
            pStmnt.setInt(1, productId);
            pStmnt.setString(2, year);
            ResultSet rs = pStmnt.executeQuery();
            if (rs.next()) {
                monthlyIncomes.put(rs.getInt("month"), rs.getDouble("income"));
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
        return monthlyIncomes;
    }

    public double getYearlyIncome(int productId, String year) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        double totalIncome = 0;
        try {
            cnnct = getConnection();
            String preQueryStatment = "select sum(price) as totalIncome from booking "
                    + "where productId = ? and year(orderDate) = ? and status = 'confirmed'";
            pStmnt = cnnct.prepareStatement(preQueryStatment);
            pStmnt.setInt(1, productId);
            pStmnt.setString(2, year);
            ResultSet rs = pStmnt.executeQuery();
            if (rs.next()) {
                totalIncome = rs.getDouble("totalIncome");
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
        return totalIncome;
    }

    public int getTotalBookingDays(int productId, String year) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        int totalBookingDays = 0;
        try {
            cnnct = getConnection();
            String preQueryStatment = "select count(*) as totalBookingDays from( "
                    + "select month(orderDate), day(orderDate) "
                    + "from booking "
                    + "where productId = ? and year(orderDate) = ? and status = 'confirmed' "
                    + "group by month(orderDate), day(orderDate) "
                    + ") as p";
            pStmnt = cnnct.prepareStatement(preQueryStatment);
            pStmnt.setInt(1, productId);
            pStmnt.setString(2, year);
            ResultSet rs = pStmnt.executeQuery();
            if (rs.next()) {
                totalBookingDays = rs.getInt("totalBookingDays");
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
        return totalBookingDays;
    }

    public double getBookingRate(int productId, String year) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        double bookingRate = 0;
        try {
            cnnct = getConnection();
            String preQueryStatment = "select coalesce((count(*) / 365) * 100, 0) as bookingRate from( "
                    + "select month(orderDate), day(orderDate) "
                    + "from booking "
                    + "where productId = ? and year(orderDate) = ? and status = 'confirmed' "
                    + "group by month(orderDate), day(orderDate) "
                    + ") as p";
            pStmnt = cnnct.prepareStatement(preQueryStatment);
            pStmnt.setInt(1, productId);
            pStmnt.setString(2, year);
            ResultSet rs = pStmnt.executeQuery();
            if (rs.next()) {
                bookingRate = rs.getDouble("bookingRate");
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
        return bookingRate;
    }
}
