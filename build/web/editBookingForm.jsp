<%@page import="ict.utils.DateUtil"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="icon" href="./media/icon.png">
        <title>DGL | Home</title>
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
        <link rel="stylesheet" href="./css/commonStyle.css" />
        <link rel="stylesheet" href="./css/header.css" />
        <link rel="stylesheet" href="./css/footer.css" />
        <link rel="stylesheet" href="./css/editBookingForm.css" />
    </head>
    <body>
        <jsp:useBean id="user" class="ict.bean.User" scope="session" />
        <jsp:useBean id="booking" class="ict.bean.Booking" scope="request" />
        <jsp:include page="./components/header.jsp" />
        <div id="editBookingForm" class="page-center-container">
            <form id="booking-form" method="post" action="booking">
                <%if (user.getRole().equals("personalTrainer")) {%>
                <input type="hidden" name="dispatch" value="./booking?action=getPersonalTrainerBookings&id=<%=user.getId()%>" />
                <%} else {%>
                <input type="hidden" name="dispatch" value="./booking?action=getAllBookings" />
                <%}%>
                <input type="hidden"name="action" 
                       value="<%=user.getRole().equals("customer") ? "customerEditBooking" : "editBooking"%>"/>
                <input type="hidden" name="id" value="<%=booking.getId()%>" />
                <input type="hidden" name="custId" value="<%=booking.getCustId()%>" />
                <%if (!user.getRole().equals("customer")) {%>
                <div class="form-group mb-3">
                    <label class="mb-2">Status</label>
                    <select class="form-select" name="status" required>
                        <% if (booking.getStatus() != null) {%>
                        <option selected hidden value="<%=booking.getStatus()%>">
                            <%=booking.getStatus()%>
                        </option>
                        <%}%>
                        <option value="Waiting for confirmation">Waiting for confirmation</option>
                        <option value="Cancel">Cancel</option>
                        <option value="Confirmed">Confirmed</option>
                    </select>
                </div>
                <div class="form-group mb-3">
                    <label class="mb-2">Hourly Rate</label>
                    <div class="input-group mb-3">
                        <span class="input-group-text">$</span>
                        <input
                            type="number"
                            class="form-control"
                            name="price"
                            value="<%=booking.getPrice()%>"
                            required
                            />
                    </div>
                </div>
                <%} else {%>
                <input type="hidden" name="status" value="<%=booking.getStatus()%>" />
                <%}%>
                <div class="mb-3">
                    <label class="form-label">Name</label>
                    <input type="text" class="form-control" name="custName" value="<%=booking.getCustName()%>" required/>
                </div>
                <div class="mb-3">
                    <label class="form-label">Email</label>
                    <input type="email" class="form-control" name="custEmail" value="<%=booking.getCustEmail()%>" required/>
                </div>
                <div class="col-auto">
                    <label for="telphone" class="mb-1">Telphone</label>
                    <div class="input-group mb-2">
                        <div class="input-group-prepend">
                            <div class="input-group-text">+852</div>
                        </div>
                        <input
                            id="telephone"
                            type="text"
                            name="custTel"
                            class="form-control"
                            placeholder="Eg: 12345678"
                            value="<%=booking.getCustTel()%>"
                            required
                            />
                    </div>
                </div>
                <div class="mb-3">
                    <span class="form-title">Choose time</span>
                    <input
                        type="date"
                        class="form-control"
                        name="bookingDate"
                        min="<%=DateUtil.getToday()%>"
                        value="<%=booking.getBookingDate()%>"
                        required
                        />
                </div>
                <span class="form-title">Choose time</span>
                <div class="time-slot-container">
                    <%
                        for (int i = 11; i <= 22; i++) {
                            String id = i + "-" + (i + 1);
                            String val = i + ":00 - " + (i + 1) + ":00";
                    %>
                    <input
                        type="radio"
                        class="btn-check"
                        name="timeSlot"
                        autocomplete="off"
                        id="<%=id%>"
                        value="<%=val%>"
                        <%=booking.getTimeSlot().equals(val) ? "checked" : ""%>
                        required
                        />
                    <label class="btn btn-outline-success" for="<%=id%>"
                           ><%=val%></label
                    >
                    <%}%>
                </div>
                <button type="submit" class="btn btn-primary w-100">
                    Submit
                </button>
            </form>
        </div>
        <jsp:include page="./components/footer.jsp" />
    </body>
</html>
