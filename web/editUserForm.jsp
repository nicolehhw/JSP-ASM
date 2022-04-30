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
        <link rel="stylesheet" href="./css/editUserForm.css" />
    </head>
    <body>
        <jsp:useBean id="user" class="ict.bean.User" scope="request" />
        <jsp:include page="./components/header.jsp" />
        <div id="editUserForm" class="page-center-container">
            <form method="post" action="user">
                <%
                    if (request.getAttribute("registered") != null) {
                %>
                <div class="alert alert-danger" role="alert">
                    Email has been registered
                </div>
                <%
                    }
                %>
                <input type="hidden" name="action" value="<%=user.getEmail() != null ? "editUser" : "addUser"%>" />
                <input type="hidden" name="id" value="<%=user.getEmail() != null ? user.getId() : ""%>" />
                <div class="form-group my-2">
                    <label class="mb-2">Role</label>
                    <select class="form-select" name="role" required>
                        <% if (user.getRole() != null) {%>
                        <option selected hidden value="<%=user.getRole()%>"><%=user.getRole()%></option>
                        <%}%>
                        <option value="customer">Customer</option>
                        <option value="staff">Staff</option>
                        <option value="admin">Senior Management</option>
                        <option value="personalTrainer">
                            Personal Trainer
                        </option>
                    </select>
                </div>
                <div class="row my-1">
                    <div class="col">
                        <label for="fname" class="my-2">First name</label>
                        <input
                            id="fname"
                            type="text"
                            name="fname"
                            class="form-control"
                            placeholder="First name"
                            value="<%=user.getFname() != null ? user.getFname() : ""%>"
                            required
                            />
                    </div>
                    <div class="col">
                        <label for="lname" class="my-2">Last name</label>
                        <input
                            id="lname"
                            type="text"
                            name="lname"
                            class="form-control"
                            placeholder="Last name"
                            value="<%=user.getLname() != null ? user.getLname() : ""%>"
                            required
                            />
                    </div>
                </div>
                <div class="form-group my-1">
                    <label for="email" class="my-2">Email address</label>
                    <input
                        type="email"
                        class="form-control"
                        id="email"
                        name="email"
                        placeholder="Enter email"
                        value="<%=user.getEmail() != null ? user.getEmail() : ""%>"
                        required
                        />
                </div>
                <div class="form-group my-1">
                    <label for="password" class="my-2">Password</label>
                    <input
                        type="password"
                        class="form-control"
                        id="password"
                        name="password"
                        placeholder="Password"
                        value="<%=user.getPassword() != null ? user.getPassword() : ""%>"
                        required
                        />
                </div>
                <div class="col-auto my-1">
                    <label for="telphone" class="my-2">Telphone</label>
                    <div class="input-group mb-2">
                        <div class="input-group-prepend">
                            <div class="input-group-text">+852</div>
                        </div>
                        <input
                            id="telephone"
                            type="text"
                            name="tel"
                            class="form-control"
                            placeholder="Eg: 12345678"
                            value="<%=user.getTel() != null ? user.getTel() : ""%>"
                            required
                            />
                    </div>
                </div>
                <div class="form-group my-1">
                    <label for="gender" class="d-block mt-3">Gender</label>
                    <div class="form-check-inline mt-2">
                        <input
                            class="form-check-input"
                            type="radio"
                            name="gender"
                            value="Male"
                            <%=user.getGender() != null && user.getGender().equals("Male") ? "checked" : ""%>
                            required
                            />
                        <label class="form-check-label" for="inlineRadio1"
                               >Male</label
                        >
                    </div>
                    <div class="form-check-inline">
                        <input
                            class="form-check-input"
                            type="radio"
                            name="gender"
                            id="inlineRadio2"
                            value="Female"
                            <%=user.getGender() != null && user.getGender().equals("Female") ? "checked" : ""%>
                            required
                            />
                        <label class="form-check-label" for="inlineRadio2"
                               >Female</label
                        >
                    </div>
                </div>
                <button type="submit" class="btn btn-primary mt-3 w-100">
                    <%=user.getEmail() != null ? "Edit User" : "Create User"%>
                </button>
            </form>
        </div>

        <jsp:include page="./components/footer.jsp" />
        <script src="./js/header.js"></script>
    </body>
</html>