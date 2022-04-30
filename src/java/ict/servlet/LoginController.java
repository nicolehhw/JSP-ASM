package ict.servlet;

import ict.bean.User;
import ict.db.UserDB;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "LoginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {

    private UserDB userDb;

    @Override
    public void init() {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        userDb = new UserDB(dbUrl, dbUser, dbPassword);
        userDb.createUserTable();
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

        // prevent user who hasn't logged in makes api calls except handleLogin api
        if (!isLoggedIn(request) && !(action.equals("login"))) {
            response.sendRedirect("/login.jsp");
            return;
        }

        switch (action) {
            case "login":
                login(request, response);
                break;
            case "logout":
                logout(request, response);
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

    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String URL;

        boolean isValid = userDb.isValidUser(email, password);

        if (isValid) {
            HttpSession session = request.getSession(true);
            User user = userDb.getUserByEmail(email);
            session.setAttribute("user", user);
            URL = "home.jsp";
        } else {
            URL = "login.jsp";
            request.setAttribute("error", "Incorrect email or password");
        }

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/" + URL);
        rd.forward(request, response);
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute("user");
            session.invalidate();
        }
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/home.jsp");
        rd.forward(request, response);
    }
}
