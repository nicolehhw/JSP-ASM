package ict.servlet;

import ict.bean.BookingReminder;
import ict.db.BookingReminderDB;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "BookingReminderController", urlPatterns = {"/bookingReminder"})
public class BookingReminderController extends HttpServlet {

    BookingReminderDB bookingReminderDb;

    @Override
    public void init() {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        bookingReminderDb = new BookingReminderDB(dbUrl, dbUser, dbPassword);
        bookingReminderDb.createBookingReminderTable();
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
            case "getAllBookingReminders":
                getAllBookingReminders(request, response);
                break;
            case "deleteBookingReminder":
                deleteBookingReminder(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED);
        }
    }

    private void getAllBookingReminders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        ArrayList<BookingReminder> bookingReminders = bookingReminderDb.getAllBookingReminders(id);
        request.setAttribute("bookingReminders", bookingReminders);
        bookingReminderDb.editBookingReminder(id);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/notification.jsp");
        rd.forward(request, response);
    }

    private void deleteBookingReminder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int userId = Integer.parseInt(request.getParameter("userId"));
        bookingReminderDb.deleteBookingReminder(id);
        response.sendRedirect("./bookingReminder?action=getAllBookingReminders&id=" + userId);
    }
}
