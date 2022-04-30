<%@page import="ict.utils.DateUtil"%>
<%@page import="java.util.ArrayList"%>
<%@page import="ict.bean.GymCenter"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="icon" href="./media/icon.png">
        <title>DGL | Management</title>
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
        <!-- import export function -->
        <script src="https://cdn.datatables.net/buttons/2.2.2/js/dataTables.buttons.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/pdfmake.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/vfs_fonts.js"></script>
        <script src="https://cdn.datatables.net/buttons/2.2.2/js/buttons.html5.min.js"></script>
        <!-- import chart js -->
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <link rel="stylesheet" href="./css/commonStyle.css" />
        <link rel="stylesheet" href="./css/header.css" />
        <link rel="stylesheet" href="./css/footer.css" />
        <link rel="stylesheet" href="./css/management.css" />
    </head>
    <body>
        <jsp:useBean id="user" class="ict.bean.User" scope="session" />
        <jsp:useBean id="gymCenters" class="ArrayList<GymCenter>" scope="request" />
        <jsp:include page="./components/header.jsp" />
        <div id="management" class="page-padding-container">
            <div class="container">
                <div class="management-container">
                    <jsp:include page="./components/managementLeftBar.jsp" />
                    <div id="page">
                        <table
                            id="gym-center-m-table"
                            class="table table-striped nowrap active show"
                            style="width: 100%"
                            >
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Location</th>
                                    <th>Name</th>
                                    <th>Tel</th>
                                    <th>HourlyRate</th>
                                    <th>Status</th>
                                    <th>Edit</th>
                                    <th>Delete</th>
                                    <%if(user.getRole().equals("admin")){%>
                                        <th>Report</th>
                                    <%}%>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    for (GymCenter gymCenter : gymCenters) {
                                %>
                                <tr>
                                    <td><%=gymCenter.getId()%></td>
                                    <td><%=gymCenter.getLocation()%></td>
                                    <td><%=gymCenter.getName()%></td>
                                    <td><%=gymCenter.getTel()%></td>
                                    <td><%=gymCenter.getHourlyRate()%></td>
                                    <td>
                                        <%if (gymCenter.getIsEnabled()) {%>
                                        <span class="badge bg-success fs-6">Enabled</span>
                                        <%} else {%>
                                        <span class="badge bg-danger fs-6">Disabled</span>
                                        <%}%>
                                    </td>
                                    <td>
                                        <a href="./gymCenter?action=getGymCenterById&id=<%=gymCenter.getId()%>"
                                           class="btn btn-warning"
                                           >
                                            Edit
                                        </a>
                                    </td>
                                    <td>
                                        <form method="post" action="gymCenter">
                                            <input
                                                type="hidden"
                                                name="action"
                                                value="deleteGymCenter"
                                                />
                                            <input type="hidden" name="id" value="<%=gymCenter.getId()%>" />
                                            <button type="submit" class="btn btn-danger">
                                                Delete
                                            </button>
                                        </form>
                                    </td>
                                    <%if(user.getRole().equals("admin")){%>
                                        <td>
                                            <a href="./report?action=getReport&productId=<%=gymCenter.getId()%>&year=<%=DateUtil.getYear()%>" 
                                               class="btn btn-success">View</a>
                                        </td>
                                    <%}%>
                                </tr>
                                <%
                                    }
                                %>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <th>ID</th>
                                    <th>Location</th>
                                    <th>Name</th>
                                    <th>Tel</th>
                                    <th>HourlyRate</th>
                                    <th>Status</th>
                                    <th>Edit</th>
                                    <th>Delete</th>
                                    <%if(user.getRole().equals("admin")){%>
                                        <th>Report</th>
                                    <%}%>
                                </tr>
                            </tfoot>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="./components/footer.jsp" />
        <script src="./js/header.js"></script>
        <script src="./js/management.js"></script>
        <script>
            const gym_center_booking_rate_chart = new Chart(
                    document.getElementById("report-booking-rate-chart"),
                    {
                        type: "pie",
                        data: {
                            labels: ["All bookings", "gym center bookings"],
                            datasets: [
                                {
                                    data: [50, 100],
                                    backgroundColor: ["rgb(54, 162, 235)", "rgb(255, 205, 86)"],
                                },
                            ],
                        },
                        options: {
                            responsive: true,
                        },
                    }
            );

            const gym_center_income_chart = new Chart(
                    document.getElementById("report-income-chart"),
                    {
                        type: "line",
                        data: {
                            labels: [
                                "January",
                                "February",
                                "March",
                                "April",
                                "May",
                                "June",
                                "July",
                                "August",
                                "September",
                                "October",
                                "November",
                                "December",
                            ],
                            datasets: [
                                {
                                    label: "Monthly Income",
                                    data: [65, 59, 80, 81, 56, 55, 40, 80, 81, 56, 55, 40],
                                    fill: false,
                                    borderColor: "rgb(75, 192, 192)",
                                    tension: 0.1,
                                },
                            ],
                        },
                        options: {
                            responsive: true,
                        },
                    }
            );
        </script>
    </div>
</body>
</html>
