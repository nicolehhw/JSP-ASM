package ict.bean;

import java.io.Serializable;

public class Cart implements Serializable {

    private int productId;
    private String bookingType, name, location, bookingDate, timeSlot;
    private Double hourlyRate;

    public Cart() {
    }

    public Cart(int productId, String bookingType, String name, String location, String bookingDate, String timeSlot, Double hourlyRate) {
        this.productId = productId;
        this.bookingType = bookingType;
        this.name = name;
        this.location = location;
        this.bookingDate = bookingDate;
        this.timeSlot = timeSlot;
        this.hourlyRate = hourlyRate;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getBookingType() {
        return bookingType;
    }

    public void setBookingType(String bookingType) {
        this.bookingType = bookingType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(Double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    @Override
    public String toString() {
        return "Cart{" + "productId=" + productId + ", bookingType=" + bookingType + ", name=" + name + ", hourlyRate=" + hourlyRate + ", bookingDate=" + bookingDate + ", timeSlot=" + timeSlot + '}';
    }

}
