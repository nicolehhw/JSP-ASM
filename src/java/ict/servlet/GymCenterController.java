package ict.servlet;

import ict.bean.GymCenter;
import ict.db.BookingDB;
import ict.db.GymCenterDB;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet(name = "GymCenterController", urlPatterns = {"/gymCenter"})
@MultipartConfig(maxFileSize = 5191680)
public class GymCenterController extends HttpServlet {

    private GymCenterDB gymCenterDb;
    private BookingDB bookingDb;

    @Override
    public void init() {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        gymCenterDb = new GymCenterDB(dbUrl, dbUser, dbPassword);
        bookingDb = new BookingDB(dbUrl, dbUser, dbPassword);
        gymCenterDb.createGymCenterTable();
        bookingDb.createBookingTable();
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
            case "getAllGymCenters":
                getAllGymCenters(request, response);
                break;
            case "getGymCenterByEnabled":
                getGymCenterByEnabled(request, response);
                break;
            case "getGymCenterById":
                getGymCenterById(request, response);
                break;
            case "getGymCenterBookingForm":
                getGymCenterBookingForm(request, response);
                break;
            case "searchGymCenters":
                searchGymCenters(request, response);
                break;
            case "sortGymCenterByPrice":
                sortGymCenterByPrice(request, response);
                break;
            case "addGymCenter":
                addGymCenter(request, response);
                break;
            case "editGymCenter":
                editGymCenter(request, response);
                break;
            case "deleteGymCenter":
                deleteGymCenter(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED);
        }
    }

    private void getAllGymCenters(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<GymCenter> gymCenters = gymCenterDb.getAllGymCenters();
        request.setAttribute("gymCenters", gymCenters);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/gymCenters.jsp");
        rd.forward(request, response);
    }

    private void getGymCenterByEnabled(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<GymCenter> gymCenters = gymCenterDb.getGymCenterByEnabled();
        request.setAttribute("gymCenters", gymCenters);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/gymCenterBookingList.jsp");
        rd.forward(request, response);
    }

    private void getGymCenterById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        GymCenter gymCenter = gymCenterDb.getGymCenterById(id);
        request.setAttribute("gymCenter", gymCenter);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/editGymCenterForm.jsp");
        rd.forward(request, response);
    }

    private void getGymCenterBookingForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String bookingDate = request.getParameter("bookingDate");
        GymCenter gymCenter = gymCenterDb.getGymCenterById(id);
        ArrayList<String> bookedTimeSlots = bookingDb.getBookedTimeSlots(id, bookingDate, "Gym Center");
        request.setAttribute("gymCenter", gymCenter);
        request.setAttribute("bookedTimeSlots", bookedTimeSlots);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/bookingGymCenterForm.jsp?bookingDate=" + bookingDate);
        rd.forward(request, response);
    }

    private void searchGymCenters(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        ArrayList<GymCenter> gymCenters = gymCenterDb.searchGymCenter(keyword);
        request.setAttribute("gymCenters", gymCenters);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/gymCenterBookingList.jsp");
        rd.forward(request, response);
    }

    private void sortGymCenterByPrice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String order = request.getParameter("order");
        ArrayList<GymCenter> gymCenters = gymCenterDb.sortGymCentersByPrice(order);
        request.setAttribute("gymCenters", gymCenters);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/gymCenterBookingList.jsp");
        rd.forward(request, response);
    }

    private void addGymCenter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String tel = request.getParameter("tel");
        String location = request.getParameter("location");
        String description = request.getParameter("description");
        Double hourlyRate = Double.parseDouble(request.getParameter("hourlyRate"));
        Boolean isEnabled = Boolean.parseBoolean(request.getParameter("isEnabled"));

        Part part = request.getPart("image");
        InputStream img = part.getInputStream();
        gymCenterDb.addGymCenter(name, tel, location, img, description, hourlyRate, isEnabled);
        response.sendRedirect("./gymCenter?action=getAllGymCenters");
    }

    private void editGymCenter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String tel = request.getParameter("tel");
        String location = request.getParameter("location");
        String description = request.getParameter("description");
        Double hourlyRate = Double.parseDouble(request.getParameter("hourlyRate"));
        Boolean isEnabled = Boolean.parseBoolean(request.getParameter("isEnabled"));
        InputStream img = null;
        String image = request.getParameter("image");

        Part part = request.getPart("image");
        if (image == null) {
            img = part.getInputStream();
        }
        GymCenter gymCenter = new GymCenter(id, name, tel, location, null, description, hourlyRate, isEnabled);
        gymCenterDb.editGymCenter(gymCenter, img);
        response.sendRedirect("./gymCenter?action=getAllGymCenters");
    }

    private void deleteGymCenter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        gymCenterDb.deleteGymCenter(id);
        response.sendRedirect("./gymCenter?action=getAllGymCenters");
    }
}
