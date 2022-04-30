<%@page import="ict.bean.Booking"%>
<%@page import="java.util.ArrayList"%>
<%@page import="ict.utils.DateUtil"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="icon" href="./media/icon.png">
        <title>DGL | Booking Records</title>
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
        <!-- import datatables -->
        <link
            rel="stylesheet"
            href="https://cdn.datatables.net/1.11.5/css/dataTables.bootstrap5.min.css"
            />
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.0.1/css/bootstrap.min.css"
            />
        <script src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js"></script>
        <script src="https://cdn.datatables.net/1.11.5/js/dataTables.bootstrap5.min.js"></script>
        <!-- import datatables responsive -->
        <link
            rel="stylesheet"
            href="https://cdn.datatables.net/fixedheader/3.2.2/css/fixedHeader.bootstrap.min.css"
            />
        <link
            rel="stylesheet"
            href="https://cdn.datatables.net/responsive/2.2.9/css/responsive.bootstrap.min.css"
            />
        <script src="https://cdn.datatables.net/fixedheader/3.2.2/js/dataTables.fixedHeader.min.js"></script>
        <script src="https://cdn.datatables.net/responsive/2.2.9/js/dataTables.responsive.min.js"></script>
        <script src="https://cdn.datatables.net/responsive/2.2.9/js/responsive.bootstrap.min.js"></script>
        <link rel="stylesheet" href="./css/commonStyle.css" />
        <link rel="stylesheet" href="./css/header.css" />
        <link rel="stylesheet" href="./css/footer.css" />
        <link rel="stylesheet" href="./css/bookingRecords.css" />
    </head>
    <body>
        <jsp:useBean id="bookings" class="ArrayList<Booking>" scope="request" />
        <jsp:include page="./components/header.jsp" />
        <div id="bookingRecords" class="page-padding-container">
            <div class="container">
                <h3 class="title">Booking Records</h3>
                <table
                    id="booking-records-table"
                    class="table table-striped nowrap"
                    style="width: 100%"
                    >
                    <thead>
                        <tr>
                            <th>Order ID</th>
                            <th>Order Date</th>
                            <th class="tablet-l">Product ID</th>
                            <th class="tablet-l">CustName</th>
                            <th class="tablet-l">CustEmail</th>
                            <th class="tablet-l">CustTel</th>
                            <th>Type</th>
                            <th>Name</th>
                            <th>Location</th>
                            <th>Hourly Rate</th>
                            <th>Booking Date</th>
                            <th>Time Slot</th>
                            <th>Status</th>
                            <th>Update</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            for (Booking booking : bookings) {
                        %>
                        <tr>
                            <td><%=booking.getId()%></td>
                            <td><%=booking.getOrderDate()%></td>
                            <td><%=booking.getProductId()%></td>
                            <td class="custName"><%=booking.getCustName()%></td>
                            <td class="custEmail"><%=booking.getCustEmail()%></td>
                            <td class="custTel"><%=booking.getCustTel()%></td>
                            <td><%=booking.getBookingType()%></td>
                            <td><%=booking.getProductName()%></td>
                            <td><%=booking.getProductLoc() != null ? booking.getProductLoc() : "/"%></td>
                            <td><%=booking.getPrice()%></td>
                            <td class="bookingDate"><%=booking.getBookingDate()%></td>
                            <td class="timeSlot"><%=booking.getTimeSlot()%></td>
                            <td>
                                <span class="badge bg-secondary rounded-pill"><%=booking.getStatus()%></span>
                            </td>
                            <td>
                                <%if (booking.getStatus().equals("Waiting for confirmation")) {%>
                                <a href="./booking?action=getBookingById&id=<%=booking.getId()%>" class="btn btn-warning">
                                    Update
                                </a>
                                <%} else {%>
                                <button class="btn btn-warning" disabled>N/A</button>
                                <%}%>
                            </td>
                        </tr>
                        <%
                            }
                        %>
                    </tbody>
                    <tfoot>
                        <tr>
                            <th>Order ID</th>
                            <th>Order Date</th>
                            <th class="tablet-l">Product ID</th>
                            <th class="tablet-l">CustName</th>
                            <th class="tablet-l">CustEmail</th>
                            <th class="tablet-l">CustTel</th>
                            <th>Type</th>
                            <th>Name</th>
                            <th>Location</th>
                            <th>Hourly Rate</th>
                            <th>Booking Date</th>
                            <th>Time Slot</th>
                            <th>Status</th>
                            <th>Update</th>
                        </tr>
                    </tfoot>
                </table>
            </div>
        </div>
        <jsp:include page="./components/footer.jsp" />
    </body>
    <script src="./js/header.js"></script>
    <script src="./js/bookingRecords.js"></script>
</html>
