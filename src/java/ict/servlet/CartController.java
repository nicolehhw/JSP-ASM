package ict.servlet;

import ict.bean.Cart;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "CartController", urlPatterns = {"/cart"})
public class CartController extends HttpServlet {

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
            case "getCart":
                getCart(request, response);
                break;
            case "addToCart":
                addToCart(request, response);
                break;
            case "removeFromCart":
                removeFromCart(request, response);
                break;
            case "removeAllFromCart":
                removeAllFromCart(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED);
        }
    }

    private void getCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        if (session.getAttribute("cart") == null) {
            ArrayList<Cart> cart = new ArrayList<>();
            session.setAttribute("cart", cart);
        }
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/cart.jsp");
        rd.forward(request, response);
    }

    private void addToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getParameter("dispatch");
        int productId = Integer.parseInt(request.getParameter("productId"));
        String bookingType = request.getParameter("bookingType");
        String name = request.getParameter("name");
        String location = request.getParameter("location");
        Double hourlyRate = Double.parseDouble(request.getParameter("hourlyRate"));
        String bookingDate = request.getParameter("bookingDate");
        String timeSlot = request.getParameter("timeSlot");

        ArrayList<Cart> cart;

        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null) {
            if (session.getAttribute("cart") != null) {
                cart = (ArrayList<Cart>) session.getAttribute("cart");
            } else {
                cart = new ArrayList<>();
            }
            Cart cartItem = new Cart(productId, bookingType, name, location, bookingDate, timeSlot, hourlyRate);
            cart.add(cartItem);
            session.setAttribute("cart", cart);
            request.setAttribute("success", "true");
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/" + url);
            rd.forward(request, response);
        }else{
            request.setAttribute("error", "Please log in your account");
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
            rd.forward(request, response);
        }
    }

    private void removeFromCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int index = Integer.parseInt(request.getParameter("id"));
        HttpSession session = request.getSession();

        if (session.getAttribute("cart") != null) {
            ArrayList<Cart> cart = (ArrayList<Cart>) session.getAttribute("cart");
            if (cart.size() > index) {
                cart.remove(index);
                session.setAttribute("cart", cart);
            }
        }
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/cart?action=getCart");
        rd.forward(request, response);
    }

    private void removeAllFromCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute("cart");
        }
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/cart?action=getCart");
        rd.forward(request, response);
    }

}
