package ict.servlet;

import ict.bean.PersonalTrainer;
import ict.bean.User;
import ict.db.BookingDB;
import ict.db.PersonalTrainerDB;
import ict.db.UserDB;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "UserController", urlPatterns = {"/user"})
public class UserController extends HttpServlet {

    private UserDB userDb;
    private PersonalTrainerDB personalTrainerDb;
    private BookingDB bookingDb;

    @Override
    public void init() {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        userDb = new UserDB(dbUrl, dbUser, dbPassword);
        bookingDb = new BookingDB(dbUrl, dbUser, dbPassword);
        personalTrainerDb = new PersonalTrainerDB(dbUrl, dbUser, dbPassword);
        userDb.createUserTable();
        personalTrainerDb.createPersonalTrainerTable();
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
        String role = getUserRole(request);

        if (!isLoggedIn(request) || role.equals("customer")) {
            response.sendRedirect("/home.jsp");
            return;
        }

        switch (action) {
            case "getAllUsers":
                getAllUsers(request, response);
                break;
            case "getUserById":
                getUserById(request, response);
                break;
            case "addUser":
                addUser(request, response);
                break;
            case "editUser":
                editUser(request, response);
                break;
            case "deleteUser":
                deleteUser(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED);
        }
    }

    private boolean isLoggedIn(HttpServletRequest request) {
        boolean result = false;
        HttpSession session = request.getSession();

        if (session.getAttribute("user") != null) {
            result = true;
        }
        return result;
    }

    private String getUserRole(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String role = user.getRole();

        return role;
    }

    private void getAllUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<User> users = userDb.getAllUsers();
        request.setAttribute("users", users);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/users.jsp");
        rd.forward(request, response);
    }

    private void getUserById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User user = userDb.getUserById(id);
        request.setAttribute("user", user);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/editUserForm.jsp");
        rd.forward(request, response);
    }

    private void addUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String role = request.getParameter("role");
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String tel = request.getParameter("tel");
        String gender = request.getParameter("gender");

        User isUserExisting = userDb.getUserByEmail(email);

        if (isUserExisting == null) {
            userDb.addUser(role, fname, lname, email, password, tel, gender);
            User user = userDb.getUserByEmail(email);

            if (role.equals("personalTrainer")) {
                personalTrainerDb.addPersonalTrainer(user.getId(), lname + " " + fname, null, email, tel, null, null, 0, false);
            }
            response.sendRedirect("./user?action=getAllUsers");
        } else {
            request.setAttribute("registered", "true");
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/editUserForm.jsp");
            rd.forward(request, response);
        }

    }

    private void editUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String role = request.getParameter("role");
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String tel = request.getParameter("tel");
        String gender = request.getParameter("gender");
        if (role.equals("personalTrainer")) {
            PersonalTrainer isExisting = personalTrainerDb.getPersonalTrainerById(id);
            if (isExisting == null) {
                personalTrainerDb.addPersonalTrainer(id, lname + " " + fname, null, email, tel, null, null, 0, false);
            }
        }else{
             personalTrainerDb.deletePersonalTrainer(id);
        }
        User user = new User(id, role, fname, lname, email, password, tel, gender);
        userDb.editUser(user);
        response.sendRedirect("./user?action=getAllUsers");
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        personalTrainerDb.deletePersonalTrainer(id);
        userDb.deleteUser(id);
        bookingDb.deleteBookingByProductId(id);
        response.sendRedirect("./user?action=getAllUsers");
    }
}
