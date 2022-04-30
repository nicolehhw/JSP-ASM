package ict.bean;

import java.io.Serializable;

public class User implements Serializable {

    private int id;
    private String role, fname, lname, email, password, tel, gender;

    public User() {
    }

    public User(int id, String role, String fname, String lname, String email, String password, String tel, String gender) {
        this.id = id;
        this.role = role;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.password = password;
        this.tel = tel;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", role=" + role + ", fname=" + fname + ", lname=" + lname + ", email=" + email + ", password=" + password + ", tel=" + tel + ", gender=" + gender + '}';
    }

    
}
