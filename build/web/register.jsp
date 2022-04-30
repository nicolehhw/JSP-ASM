<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="icon" href="./media/icon.png">
        <title>DGL | Registeration</title>
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
        <link rel="stylesheet" href="./css/register.css" />
    </head>
    <body>
        <jsp:include page="./components/header.jsp" />
        <div id="register" class="page-center-container">
            <form method="post" action="register">
                <input type="hidden" name="action" value="register" />
                <h3 class="title">Sign Up</h3>
                <%
                    if (request.getAttribute("registered") != null) {
                %>
                <div class="alert alert-danger" role="alert">
                    <%=request.getAttribute("registered")%>
                </div>
                <%
                    }
                %>
                <div class="row">
                    <div class="col">
                        <label for="fname" class="mb-1">First name</label>
                        <input
                            id="fname"
                            type="text"
                            name="fname"
                            class="form-control"
                            placeholder="First name"
                            required
                            />
                    </div>
                    <div class="col">
                        <label for="lname" class="mb-1">Last name</label>
                        <input
                            id="lname"
                            type="text"
                            name="lname"
                            class="form-control"
                            placeholder="Last name"
                            required
                            />
                    </div>
                </div>
                <div class="form-group">
                    <label for="email" class="mb-1">Email address</label>
                    <input
                        type="email"
                        class="form-control"
                        id="email"
                        name="email"
                        placeholder="Enter email"
                        required
                        />
                </div>
                <div class="form-group">
                    <label for="password" class="mb-1">Password</label>
                    <input
                        type="password"
                        class="form-control"
                        id="password"
                        name="password"
                        placeholder="Password"
                        minLength="6"
                        required
                        />
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
                            name="tel"
                            class="form-control"
                            id="inlineFormInputGroup"
                            placeholder="Eg: 12345678"
                            pattern="[0-9]{8}" 
                            title="Eg: 12345678"
                            required
                            />
                    </div>
                </div>
                <div class="form-group">
                    <label for="gender" class="d-block">Gender</label>
                    <div class="form-check-inline mt-2">
                        <input class="form-check-input" type="radio" name="gender" value="Male" checked required>
                        <label class="form-check-label" for="inlineRadio1">Male</label>
                    </div>
                    <div class="form-check-inline">
                        <input class="form-check-input" type="radio" name="gender" id="inlineRadio2" value="Female" required>
                        <label class="form-check-label" for="inlineRadio2">Female</label>
                    </div>
                </div>
                <button type="submit" class="btn btn-primary mt-1">Create</button>
            </form>
        </div>
        <jsp:include page="./components/footer.jsp" />
        <script src="./js/header.js"></script>
    </body>
</html>
