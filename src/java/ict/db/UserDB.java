package ict.db;

import ict.bean.User;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class UserDB {

    private String dburl = "";
    private String dbUser = "";
    private String dbPassword = "";

    public UserDB(String dburl, String dbUser, String dbPassword) {
        this.dburl = dburl;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }

    public Connection getConnection() throws SQLException, IOException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(dburl, dbUser, dbPassword);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void createUserTable() {
        Connection cnnct;
        Statement stmnt = null;

        try {
            cnnct = getConnection();
            stmnt = cnnct.createStatement();
            String sql = "create table if not exists user ("
                    + "id int not null auto_increment,"
                    + "fname varchar(30) not null,"
                    + "lname varchar(30) not null,"
                    + "tel varchar(15) not null,"
                    + "gender varchar(10) not null,"
                    + "email varchar(100) unique not null,"
                    + "password varchar(30) not null,"
                    + "role varchar(30) not null,"
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

    public boolean isValidUser(String email, String password) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isValid = false;
        try {
            cnnct = getConnection();
            String preQueryStaement = "select * from user where email = ? and password = ?";
            pStmnt = cnnct.prepareStatement(preQueryStaement);
            pStmnt.setString(1, email);
            pStmnt.setString(2, password);
            ResultSet rs = null;
            rs = pStmnt.executeQuery();
            if (rs.next()) {
                isValid = true;
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
        return isValid;
    }

    public ArrayList<User> getAllUsers() {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList<User> users = new ArrayList();
        try {
            cnnct = getConnection();
            String preQueryStatment = "select * from user";
            pStmnt = cnnct.prepareStatement(preQueryStatment);
            ResultSet rs = pStmnt.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt(1));
                user.setFname(rs.getString(2));
                user.setLname(rs.getString(3));
                user.setTel(rs.getString(4));
                user.setGender(rs.getString(5));
                user.setEmail(rs.getString(6));
                user.setPassword(rs.getString(7));
                user.setRole(rs.getString(8));
                users.add(user);
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
        return users;
    }

    public User getUserById(int id) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        User user = null;
        try {
            cnnct = getConnection();
            String preQueryStatment = "select * from user where id = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatment);
            pStmnt.setInt(1, id);
            ResultSet rs = pStmnt.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt(1));
                user.setFname(rs.getString(2));
                user.setLname(rs.getString(3));
                user.setTel(rs.getString(4));
                user.setGender(rs.getString(5));
                user.setEmail(rs.getString(6));
                user.setPassword(rs.getString(7));
                user.setRole(rs.getString(8));
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
        return user;
    }

    public User getUserByEmail(String email) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        User user = null;
        try {
            cnnct = getConnection();
            String preQueryStatment = "select * from user where email = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatment);
            pStmnt.setString(1, email);
            ResultSet rs = pStmnt.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt(1));
                user.setFname(rs.getString(2));
                user.setLname(rs.getString(3));
                user.setTel(rs.getString(4));
                user.setGender(rs.getString(5));
                user.setEmail(rs.getString(6));
                user.setPassword(rs.getString(7));
                user.setRole(rs.getString(8));
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
        return user;
    }

    public boolean addUser(String role, String fname, String lname, String email, String password, String tel, String gender) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;

        try {
            cnnct = getConnection();
            String preQueryStatement = "insert into user (role, fname, lname, email, password, tel, gender) "
                    + "values (?,?,?,?,?,?,?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, role);
            pStmnt.setString(2, fname);
            pStmnt.setString(3, lname);
            pStmnt.setString(4, email);
            pStmnt.setString(5, password);
            pStmnt.setString(6, tel);
            pStmnt.setString(7, gender);
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

    public boolean editUser(User user) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            String preQueryStatment = "update user set role=?, fname=?, lname=?, email=?"
                    + ", password=?, tel=?, gender=? where id=?";
            pStmnt = cnnct.prepareStatement(preQueryStatment);
            pStmnt.setString(1, user.getRole());
            pStmnt.setString(2, user.getFname());
            pStmnt.setString(3, user.getLname());
            pStmnt.setString(4, user.getEmail());
            pStmnt.setString(5, user.getPassword());
            pStmnt.setString(6, user.getTel());
            pStmnt.setString(7, user.getGender());
            pStmnt.setInt(8, user.getId());
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

    public boolean deleteUser(int id) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            String preQueryStatement = "delete from user where id=?";
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
