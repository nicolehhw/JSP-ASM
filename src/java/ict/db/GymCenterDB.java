package ict.db;

import ict.bean.GymCenter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;

public class GymCenterDB {

    private String dburl = "";
    private String dbUser = "";
    private String dbPassword = "";

    public GymCenterDB(String dburl, String dbUser, String dbPassword) {
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

    public void createGymCenterTable() {
        Connection cnnct;
        Statement stmnt = null;

        try {
            cnnct = getConnection();
            stmnt = cnnct.createStatement();
            String sql = "create table if not exists gymCenter ("
                    + "id int not null auto_increment,"
                    + "name varchar(30) not null,"
                    + "tel varchar(15) not null,"
                    + "location varchar(50) not null,"
                    + "img longblob not null,"
                    + "description varchar(255) not null,"
                    + "hourlyRate double not null,"
                    + "isEnabled boolean not null,"
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

    public ArrayList<GymCenter> getAllGymCenters() {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList<GymCenter> gymCenters = new ArrayList();
        try {
            cnnct = getConnection();
            String preQueryStatment = "select * from gymCenter";
            pStmnt = cnnct.prepareStatement(preQueryStatment);
            ResultSet rs = pStmnt.executeQuery();
            while (rs.next()) {
                GymCenter gymCenter = new GymCenter();
                gymCenter.setId(rs.getInt(1));
                gymCenter.setName(rs.getString(2));
                gymCenter.setTel(rs.getString(3));
                gymCenter.setLocation(rs.getString(4));
                gymCenter.setImg(rs.getBlob(5));
                gymCenter.setDescription(rs.getString(6));
                gymCenter.setHourlyRate(rs.getDouble(7));
                gymCenter.setIsEnabled(rs.getBoolean(8));
                gymCenters.add(gymCenter);
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
        return gymCenters;
    }

    public ArrayList<GymCenter> getGymCenterByEnabled() {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList<GymCenter> gymCenters = new ArrayList();
        try {
            cnnct = getConnection();
            String preQueryStatment = "select * from gymCenter where isEnabled = '1' order by hourlyRate asc";
            pStmnt = cnnct.prepareStatement(preQueryStatment);
            ResultSet rs = pStmnt.executeQuery();
            while (rs.next()) {
                GymCenter gymCenter = new GymCenter();
                gymCenter.setId(rs.getInt(1));
                gymCenter.setName(rs.getString(2));
                gymCenter.setTel(rs.getString(3));
                gymCenter.setLocation(rs.getString(4));
                gymCenter.setImg(rs.getBlob(5));
                gymCenter.setDescription(rs.getString(6));
                gymCenter.setHourlyRate(rs.getDouble(7));
                gymCenter.setIsEnabled(rs.getBoolean(8));
                gymCenters.add(gymCenter);
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
        return gymCenters;
    }

    public GymCenter getGymCenterById(int id) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        GymCenter gymCenter = null;
        try {
            cnnct = getConnection();
            String preQueryStatment = "select * from gymCenter where id = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatment);
            pStmnt.setInt(1, id);
            ResultSet rs = pStmnt.executeQuery();
            if (rs.next()) {
                gymCenter = new GymCenter();
                gymCenter.setId(rs.getInt(1));
                gymCenter.setName(rs.getString(2));
                gymCenter.setTel(rs.getString(3));
                gymCenter.setLocation(rs.getString(4));
                gymCenter.setImg(rs.getBlob(5));
                gymCenter.setDescription(rs.getString(6));
                gymCenter.setHourlyRate(rs.getDouble(7));
                gymCenter.setIsEnabled(rs.getBoolean(8));
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
        return gymCenter;
    }

    public ArrayList<GymCenter> searchGymCenter(String keyword) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList<GymCenter> gymCenters = new ArrayList();
        try {
            cnnct = getConnection();
            String preQueryStatment = "select * from gymCenter where name like ? or location like ? "
                    + "or description like ?";
            pStmnt = cnnct.prepareStatement(preQueryStatment);
            pStmnt.setString(1, "%" + keyword + "%");
            pStmnt.setString(2, "%" + keyword + "%");
            pStmnt.setString(3, "%" + keyword + "%");
            ResultSet rs = pStmnt.executeQuery();
            if (rs.next()) {
                GymCenter gymCenter = new GymCenter();
                gymCenter.setId(rs.getInt(1));
                gymCenter.setName(rs.getString(2));
                gymCenter.setTel(rs.getString(3));
                gymCenter.setLocation(rs.getString(4));
                gymCenter.setImg(rs.getBlob(5));
                gymCenter.setDescription(rs.getString(6));
                gymCenter.setHourlyRate(rs.getDouble(7));
                gymCenter.setIsEnabled(rs.getBoolean(8));
                gymCenters.add(gymCenter);
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
        return gymCenters;
    }

    public ArrayList<GymCenter> sortGymCentersByPrice(String order) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        String preQueryStatment = null;
        ArrayList<GymCenter> gymCenters = new ArrayList();
        try {
            cnnct = getConnection();
            if (order.equals("asc")) {
                preQueryStatment = "select * from gymCenter order by hourlyRate asc";
            } else {
                preQueryStatment = "select * from gymCenter order by hourlyRate desc";
            }
            pStmnt = cnnct.prepareStatement(preQueryStatment);
            ResultSet rs = pStmnt.executeQuery();
            while (rs.next()) {
                GymCenter gymCenter = new GymCenter();
                gymCenter.setId(rs.getInt(1));
                gymCenter.setName(rs.getString(2));
                gymCenter.setTel(rs.getString(3));
                gymCenter.setLocation(rs.getString(4));
                gymCenter.setImg(rs.getBlob(5));
                gymCenter.setDescription(rs.getString(6));
                gymCenter.setHourlyRate(rs.getDouble(7));
                gymCenter.setIsEnabled(rs.getBoolean(8));
                gymCenters.add(gymCenter);
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
        return gymCenters;
    }

    public boolean addGymCenter(String name, String tel, String location, InputStream img,
            String description, double hourlyRate, boolean isEnabled) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;

        try {
            cnnct = getConnection();
            String preQueryStatement = "insert into gymCenter "
                    + "(name,tel,location,img,description,hourlyRate,isEnabled) values (?,?,?,?,?,?,?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, name);
            pStmnt.setString(2, tel);
            pStmnt.setString(3, location);
            pStmnt.setBlob(4, img);
            pStmnt.setString(5, description);
            pStmnt.setDouble(6, hourlyRate);
            pStmnt.setBoolean(7, isEnabled);
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

    public boolean editGymCenter(GymCenter gymCenter, InputStream img) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        String preQueryStatment = null;
        try {
            cnnct = getConnection();
            if (img != null) {
                preQueryStatment = "update gymCenter set name=?, tel=?, location=?"
                        + ",description=?, hourlyRate=?, isEnabled=?, img=? where id=?";
            } else {
                preQueryStatment = "update gymCenter set name=?, tel=?, location=?"
                        + ", description=?, hourlyRate=?, isEnabled=? where id=?";
            }
            pStmnt = cnnct.prepareStatement(preQueryStatment);
            pStmnt.setString(1, gymCenter.getName());
            pStmnt.setString(2, gymCenter.getTel());
            pStmnt.setString(3, gymCenter.getLocation());
            pStmnt.setString(4, gymCenter.getDescription());
            pStmnt.setDouble(5, gymCenter.getHourlyRate());
            pStmnt.setBoolean(6, gymCenter.getIsEnabled());
            if (img != null) {
                pStmnt.setBlob(7, img);
                pStmnt.setInt(8, gymCenter.getId());
            } else {
                pStmnt.setInt(7, gymCenter.getId());
            }
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

    public boolean deleteGymCenter(int id) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            String preQueryStatement = "delete from gymCenter where id=?";
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
