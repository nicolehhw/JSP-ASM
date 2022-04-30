<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="icon" href="./media/icon.png">
        <title>DGL | Login</title>
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
        <link rel="stylesheet" href="./css/login.css" />
    </head>
    <body>
        <jsp:include page="./components/header.jsp" />
        <div id="login" class="page-center-container">
            <form method="post" action="login">
                <input type="hidden" name="action" value="login" />
                <h3 class="title">Login</h3>
                <%
                    if (request.getAttribute("error") != null) {
                %>
                <div class="alert alert-danger" role="alert">
                    <%=request.getAttribute("error")%>
                </div>
                <%
                    }
                %>
                <div class="form-group">
                    <label for="email" class="mb-2">Email address</label>
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
                    <label for="password" class="mb-2">Password</label>
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
                <button type="submit" class="btn btn-primary mt-2">Log in</button>
                <a class="btn btn-primary" href="./register.jsp" role="button"
                   >Sign Up</a
                >
            </form>
        </div>
        <jsp:include page="./components/footer.jsp" />
    </body>
    <script src="./js/header.js"></script>
</html>
