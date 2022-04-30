package ict.bean;

import java.io.Serializable;
import java.sql.Blob;

public class PersonalTrainer implements Serializable {

    private int id;
    private String name, trainingType, email, tel, description;
    private Blob img;
    private Double hourlyRate;
    private Boolean isEnabled;

    public PersonalTrainer() {
    }

    public PersonalTrainer(int id, String name, String trainingType, String email, String tel, Blob img, String description, Double hourlyRate, Boolean isEnabled) {
        this.id = id;
        this.name = name;
        this.trainingType = trainingType;
        this.email = email;
        this.tel = tel;
        this.img = img;
        this.description = description;
        this.hourlyRate = hourlyRate;
        this.isEnabled = isEnabled;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTrainingType() {
        return trainingType;
    }

    public void setTrainingType(String trainingType) {
        this.trainingType = trainingType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Blob getImg() {
        return img;
    }

    public void setImg(Blob img) {
        this.img = img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(Double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public Boolean getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(Boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

}
