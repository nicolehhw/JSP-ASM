package ict.servlet;

import ict.bean.Booking;
import ict.bean.Report;
import ict.db.ReportDB;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ReportController", urlPatterns = {"/report"})
public class ReportController extends HttpServlet {

    private ReportDB reportDb;

    @Override
    public void init() {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        reportDb = new ReportDB(dbUrl, dbUser, dbPassword);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "getReport":
                getReport(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED);
        }
    }

    private void getReport(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("productId"));
        String year = request.getParameter("year");
        ArrayList<Booking> bookingRecords = reportDb.getBookingsById(productId);
        HashMap<Integer, Double> monthlyIncomes = reportDb.getMonthlyIncomes(productId,year);
        int totalBookingDays = reportDb.getTotalBookingDays(productId,year);
        double yearlyIncome = reportDb.getYearlyIncome(productId,year);
        double bookingRate = reportDb.getBookingRate(productId,year);

        Report report = new Report(bookingRecords, monthlyIncomes, totalBookingDays, yearlyIncome, bookingRate);
        request.setAttribute("report", report);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/report.jsp");
        rd.forward(request, response);
    }
}
