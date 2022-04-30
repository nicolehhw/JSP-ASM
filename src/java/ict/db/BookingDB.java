package ict.db;

import ict.bean.Booking;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class BookingDB {

    private String dbUrl = "";
    private String dbUser = "";
    private String dbPassword = "";

    public BookingDB(String dbUrl, String dbUser, String dbPassword) {
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

    public void createBookingTable() {
        Connection cnnct;
        Statement stmnt = null;

        try {
            cnnct = getConnection();
            stmnt = cnnct.createStatement();
            String sql = "create table if not exists booking ("
                    + "id int not null auto_increment,"
                    + "custId int not null,"
                    + "productId int not null,"
                    + "bookingType varchar(30) not null,"
                    + "custName varchar(50) not null,"
                    + "custEmail varchar(100) not null,"
                    + "custTel varchar(15) not null,"
                    + "productName varchar(30) not null,"
                    + "productLoc varchar(30),"
                    + "bookingDate varchar(30) not null,"
                    + "timeSlot varchar(15) not null,"
                    + "status varchar(50) default 'Waiting for confirmation',"
                    + "price double not null,"
                    + "orderDate datetime default current_timestamp,"
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

    public ArrayList<Booking> getAllBookings() {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList<Booking> bookings = new ArrayList();
        try {
            cnnct = getConnection();
            String preQueryStatment = "select * from booking";
            pStmnt = cnnct.prepareStatement(preQueryStatment);
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

    public ArrayList<Booking> getCustomerBookings(int id) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList<Booking> bookings = new ArrayList();
        try {
            cnnct = getConnection();
            String preQueryStatment = "select * from booking where custId = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatment);
            pStmnt.setInt(1, id);
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

    public ArrayList<Booking> getPersonalTrainerBookings(int id) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList<Booking> bookings = new ArrayList();
        try {
            cnnct = getConnection();
            String preQueryStatment = "select * from booking where bookingType = ? and productId = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatment);
            pStmnt.setString(1, "Personal Trainer");
            pStmnt.setInt(2, id);
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

    public ArrayList<String> getBookedTimeSlots(int productId, String bookingDate, String bookingType) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        String preQueryStatment = null;
        ArrayList<String> timeSlots = new ArrayList();
        try {
            cnnct = getConnection();
            if (bookingType.equals("Personal Trainer")) {
                preQueryStatment = "select timeSlot from booking "
                        + "where bookingType = ? and productId = ? and bookingDate = ? "
                        + "and status != 'cancel' ";
            } else {
                preQueryStatment = "select timeSlot from booking "
                        + "where bookingType = ? and productId = ? and bookingDate = ? "
                        + "and status != 'cancel' group by timeSlot having count(*) >= 10";
            }
            pStmnt = cnnct.prepareStatement(preQueryStatment);
            pStmnt.setString(1, bookingType);
            pStmnt.setInt(2, productId);
            pStmnt.setString(3, bookingDate);
            ResultSet rs = pStmnt.executeQuery();
            while (rs.next()) {
                timeSlots.add(rs.getString(1));
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
        return timeSlots;
    }

    public Booking getBookingById(int id) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        Booking booking = null;
        try {
            cnnct = getConnection();
            String preQueryStatment = "select * from booking where id = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatment);
            pStmnt.setInt(1, id);
            ResultSet rs = pStmnt.executeQuery();
            if (rs.next()) {
                booking = new Booking();
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
        return booking;
    }

    public boolean addBooking(int custId, int productId, String bookingType, String custName, String custEmail,
            String custTel, String productName, String productLoc, String bookingDate, String timeSlot, Double price) {

        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;

        try {
            cnnct = getConnection();
            String preQueryStatement = "insert into booking (custId, productId, bookingType, custName, custEmail, "
                    + "custTel, productName, productLoc, bookingDate, timeSlot, price) values (?,?,?,?,?,?,?,?,?,?,?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, custId);
            pStmnt.setInt(2, productId);
            pStmnt.setString(3, bookingType);
            pStmnt.setString(4, custName);
            pStmnt.setString(5, custEmail);
            pStmnt.setString(6, custTel);
            pStmnt.setString(7, productName);
            pStmnt.setString(8, productLoc);
            pStmnt.setString(9, bookingDate);
            pStmnt.setString(10, timeSlot);
            pStmnt.setDouble(11, price);
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

    public boolean editBooking(Booking booking) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;

        try {
            cnnct = getConnection();
            String preQueryStatment = "update booking set custName=?, custEmail=?, custTel=?, bookingDate=?"
                    + ", timeSlot=?, status=?, price=? where id=?";
            pStmnt = cnnct.prepareStatement(preQueryStatment);
            pStmnt.setString(1, booking.getCustName());
            pStmnt.setString(2, booking.getCustEmail());
            pStmnt.setString(3, booking.getCustTel());
            pStmnt.setString(4, booking.getBookingDate());
            pStmnt.setString(5, booking.getTimeSlot());
            pStmnt.setString(6, booking.getStatus());
            pStmnt.setDouble(7, booking.getPrice());
            pStmnt.setInt(8, booking.getId());
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

    public boolean customerEditBooking(Booking booking) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;

        try {
            cnnct = getConnection();
            String preQueryStatment = "update booking set custName=?, custEmail=?, custTel=?, bookingDate=?"
                    + ", timeSlot=? where id=?";
            pStmnt = cnnct.prepareStatement(preQueryStatment);
            pStmnt.setString(1, booking.getCustName());
            pStmnt.setString(2, booking.getCustEmail());
            pStmnt.setString(3, booking.getCustTel());
            pStmnt.setString(4, booking.getBookingDate());
            pStmnt.setString(5, booking.getTimeSlot());
            pStmnt.setInt(6, booking.getId());
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

    public boolean deleteBookingByProductId(int id) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            String preQueryStatement = "delete from booking where productId=? and status != 'confirmed'";
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
