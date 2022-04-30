package ict.bean;

import java.util.Date;

public class Booking {

    private int id, custId, productId;
    private String bookingType, custName, custEmail, custTel, productName, productLoc, bookingDate, timeSlot, status;
    private double price;
    private Date orderDate;

    public Booking() {
    }

    public Booking(int id, int custId, int productId, String bookingType, String custName, String custEmail, String custTel, String productName, String productLoc, String bookingDate, String timeSlot, String status, double price, Date orderDate) {
        this.id = id;
        this.custId = custId;
        this.productId = productId;
        this.bookingType = bookingType;
        this.custName = custName;
        this.custEmail = custEmail;
        this.custTel = custTel;
        this.productName = productName;
        this.productLoc = productLoc;
        this.bookingDate = bookingDate;
        this.timeSlot = timeSlot;
        this.status = status;
        this.price = price;
        this.orderDate = orderDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustId() {
        return custId;
    }

    public void setCustId(int custId) {
        this.custId = custId;
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

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustEmail() {
        return custEmail;
    }

    public void setCustEmail(String custEmail) {
        this.custEmail = custEmail;
    }

    public String getCustTel() {
        return custTel;
    }

    public void setCustTel(String custTel) {
        this.custTel = custTel;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductLoc() {
        return productLoc;
    }

    public void setProductLoc(String productLoc) {
        this.productLoc = productLoc;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    @Override
    public String toString() {
        return "Booking{" + "id=" + id + ", custId=" + custId + ", productId=" + productId + ", bookingType=" + bookingType + ", custName=" + custName + ", custEmail=" + custEmail + ", custTel=" + custTel + ", productName=" + productName + ", productLoc=" + productLoc + ", bookingDate=" + bookingDate + ", timeSlot=" + timeSlot + ", status=" + status + ", price=" + price + ", orderDate=" + orderDate + '}';
    }

}
