<jsp:useBean id="user" class="ict.bean.User" scope="session" />
<%String role = user.getRole();%>
<header id="header">
    <div class="container">
        <nav class="nav">
            <a href="./home.jsp" class="nav-logo">
                <h1>DGL</h1>
            </a>
            <ul class="menu">
                <button class="close-menu-btn">
                    <i class="fa-solid fa-xmark"></i>
                </button>
                <li><a href="./home.jsp">Home</a></li>
                <li><a href="./gymCenter?action=getGymCenterByEnabled">Booking</a></li>
                    <%if (role != null && role.equals("customer")) {%>
                <li><a href="./booking?action=getCustomerBookings&id=<%=user.getId()%>">Booking records</a></li>
                <li><a href="./cart?action=getCart"><i class="fa-solid fa-cart-shopping"></i></a></li>
                    <%}%>
                    <%if (role != null && (role.equals("staff") || (role.equals("admin")))) {%>
                <li><a href="./gymCenter?action=getAllGymCenters">Management</a></li>
                    <%} else if (role != null && role.equals("personalTrainer")) {%>
                <li><a href="./personalTrainer?action=getPersonalTrainerById&dispatch=personalTrainerProfile&id=<%=user.getId()%>">Management</a></li>
                    <%}%>
                    <%if (role != null && !(role.equals("admin") || role.equals("staff"))) {%>
                <li>
                    <a class="reminder" href="./bookingReminder?action=getAllBookingReminders&id=<%=user.getId()%>">
                        <i class="fa-solid fa-bell"></i>
                    </a>
                </li>
                <%}%> 
                <%if (role == null) {%>
                <li><a href="./login.jsp">Login</a></li>
                <li><a href="./register.jsp">Sign Up</a></li>
                    <%} else {%>
                <li>
                    <form method="post" action="login">
                        <input type="hidden" name="action" value="logout" />
                        <button id="logout-btn" type="submit" name="logoutBtn">
                            <i class="fa-solid fa-right-from-bracket"></i>
                        </button>
                    </form>
                </li>
                <%}%>
            </ul>

            <button class="menu-toggle-btn">
                <i class="fa-solid fa-bars"></i>
            </button>
        </nav>
    </div>
</header>
