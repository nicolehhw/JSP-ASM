<%@page import="ict.utils.DateUtil"%>
<%@page import="java.util.Date"%>
<%@page import="ict.utils.Image"%>
<%@page import="ict.bean.PersonalTrainer"%>
<%@page import="java.util.ArrayList"%>
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
        <!--import toastr-->
        <link href="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/css/toastr.min.css" rel="stylesheet" />
        <script src="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/js/toastr.min.js"></script>
        <!-- import AOS -->
        <link href="https://unpkg.com/aos@2.3.1/dist/aos.css" rel="stylesheet">
        <script src="https://unpkg.com/aos@2.3.1/dist/aos.js"></script>
        <link rel="stylesheet" href="./css/commonStyle.css" />
        <link rel="stylesheet" href="./css/header.css" />
        <link rel="stylesheet" href="./css/footer.css" />
        <link rel="stylesheet" href="./css/booking.css" />
    </head>
    <body>
        <jsp:useBean id="personalTrainers" class="ArrayList<PersonalTrainer>" scope="request" />
        <jsp:include page="./components/header.jsp" />
        <div id="booking" class="page-container">
            <div id="service-selection">
                <div class="top-bar">
                    <a class="btn gc-btn" href="./gymCenter?action=getGymCenterByEnabled">Gym Center</a>
                    <a class="btn pt-btn active" href="./personalTrainer?action=getPersonalTrainerByEnabled">Personal Trainer</a>
                </div>
            </div>
            <div id="booking-content">
                <div class="container">
                    <div id="filter-bar">
                        <h3 class="title">Personal Trainers</h3>
                        <form id="search-form" method="post" action="personalTrainer">
                            <input type="hidden" name="action" value="searchPersonalTrainers" />
                            <input
                                class="form-control"
                                type="text"
                                name="keyword"
                                placeholder="Search"
                                />
                        </form>
                        <a href="./personalTrainer?action=getPersonalTrainerByEnabled" class="clear-search-btn">
                            <i class="fa-solid fa-xmark"></i>
                        </a>
                        <select id="sortBy" class="form-select">
                            <option value="asc">Price: low to high</option>
                            <option value="desc"> Price: high to low</option>
                        </select>
                    </div>
                    <div class="personal-trainer-list">
                        <%if (personalTrainers.size() == 0) {%>
                        <h4 class="empty-list-msg">No Personal Trainer Can Be Shown</h4>
                        <%}%>
                        <%
                            for (int i = 0; i < personalTrainers.size(); i++) {
                                PersonalTrainer personalTrainer = personalTrainers.get(i);
                        %>
                        <a
                            href="./personalTrainer?action=getPersonalTrainerBookingForm&id=<%=personalTrainer.getId()%>&bookingDate=<%=DateUtil.getToday()%>"
                            data-aos="fade-down"
                            data-aos-delay="<%=i * 200%>"
                            class="card card-btn"
                            >
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
                        </a>
                        <%
                            }
                        %>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="./components/footer.jsp" />
        <script src="./js/header.js"></script>
        <script>
            AOS.init();
        </script>
        <script>
            $(document).ready(function () {
                toastr.options = {
                    "progressBar": true,
                    "positionClass": "toast-top-right",
                    "timeOut": "3000"
                };

                const isSuccess = <%=request.getAttribute("success") != null ? true : false%>;
                if (isSuccess) {
                    toastr.success("Added To Cart");
                }

                $("input[name='keyword']").keyup(function (event) {
                    if (event.which === 13) {
                        event.preventDefault();
                        $("#search-form").submit();
                    }
                })

                $("#sortBy").on('change', function () {
                    const order = $(this).val();
                    window.location.href = "./personalTrainer?action=sortPersonalTrainerByPrice&order=" + order;
                })

                $("#sortBy option").each(function () {
                    if ($(this).val() === "<%=request.getParameter("order")%>") {
                        $(this).attr("selected", "selected");
                        return;
                    }
                })
            });
        </script>
    </body>
</html>
