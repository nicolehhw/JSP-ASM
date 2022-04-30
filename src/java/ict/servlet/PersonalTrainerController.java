package ict.servlet;

import ict.bean.PersonalTrainer;
import ict.bean.User;
import ict.db.BookingDB;
import ict.db.PersonalTrainerDB;
import ict.db.UserDB;
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

@WebServlet(name = "PersonalTrainerController", urlPatterns = {"/personalTrainer"})
@MultipartConfig(maxFileSize = 5191680)
public class PersonalTrainerController extends HttpServlet {

    private PersonalTrainerDB personalTrainerDb;
    private UserDB userDb;
    private BookingDB bookingDb;

    @Override
    public void init() {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        personalTrainerDb = new PersonalTrainerDB(dbUrl, dbUser, dbPassword);
        userDb = new UserDB(dbUrl, dbUser, dbPassword);
        bookingDb = new BookingDB(dbUrl, dbUser, dbPassword);
        personalTrainerDb.createPersonalTrainerTable();
        userDb.createUserTable();
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
            case "getAllPersonalTrainers":
                getAllPersonalTrainers(request, response);
                break;
            case "getPersonalTrainerByEnabled":
                getPersonalTrainerByEnabled(request, response);
                break;
            case "getPersonalTrainerById":
                getPersonalTrainerById(request, response);
                break;
            case "getPersonalTrainerBookingForm":
                getPersonalTrainerBookingForm(request, response);
                break;
            case "searchPersonalTrainers":
                searchPersonalTrainers(request, response);
                break;
            case "sortPersonalTrainerByPrice":
                sortPersonalTrainerByPrice(request, response);
                break;
            case "addPersonalTrainer":
                addPersonalTrainer(request, response);
                break;
            case "editPersonalTrainer":
                editPersonalTrainer(request, response);
                break;
            case "deletePersonalTrainer":
                deletePersonalTrainer(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED);
        }
    }

    private void getAllPersonalTrainers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<PersonalTrainer> personalTrainers = personalTrainerDb.getAllPersonalTrainers();
        request.setAttribute("personalTrainers", personalTrainers);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/personalTrainers.jsp");
        rd.forward(request, response);
    }

    private void getPersonalTrainerByEnabled(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<PersonalTrainer> personalTrainers = personalTrainerDb.getPersonalTrainerByEnabled();
        request.setAttribute("personalTrainers", personalTrainers);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/personalTrainerBookingList.jsp");
        rd.forward(request, response);
    }

    private void getPersonalTrainerById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getParameter("dispatch");
        int id = Integer.parseInt(request.getParameter("id"));
        PersonalTrainer personalTrainer = personalTrainerDb.getPersonalTrainerById(id);
        request.setAttribute("personalTrainer", personalTrainer);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/" + url + ".jsp");
        rd.forward(request, response);
    }

    private void getPersonalTrainerBookingForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String bookingDate = request.getParameter("bookingDate");
        PersonalTrainer personalTrainer = personalTrainerDb.getPersonalTrainerById(id);
        ArrayList<String> bookedTimeSlots = bookingDb.getBookedTimeSlots(id, bookingDate, "Personal Trainer");
        request.setAttribute("personalTrainer", personalTrainer);
        request.setAttribute("bookedTimeSlots", bookedTimeSlots);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/bookingPersonalTrainerForm.jsp?bookingDate=" + bookingDate);
        rd.forward(request, response);
    }

    private void searchPersonalTrainers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        ArrayList<PersonalTrainer> personalTrainers = personalTrainerDb.searchPersonalTrainers(keyword);
        request.setAttribute("personalTrainers", personalTrainers);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/personalTrainerBookingList.jsp");
        rd.forward(request, response);
    }

    private void sortPersonalTrainerByPrice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String order = request.getParameter("order");
        ArrayList<PersonalTrainer> personalTrainers = personalTrainerDb.sortPersonalTrainersByPrice(order);
        System.out.println(personalTrainers);
        request.setAttribute("personalTrainers", personalTrainers);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/personalTrainerBookingList.jsp");
        rd.forward(request, response);
    }

    private void addPersonalTrainer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String tel = request.getParameter("tel");
        String gender = request.getParameter("gender");
        String trainingType = request.getParameter("trainingType");
        String description = request.getParameter("description");
        Double hourlyRate = Double.parseDouble(request.getParameter("hourlyRate"));
        Boolean isEnabled = Boolean.parseBoolean(request.getParameter("isEnabled"));

        User isExisting = userDb.getUserByEmail(email);
        if (isExisting != null) {
            request.setAttribute("registered", "Email has been registered");
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/editPersonalTrainerForm.jsp");
            rd.forward(request, response);
            return;
        }

        String image = request.getParameter("image");
        Part part = request.getPart("image");
        if (image == null) {
            boolean isSuccess = userDb.addUser("personalTrainer", fname, lname, email, password, tel, gender);
            User user = userDb.getUserByEmail(email);
            if (isSuccess) {
                InputStream img = part.getInputStream();
                personalTrainerDb.addPersonalTrainer(user.getId(), null, trainingType, null, null, img, description, hourlyRate, isEnabled
                );
                response.sendRedirect("./personalTrainer?action=getAllPersonalTrainers");
            }
        } else {
            response.sendRedirect("./editPersonalTrainerForm.jsp?empty=true");
        }
    }

    private void editPersonalTrainer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dispatch = request.getParameter("dispatch");
        int id = Integer.parseInt(request.getParameter("id"));
        String trainingType = request.getParameter("trainingType");
        String description = request.getParameter("description");
        Double hourlyRate = Double.parseDouble(request.getParameter("hourlyRate"));
        Boolean isEnabled = Boolean.parseBoolean(request.getParameter("isEnabled"));

        InputStream img = null;
        String image = request.getParameter("image");
        Part part = request.getPart("image");
        if (image == null) {
            img = part.getInputStream();
        }

        PersonalTrainer personalTrainer = new PersonalTrainer(id, null, trainingType, null,
                null, null, description, hourlyRate, isEnabled);
        personalTrainerDb.editPersonalTrainer(personalTrainer, img);
        response.sendRedirect(dispatch);
    }

    private void deletePersonalTrainer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        personalTrainerDb.deletePersonalTrainer(id);
        userDb.deleteUser(id);
        bookingDb.deleteBookingByProductId(id);
        response.sendRedirect("./personalTrainer?action=getAllPersonalTrainers");
    }
}
