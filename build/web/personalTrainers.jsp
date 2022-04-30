<%@page import="ict.utils.DateUtil"%>
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
        <jsp:useBean id="personalTrainers" class="ArrayList<PersonalTrainer>" scope="request" />
        <jsp:include page="./components/header.jsp" />
        <div id="management" class="page-padding-container">
            <div class="container">
                <div class="management-container">
                    <jsp:include page="./components/managementLeftBar.jsp" />
                    <div id="page">
                        <table
                            id="personal-trainer-m-table"
                            class="table table-striped nowrap"
                            style="width: 100%"
                            >
                            <thead>
                                <tr>
                                    <th>Trainer ID</th>
                                    <th>Name</th>
                                    <th>Email</th>
                                    <th>Tel</th>
                                    <th>Training Type</th>
                                    <th>Hourly Rate</th>
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
                                    for (PersonalTrainer personalTrainer : personalTrainers) {
                                %> 
                                <tr>
                                    <td><%=personalTrainer.getId()%></td>
                                    <td><%=personalTrainer.getName()%></td>
                                    <td><%=personalTrainer.getEmail()%></td>
                                    <td><%=personalTrainer.getTel()%></td>
                                    <td><%=personalTrainer.getTrainingType()%></td>
                                    <td><%=personalTrainer.getHourlyRate()%></td>
                                    <td>
                                        <%if (personalTrainer.getIsEnabled()) {%>
                                        <span class="badge bg-success fs-6">Enabled</span>
                                        <%} else {%>
                                        <span class="badge bg-danger fs-6">Disbaled</span>
                                        <%}%>
                                    </td>
                                    <td>
                                        <a href="./personalTrainer?action=getPersonalTrainerById&dispatch=editPersonalTrainerForm&id=<%=personalTrainer.getId()%>"
                                           type="button"
                                           class="btn btn-warning"
                                           a>
                                            Edit
                                        </a>
                                    </td>
                                    <td>
                                        <form method="post" action="personalTrainer">
                                            <input
                                                type="hidden"
                                                name="action"
                                                value="deletePersonalTrainer"
                                                />
                                            <input type="hidden" name="id" value="<%=personalTrainer.getId()%>" />
                                            <button type="submit" class="btn btn-danger">
                                                Delete
                                            </button>
                                        </form>
                                    </td>
                                    <%if(user.getRole().equals("admin")){%>
                                        <td>
                                            <a href="./report?action=getReport&productId=<%=personalTrainer.getId()%>&year=<%=DateUtil.getYear()%>" 
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
                                    <th>Trainer ID</th>
                                    <th>Name</th>
                                    <th>Email</th>
                                    <th>Tel</th>
                                    <th>Training Type</th>
                                    <th>Hourly Rate</th>
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
            $("#personal-trainer-m-table tfoot th").each(function () {
            $(this).html('<input type="text" placeholder="Search" />');
            });
            const personal_trainer_m_table = $("#personal-trainer-m-table").DataTable({
            dom: '<"topbar"lf>Bt<"pagaination"ip>',
                    responsive: true,
                    buttons: [
            <%if (user.getRole().equals("admin") || user.getRole().equals("staff")) {%>
                    {
                    text: "Add Trainer",
                            className: "btn btn-primary",
                            action: function (e, dt, node, config) {
                            window.location.href = "./editPersonalTrainerForm.jsp";
                            },
                    },
            <%}%>
                    {
                    extend: "csv",
                            className: "btn btn-primary",
                    },
                    {
                    extend: "excel",
                            className: "btn btn-primary",
                    },
                    {
                    extend: "pdf",
                            className: "btn btn-primary",
                    },
                    ],
                    language: {
                    lengthMenu: "_MENU_",
                            info: "Showing _START_ to _END_ of _TOTAL_ booking records",
                            eroRecords: "No matching booking records found",
                            search: "_INPUT_",
                            searchPlaceholder: "Enter keyword to search personal trainer",
                    },
                    initComplete: function () {
                    // footer search
                    this.api()
                            .columns()
                            .every(function () {
                            var that = this;
                            $("input", this.footer()).on("keyup change clear", function () {
                            if (that.search() !== this.value) {
                            that.search(this.value).draw();
                            }
                            });
                            });
                    },
            });

        </script>
    </div>
</body>
</html>