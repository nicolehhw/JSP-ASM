package ict.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Report implements Serializable {

    private ArrayList<Booking> bookings;
    private HashMap<Integer, Double> monthlyIncomes;
    private int totalBookingDays;
    private Double yearlyIncome, bookingRate;

    public Report() {
    }

    public Report(ArrayList<Booking> bookings, HashMap<Integer, Double> monthlyIncomes,int totalBookingDays, Double yearlyIncome, Double bookingRate) {
        this.bookings = bookings;
        this.monthlyIncomes = monthlyIncomes;
        this.yearlyIncome = yearlyIncome;
        this.totalBookingDays = totalBookingDays;
        this.bookingRate = bookingRate;
    }

    public ArrayList<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(ArrayList<Booking> bookings) {
        this.bookings = bookings;
    }

    public HashMap<Integer, Double> getMonthlyIncomes() {
        return monthlyIncomes;
    }

    public void setMonthlyIncomes(HashMap<Integer, Double> monthlyIncomes) {
        this.monthlyIncomes = monthlyIncomes;
    }

    public Double getYearlyIncome() {
        return yearlyIncome;
    }

    public void setYearlyIncome(Double yearlyIncome) {
        this.yearlyIncome = yearlyIncome;
    }

    public int getTotalBookingDays() {
        return totalBookingDays;
    }

    public void setTotalBookingDays(int totalBookingDays) {
        this.totalBookingDays = totalBookingDays;
    }

    public Double getBookingRate() {
        return bookingRate;
    }

    public void setBookingRate(Double bookingRate) {
        this.bookingRate = bookingRate;
    }

}
