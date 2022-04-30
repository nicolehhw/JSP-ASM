package ict.bean;

import java.io.Serializable;
import java.sql.Blob;

public class GymCenter implements Serializable {

    private int id;
    private String name, tel, location,  description;
    private Blob img;
    private Double hourlyRate;
    private Boolean isEnabled;

    public GymCenter() {
    }

    public GymCenter(int id, String name, String tel, String location, Blob img, String description, Double hourlyRate, Boolean isEnabled) {
        this.id = id;
        this.name = name;
        this.tel = tel;
        this.location = location;
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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    @Override
    public String toString() {
        return "GymCenter{" + "id=" + id + ", name=" + name + ", tel=" + tel + ", location=" + location + ", description=" + description + ", hourlyRate=" + hourlyRate + ", isEnabled=" + isEnabled + '}';
    }
}
