<%@page import="ict.utils.DateUtil"%>
<%@page import="java.util.HashMap"%>
<%@page import="ict.bean.Booking"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="icon" href="./media/icon.png">
        <title>DGL | Report</title>
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
        <link rel="stylesheet" href="./css/report.css" />
    </head>
    <body>
        <jsp:useBean id="report" class="ict.bean.Report" scope="request" />
        <%
            String chartData = "";
            HashMap<Integer, Double> monthlyIncomes = report.getMonthlyIncomes();
            for (int i = 1; i <= 12; i++) {
                if (monthlyIncomes.get(i) != null) {
                    chartData += monthlyIncomes.get(i) + ",";
                } else {
                    chartData += "0,";
                }
            }
            chartData = chartData.substring(0, chartData.length() - 1);
        %>
        <jsp:include page="./components/header.jsp" />
        <div id="report" class="page-padding-container">
            <input type="hidden" name="chartData" value="<%=chartData%>" />
            <div class="container">
                <div id="report-container">
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
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                for (Booking booking : report.getBookings()) {
                            %>
                            <tr>
                                <td><%=booking.getId()%></td>
                                <td><%=booking.getOrderDate()%></td>
                                <td><%=booking.getProductId()%></td>
                                <td><%=booking.getCustName()%></td>
                                <td><%=booking.getCustEmail()%></td>
                                <td><%=booking.getCustTel()%></td>
                                <td><%=booking.getBookingType()%></td>
                                <td><%=booking.getProductName()%></td>
                                <td><%=booking.getProductLoc() != null ? booking.getProductLoc() : "/"%></td>
                                <td><%=booking.getPrice()%></td>
                                <td><%=booking.getBookingDate()%></td>
                                <td><%=booking.getTimeSlot()%></td>
                                <td>
                                    <span class="badge bg-secondary rounded-pill"><%=booking.getStatus()%></span>
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
                            </tr>
                        </tfoot>
                    </table>
                    <h3 class="title">Analytic Report</h3>
                    <div class="graphs">
                        <form method="get" action="report">
                            <input type="hidden" name="action" value="getReport" />
                            <input type="hidden" name="productId" value="<%=request.getParameter("productId")%>" />
                            <input type="number" name="year" min="2015" max="<%=DateUtil.getYear()%>" 
                                   step="1" value="<%=request.getParameter("year")%>" onchange="this.form.submit()"/>
                        </form>
                        <div class="white-bg-color">
                            <div class="income-chart-container"> 
                                <div>
                                    <canvas id="income-chart"></canvas>
                                </div>
                                <h4 class="details-title"><%=request.getParameter("year")%> - Details</h4>
                                <table class="details">
                                    <tbody>
                                        <tr>
                                            <td>Total Yearly Income:</td>
                                            <td>$<%=report.getYearlyIncome()%> HKD</td>
                                        </tr>
                                        <tr>
                                            <td>Booking Rate (Year):</td>
                                            <td><%=report.getBookingRate()%>%</td>
                                        </tr>
                                        <tr>
                                            <td>Total Booking Days of year:</td>
                                            <td><%=report.getTotalBookingDays()%> / 365 days</td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="./components/footer.jsp" />
        <script src="./js/header.js"></script>
        <script src="./js/report.js"></script>
        <script>
            const data = $("#report input[name='chartData']").val().split(",");
            const income_chart = new Chart($("#income-chart"), {
                type: 'line',
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
                            label: 'Monthly Income',
                            data: data,
                            fill: false,
                            borderColor: 'rgb(75, 192, 192)',
                            tension: 0.1
                        },
                    ],
                },
            });
        </script>
    </body>
</html>
