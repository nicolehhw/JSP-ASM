<jsp:useBean id="user" class="ict.bean.User" scope="session" />
<%
    String role = user.getRole();
    if (role.equals("staff")) {
%>
<div id="left-bar">
    <a href="./gymCenter?action=getAllGymCenters" class="left-bar-btn"> Gym center management </a>
    <a href="./personalTrainer?action=getAllPersonalTrainers" class="left-bar-btn"> Personal trainer management </a>
    <a href="./booking?action=getAllBookings" class="left-bar-btn"> Booking requests</a>
</div>
<%
    } else if (role.equals("personalTrainer")) {
%>
<div id="left-bar">
    <a href="./personalTrainer?action=getPersonalTrainerById&dispatch=personalTrainerProfile&id=<%=user.getId()%>" class="left-bar-btn"> Personal trainer management </a>
    <a href="./booking?action=getPersonalTrainerBookings&id=<%=user.getId()%>" class="left-bar-btn"> Booking requests</a>
</div>
<%
    } else if (role.equals("admin")) {
%>
<div id="left-bar">
   <a href="./gymCenter?action=getAllGymCenters" class="left-bar-btn"> Gym center management </a>
    <a href="./personalTrainer?action=getAllPersonalTrainers" class="left-bar-btn"> Personal trainer management </a>
    <a href="./user?action=getAllUsers" class="left-bar-btn"> User management</a>
</div>
<%
    }
%>