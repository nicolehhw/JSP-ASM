<%@page import="ict.utils.Image"%>
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
        <title>DGL | Booking</title>
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
        <link rel="stylesheet" href="./css/bookingForm.css" />
    </head>
    <body>
        <jsp:useBean id="personalTrainer" class="ict.bean.PersonalTrainer" scope="request" />
        <jsp:useBean id="bookedTimeSlots" class="ArrayList<String>" scope="request" />
        <jsp:include page="./components/header.jsp" />
        <div id="booking" class="page-center-container">
            <div class="container">
                <div class="booking-wrapper">
                    <div class="booking-details">
                        <img
                            src="<%=Image.covertBlobToImg(personalTrainer.getImg())%>"
                            onerror="this.src='./media/defaultImg.png'"
                            alt="personal-trainer-img"
                            class="img"
                            />
                        <table class="details">
                            <tbody>
                                <tr>
                                    <td>Name:</td>
                                    <td><%=personalTrainer.getName()%></td>
                                </tr>
                                <tr>
                                    <td>Training type:</td>
                                    <td><%=personalTrainer.getTrainingType()%></td>
                                </tr>
                                <tr>
                                    <td>Contact:</td>
                                    <td><%=personalTrainer.getTel()%></td>
                                </tr>
                                <tr>
                                    <td>Hourly Rate:</td>
                                    <td>$<%=personalTrainer.getHourlyRate()%> HKD</td>
                                </tr>
                                <tr>
                                    <td>Description:</td>
                                    <td><%=personalTrainer.getDescription()%></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <form id="booking-form" method="post" action="cart">
                        <input type="hidden" name="action" value="addToCart" />
                        <input type="hidden" name="dispatch" 
                               value="personalTrainer?action=getPersonalTrainerByEnabled" />
                        <input type="hidden" name="bookingType" value="Personal Trainer" />
                        <input type="hidden" name="productId" value="<%=personalTrainer.getId()%>"/>
                        <input type="hidden" name="name" value="<%=personalTrainer.getName()%>"/>
                        <input type="hidden" name="hourlyRate" value="<%=personalTrainer.getHourlyRate()%>"/>
                        <div class="mb-3">
                            <span class="form-title">Choose Date</span>
                            <span class="divider"></span>
                            <input
                                type="date"
                                class="form-control booking-date-input"
                                name="bookingDate"
                                min="<%=DateUtil.getToday()%>"
                                value="<%=request.getParameter("bookingDate")%>"
                                required
                                />
                        </div>
                        <span class="form-title">Choose time</span>
                        <span class="divider"></span>
                        <div class="time-slot-container">
                            <%
                                for (int i = 11; i <= 22; i++) {
                                    String id = i + "-" + (i + 1);
                                    String val = i + ":00 - " + (i + 1) + ":00";
                                    Boolean isDisabled = i < Integer.parseInt(DateUtil.getTime()) && DateUtil.getToday().equals(request.getParameter("bookingDate"));
                            %>
                            <input
                                type="radio"
                                class="btn-check"
                                name="timeSlot"
                                autocomplete="off"
                                id="<%=id%>"
                                value="<%=val%>"
                                <%=bookedTimeSlots.contains(val) ? "disabled" : ""%>
                                <%= isDisabled ? "disabled" : ""%>
                                required
                                />
                            <label class="btn <%=bookedTimeSlots.contains(val) ? "btn-secondary" : "btn-outline-success"%>" for="<%=id%>"
                                   ><%=val%></label
                            >
                            <%}%>
                        </div>
                        <button type="submit" class="btn btn-dark w-100 mt-2">
                            Add To Cart
                        </button>
                    </form>
                </div>
            </div>
        </div>
        <jsp:include page="./components/footer.jsp" />
        <script src="./js/header.js"></script>
        <script>
            $(document).ready(function () {
                $("input[name='bookingDate']").change(function () {
                    const bookingDate = $(this).val();
                    const id = <%=request.getParameter("id")%>;
                    window.location.href = "./personalTrainer?action=getPersonalTrainerBookingForm&id=" + id + "&bookingDate=" + bookingDate + "";
                });
            });
        </script>
    </body>
</html>
