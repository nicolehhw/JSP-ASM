<%@page import="ict.bean.User"%>
<%@page import="ict.utils.DateUtil"%>
<%@page import="ict.bean.Cart"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="icon" href="./media/icon.png">
        <title>DGL | Cart</title>
        <!-- import fontawesome -->
        <script
            src="https://kit.fontawesome.com/4446d08019.js"
            crossorigin="anonymous"
        ></script>
        <!-- import bootstrap -->
        <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
            crossorigin="anonymous"
            />
        <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
            crossorigin="anonymous"
        ></script>
        <!-- import jquery -->
        <script
            src="https://code.jquery.com/jquery-3.6.0.js"
            integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
            crossorigin="anonymous"
        ></script>
        <!-- import poppin font -->
        <link
            href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap"
            rel="stylesheet"
            />
        <!--import toastr-->
        <link href="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/css/toastr.min.css" rel="stylesheet" />
        <script src="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/js/toastr.min.js"></script>
        <link rel="stylesheet" href="./css/commonStyle.css" />
        <link rel="stylesheet" href="./css/header.css" />
        <link rel="stylesheet" href="./css/footer.css" />
        <link rel="stylesheet" href="./css/cart.css" />
    </head>
    <body>
        <jsp:useBean id="user" class="User" scope="session" />
        <jsp:useBean id="cart" class="ArrayList<Cart>" scope="session" />
        <jsp:include page="./components/header.jsp" />
        <div id="cart" class="page-padding-container">
            <div class="container">
                <h4 class="title">Cart - Booking Items</h4>
                <div class="divider"></div>
                <div class="top-bar">
                    <span class="total-label">Total booking: <%=cart.size()%></span>
                    <%if (cart.size() != 0) {%>
                    <a href="./cart?action=removeAllFromCart" type="button" class="btn btn-danger">
                        Remove All
                    </a>
                    <%}%>
                </div>
                <table class="booking-list">
                    <thead>
                        <tr>
                            <th>Item No.</th>
                            <th>Booking Type</th>
                            <th>Location</th>
                            <th>Name</th>
                            <th>Hourly Rate</th>
                            <th>Booking Date</th>
                            <th>Time Slot</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            for (int i = 0; i < cart.size(); i++) {
                                Cart cartItem = cart.get(i);
                        %>
                        <tr>
                            <td>
                                <span class="responsive-label">Item No: </span>
                                <%=i + 1%>
                            </td>
                            <td>
                                <span class="responsive-label">Booking Type: </span>
                                <%=cartItem.getBookingType()%>
                            </td>
                            <td>
                                <span class="responsive-label">Location: </span>
                                <%=cartItem.getLocation() != null ? cartItem.getLocation() : "/"%>
                            </td>
                            <td>
                                <span class="responsive-label">Name: </span>
                                <%=cartItem.getName()%>
                            </td>
                            <td>
                                <span class="responsive-label">Hourly Rate: </span>
                                <%=cartItem.getHourlyRate()%>
                            </td>
                            <td>
                                <span class="responsive-label">Booking Date: </span>
                                <%=cartItem.getBookingDate()%>
                            </td>
                            <td>
                                <span class="responsive-label">Time Slot: </span>
                                <%=cartItem.getTimeSlot()%>
                            </td>
                            <td>
                                <a href="./cart?action=removeFromCart&id=<%=i%>" type="button" class="btn btn-danger w-100">
                                    <i class="fa-solid fa-trash-can"></i>
                                </a>
                            </td>
                        </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
                <div class="divider"></div>
                <%if (cart.size() != 0) {%>
                <form method="post" action="booking">
                    <input type="hidden" name="action" value="addBooking" />
                    <button type="submit" class="btn make-booking-btn w-100">Booking Now</button>
                </form>
                <%}%>
            </div>
        </div>
        <jsp:include page="./components/footer.jsp" />
        <script src="./js/header.js"></script>
        <script>
            toastr.options = {
                "progressBar": true,
                "positionClass": "toast-top-right",
                "timeOut": "3000"
            };

            const params = new URLSearchParams(window.location.search).get("success");
            if (params === "true") {
                toastr.success("Booked Successfully");
            }
        </script>
    </body>
</html>
