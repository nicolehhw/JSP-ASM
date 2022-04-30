package ict.servlet;

import ict.bean.Booking;
import ict.bean.Cart;
import ict.bean.User;
import ict.db.BookingDB;
import ict.db.BookingReminderDB;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "BookingController", urlPatterns = {"/booking"})
public class BookingController extends HttpServlet {

    private BookingDB bookingDb;
    private BookingReminderDB bookingReminderDb;

    @Override
    public void init() {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        bookingDb = new BookingDB(dbUrl, dbUser, dbPassword);
        bookingReminderDb = new BookingReminderDB(dbUrl, dbUser, dbPassword);
        bookingDb.createBookingTable();
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
            case "getAllBookings":
                getAllBookings(request, response);
                break;
            case "getCustomerBookings":
                getCustomerBookings(request, response);
                break;
            case "getPersonalTrainerBookings":
                getPersonalTrainerBookings(request, response);
                break;
            case "getBookingById":
                getBookingById(request, response);
                break;
            case "addBooking":
                addBooking(request, response);
                break;
            case "editBooking":
                editBooking(request, response);
                break;
            case "customerEditBooking":
                customerEditBooking(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED);
        }
    }

    private void getAllBookings(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Booking> bookings = bookingDb.getAllBookings();
        request.setAttribute("bookings", bookings);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/bookings.jsp");
        rd.forward(request, response);
    }

    private void getCustomerBookings(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        ArrayList<Booking> bookings = bookingDb.getCustomerBookings(id);
        request.setAttribute("bookings", bookings);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/bookingRecords.jsp");
        rd.forward(request, response);
    }

    private void getPersonalTrainerBookings(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        ArrayList<Booking> bookings = bookingDb.getPersonalTrainerBookings(id);
        request.setAttribute("bookings", bookings);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/bookings.jsp");
        rd.forward(request, response);
    }

    private void getBookingById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Booking booking = bookingDb.getBookingById(id);
        request.setAttribute("booking", booking);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/editBookingForm.jsp");
        rd.forward(request, response);
    }

    private void addBooking(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        if (session.getAttribute("cart") != null && session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            ArrayList<Cart> cart = (ArrayList<Cart>) session.getAttribute("cart");

            for (Cart cartItem : cart) {
                int custId = user.getId();
                int productId = cartItem.getProductId();
                String bookingType = cartItem.getBookingType();
                String custName = user.getLname() + " " + user.getFname();
                String custEmail = user.getEmail();
                String custTel = user.getTel();
                String productName = cartItem.getName();
                String productLoc = cartItem.getLocation();
                String bookingDate = cartItem.getBookingDate();
                String timeSlot = cartItem.getTimeSlot();
                Double price = cartItem.getHourlyRate();

                bookingDb.addBooking(custId, productId, bookingType, custName, custEmail, custTel, productName,
                        productLoc, bookingDate, timeSlot, price);

                if (bookingType.equals("Personal Trainer")) {
                    String message = "New Booking Request <br> (Booking Date: " + bookingDate + ", Time Slot: " + timeSlot + ")";
                    bookingReminderDb.addBookingReminder(productId, message);
                }
            }

            session.removeAttribute("cart");
            response.sendRedirect("./cart?action=getCart&success=true");
        }
    }

    private void editBooking(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dispatch = request.getParameter("dispatch");
        int id = Integer.parseInt(request.getParameter("id"));
        int custId = Integer.parseInt(request.getParameter("custId"));
        String custName = request.getParameter("custName");
        String custEmail = request.getParameter("custEmail");
        String custTel = request.getParameter("custTel");
        String status = request.getParameter("status");
        String bookingDate = request.getParameter("bookingDate");
        String timeSlot = request.getParameter("timeSlot");
        Double price = Double.parseDouble(request.getParameter("price"));
        Booking booking = new Booking(id, 0, 0, null, custName, custEmail, custTel,
                null, null, bookingDate, timeSlot, status, price, null);
        bookingDb.editBooking(booking);

        String message = "(ID: " + id + ") Your Booking has been updated";
        bookingReminderDb.addBookingReminder(custId, message);
        response.sendRedirect(dispatch);
    }

    private void customerEditBooking(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int custId = Integer.parseInt(request.getParameter("custId"));
        String custName = request.getParameter("custName");
        String custEmail = request.getParameter("custEmail");
        String custTel = request.getParameter("custTel");
        String status = request.getParameter("status");
        String bookingDate = request.getParameter("bookingDate");
        String timeSlot = request.getParameter("timeSlot");

        if (status.equals("Waiting for confirmation")) {
            Booking booking = new Booking(id, 0, 0, null, custName, custEmail, custTel,
                    null, null, bookingDate, timeSlot, null, 0, null);
            bookingDb.customerEditBooking(booking);
        }
        response.sendRedirect("./booking?action=getCustomerBookings&id=" + custId);
    }
}
