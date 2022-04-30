package ict.db;

import ict.bean.PersonalTrainer;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;

public class PersonalTrainerDB {

    private String dburl = "";
    private String dbUser = "";
    private String dbPassword = "";

    public PersonalTrainerDB(String dburl, String dbUser, String dbPassword) {
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

    public void createPersonalTrainerTable() {
        Connection cnnct;
        Statement stmnt = null;

        try {
            cnnct = getConnection();
            stmnt = cnnct.createStatement();
            String sql = "create table if not exists personalTrainer ("
                    + "id int not null,"
                    + "name varchar(30),"
                    + "trainingType varchar(30),"
                    + "email varchar(100),"
                    + "tel varchar(15),"
                    + "img longblob,"
                    + "description varchar(255),"
                    + "hourlyRate double not null,"
                    + "isEnabled boolean not null,"
                    + "constraint fk_trainer foreign key (id) references user(id),"
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

    public ArrayList<PersonalTrainer> getAllPersonalTrainers() {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList<PersonalTrainer> personalTrainers = new ArrayList();
        try {
            cnnct = getConnection();
            String preQueryStatment = "select personalTrainer.id, user.fname, user.lname, "
                    + "personalTrainer.trainingType, user.email, user.tel, personalTrainer.img, personalTrainer.description, "
                    + "personalTrainer.hourlyRate, personalTrainer.isEnabled "
                    + "from personalTrainer "
                    + "inner join user "
                    + "on personalTrainer.id = user.id";

            pStmnt = cnnct.prepareStatement(preQueryStatment);
            ResultSet rs = pStmnt.executeQuery();
            while (rs.next()) {
                PersonalTrainer personalTrainer = new PersonalTrainer();
                personalTrainer.setId(rs.getInt(1));
                personalTrainer.setName(rs.getString(2) + " " + rs.getString(3));
                personalTrainer.setTrainingType(rs.getString(4));
                personalTrainer.setEmail(rs.getString(5));
                personalTrainer.setTel(rs.getString(6));
                personalTrainer.setImg(rs.getBlob(7));
                personalTrainer.setDescription(rs.getString(8));
                personalTrainer.setHourlyRate(rs.getDouble(9));
                personalTrainer.setIsEnabled(rs.getBoolean(10));
                personalTrainers.add(personalTrainer);
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
        return personalTrainers;
    }

    public ArrayList<PersonalTrainer> getPersonalTrainerByEnabled() {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList<PersonalTrainer> personalTrainers = new ArrayList();
        try {
            cnnct = getConnection();
            String preQueryStatment = "select personalTrainer.id, user.fname, user.lname, "
                    + "personalTrainer.trainingType, user.email, user.tel, personalTrainer.img, personalTrainer.description, "
                    + "personalTrainer.hourlyRate, personalTrainer.isEnabled "
                    + "from personalTrainer "
                    + "inner join user "
                    + "on personalTrainer.id = user.id "
                    + "where isEnabled = '1' "
                    + "order by hourlyRate asc";

            pStmnt = cnnct.prepareStatement(preQueryStatment);
            ResultSet rs = pStmnt.executeQuery();
            while (rs.next()) {
                PersonalTrainer personalTrainer = new PersonalTrainer();
                personalTrainer.setId(rs.getInt(1));
                personalTrainer.setName(rs.getString(2) + " " + rs.getString(3));
                personalTrainer.setTrainingType(rs.getString(4));
                personalTrainer.setEmail(rs.getString(5));
                personalTrainer.setTel(rs.getString(6));
                personalTrainer.setImg(rs.getBlob(7));
                personalTrainer.setDescription(rs.getString(8));
                personalTrainer.setHourlyRate(rs.getDouble(9));
                personalTrainer.setIsEnabled(rs.getBoolean(10));
                personalTrainers.add(personalTrainer);
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
        return personalTrainers;
    }

    public PersonalTrainer getPersonalTrainerById(int id) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        PersonalTrainer personalTrainer = null;
        try {
            cnnct = getConnection();
            String preQueryStatment = "select personalTrainer.id, user.fname, user.lname, "
                    + "personalTrainer.trainingType, user.email, user.tel, personalTrainer.img, personalTrainer.description, "
                    + "personalTrainer.hourlyRate, personalTrainer.isEnabled "
                    + "from personalTrainer "
                    + "inner join user "
                    + "on personalTrainer.id = user.id "
                    + "where personalTrainer.id = ? ";

            pStmnt = cnnct.prepareStatement(preQueryStatment);
            pStmnt.setInt(1, id);
            ResultSet rs = pStmnt.executeQuery();
            if (rs.next()) {
                personalTrainer = new PersonalTrainer();
                personalTrainer.setId(rs.getInt(1));
                personalTrainer.setName(rs.getString(2) + " " + rs.getString(3));
                personalTrainer.setTrainingType(rs.getString(4));
                personalTrainer.setEmail(rs.getString(5));
                personalTrainer.setTel(rs.getString(6));
                personalTrainer.setImg(rs.getBlob(7));
                personalTrainer.setDescription(rs.getString(8));
                personalTrainer.setHourlyRate(rs.getDouble(9));
                personalTrainer.setIsEnabled(rs.getBoolean(10));
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
        return personalTrainer;
    }

    public ArrayList<PersonalTrainer> searchPersonalTrainers(String keyword) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList<PersonalTrainer> personalTrainers = new ArrayList();
        try {
            cnnct = getConnection();
            String preQueryStatment = "select personalTrainer.id, user.fname, user.lname, "
                    + "personalTrainer.trainingType, user.email, user.tel, personalTrainer.img, personalTrainer.description, "
                    + "personalTrainer.hourlyRate, personalTrainer.isEnabled "
                    + "from personalTrainer "
                    + "inner join user "
                    + "on personalTrainer.id = user.id "
                    + "where name like ? or trainingtype like ? or description like ? ";
            pStmnt = cnnct.prepareStatement(preQueryStatment);
            pStmnt.setString(1, "%" + keyword + "%");
            pStmnt.setString(2, "%" + keyword + "%");
            pStmnt.setString(3, "%" + keyword + "%");
            ResultSet rs = pStmnt.executeQuery();
            if (rs.next()) {
                PersonalTrainer personalTrainer = new PersonalTrainer();
                personalTrainer.setId(rs.getInt(1));
                personalTrainer.setName(rs.getString(2) + " " + rs.getString(3));
                personalTrainer.setTrainingType(rs.getString(4));
                personalTrainer.setEmail(rs.getString(5));
                personalTrainer.setTel(rs.getString(6));
                personalTrainer.setImg(rs.getBlob(7));
                personalTrainer.setDescription(rs.getString(8));
                personalTrainer.setHourlyRate(rs.getDouble(9));
                personalTrainer.setIsEnabled(rs.getBoolean(10));
                personalTrainers.add(personalTrainer);
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
        return personalTrainers;
    }

    public ArrayList<PersonalTrainer> sortPersonalTrainersByPrice(String order) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList<PersonalTrainer> personalTrainers = new ArrayList();
        String preQueryStatment = "select personalTrainer.id, user.fname, user.lname, "
                + "personalTrainer.trainingType, user.email, user.tel, personalTrainer.img, personalTrainer.description, "
                + "personalTrainer.hourlyRate, personalTrainer.isEnabled "
                + "from personalTrainer "
                + "inner join user "
                + "on personalTrainer.id = user.id ";
        try {
            cnnct = getConnection();
            if (order.equals("asc")) {
                preQueryStatment += "order by personalTrainer.hourlyRate asc";
            } else {
                preQueryStatment += "order by personalTrainer.hourlyRate desc";
            }
            pStmnt = cnnct.prepareStatement(preQueryStatment);
            ResultSet rs = pStmnt.executeQuery();
            while (rs.next()) {
                PersonalTrainer personalTrainer = new PersonalTrainer();
                personalTrainer.setId(rs.getInt(1));
                personalTrainer.setName(rs.getString(2) + " " + rs.getString(3));
                personalTrainer.setTrainingType(rs.getString(4));
                personalTrainer.setEmail(rs.getString(5));
                personalTrainer.setTel(rs.getString(6));
                personalTrainer.setImg(rs.getBlob(7));
                personalTrainer.setDescription(rs.getString(8));
                personalTrainer.setHourlyRate(rs.getDouble(9));
                personalTrainer.setIsEnabled(rs.getBoolean(10));
                personalTrainers.add(personalTrainer);
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
        return personalTrainers;
    }

    public boolean addPersonalTrainer(int id, String name, String trainingType, String email, String tel,
            InputStream img, String description, double hourlyRate, boolean isEnabled) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;

        try {
            cnnct = getConnection();
            String preQueryStatement = "insert into personalTrainer "
                    + "(id, name, trainingType, email, tel, img, description, hourlyRate, isEnabled) "
                    + "values (?,?,?,?,?,?,?,?,?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, id);
            pStmnt.setString(2, name);
            pStmnt.setString(3, trainingType);
            pStmnt.setString(4, email);
            pStmnt.setString(5, tel);
            pStmnt.setBlob(6, img);
            pStmnt.setString(7, description);
            pStmnt.setDouble(8, hourlyRate);
            pStmnt.setBoolean(9, isEnabled);
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

    public boolean editPersonalTrainer(PersonalTrainer personalTrainer, InputStream img) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        String preQueryStatment = null;

        try {
            cnnct = getConnection();
            if (img != null) {
                preQueryStatment = "update personalTrainer set trainingType=?, "
                        + "description=?, hourlyRate=?, isEnabled=?, img=? where id=?";
            } else {
                preQueryStatment = "update personalTrainer set trainingType=?, "
                        + "description=?, hourlyRate=?, isEnabled=? where id=?";
            }
            pStmnt = cnnct.prepareStatement(preQueryStatment);
            pStmnt.setString(1, personalTrainer.getTrainingType());
            pStmnt.setString(2, personalTrainer.getDescription());
            pStmnt.setDouble(3, personalTrainer.getHourlyRate());
            pStmnt.setBoolean(4, personalTrainer.getIsEnabled());
            if (img != null) {
                pStmnt.setBlob(5, img);
                pStmnt.setInt(6, personalTrainer.getId());
            } else {
                pStmnt.setInt(5, personalTrainer.getId());
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

    public boolean deletePersonalTrainer(int id) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            String preQueryStatement = "delete from personalTrainer where id=?";
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
