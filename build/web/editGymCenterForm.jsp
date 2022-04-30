<%@page import="ict.utils.Image"%>
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
        <link rel="stylesheet" href="./css/editGymCenterForm.css" />
    </head>
    <body>
        <jsp:useBean id="gymCenter" class="ict.bean.GymCenter" scope="request" />
        <jsp:include page="./components/header.jsp" />
        <div id="editGymCenterForm" class="page-center-container">
            <form method="post" action="gymCenter" enctype="multipart/form-data">
                <%if (request.getAttribute("imageError") != null) {%>                    
                <div class="alert alert-danger" role="alert">
                    <%=request.getAttribute("imageError")%>
                </div>
                <%}%>
                <input type="hidden" name="action" value="<%=gymCenter.getName() != null ? "editGymCenter" : "addGymCenter"%>" />
                <input type="hidden" name="id" value="<%=gymCenter.getId()%>" />
                <div class="form-group mb-3">
                    <label class="mb-2">Status</label>
                    <select class="form-select" name="isEnabled" required>
                        <% if (gymCenter.getIsEnabled() != null) {%>
                        <option selected hidden value="<%=gymCenter.getIsEnabled()%>">
                            <%=gymCenter.getIsEnabled() ? "Enabled" : "Disabled"%>
                        </option>
                        <%}%>
                        <option value="true">Enabled</option>
                        <option value="false">Disabled</option>
                    </select>
                </div>
                <div class="form-group mb-3">
                    <label class="mb-2 d-block">Gym Center Image</label>
                     <% if (gymCenter.getImg() != null) {%>
                    <img src="<%=Image.covertBlobToImg(gymCenter.getImg())%>" 
                         onerror="this.src='./media/defaultImg.png'"
                         alt="gym-center-img" 
                         class="gym-center-img"/>
                    <%}%>
                    <input type="file" class="form-control" name="image" />
                </div>
                <div class="form-group mb-3">
                    <label class="mb-2">Name</label>
                    <input type="text" class="form-control" name="name" required
                           value="<%=gymCenter.getName() != null ? gymCenter.getName() : ""%>"/>
                </div>
                <div class="form-group mb-3">
                    <label class="mb-2">Location</label>
                    <input type="text" class="form-control" name="location" required
                           value="<%=gymCenter.getLocation() != null ? gymCenter.getLocation() : ""%>"/>
                </div>
                <div class="form-group mb-3">
                    <label class="mb-2">Telphone</label>
                    <div class="input-group mb-2">
                        <div class="input-group-prepend">
                            <div class="input-group-text">+852</div>
                        </div>
                        <input
                            type="text"
                            name="tel"
                            class="form-control"
                            placeholder="Eg: 12345678"
                            value="<%=gymCenter.getTel() != null ? gymCenter.getTel() : ""%>"
                            reuqired
                            />
                    </div>
                </div>
                <div class="form-group mb-3">
                    <label class="mb-2">Hourly Rate</label>
                    <div class="input-group mb-3">
                        <span class="input-group-text">$</span>
                        <input
                            type="number"
                            class="form-control"
                            name="hourlyRate"
                            value="<%=gymCenter.getHourlyRate() != null ? gymCenter.getHourlyRate() : ""%>"
                            required
                            />
                    </div>
                </div>
                <div class="form-floating">
                    <textarea
                        class="form-control"
                        style="height: 120px"
                        name="description"
                        ><%=gymCenter.getDescription() != null ? gymCenter.getDescription() : ""%></textarea>
                    <label for="floatingTextarea2">Description</label>
                </div>
                    <button type="submit" class="btn btn-primary w-100 mt-3">
                        <%=gymCenter.getName() != null ? "Edit Gym Center" : "Create Gym Center"%>
                    </button> 
            </form>
        </div>

        <jsp:include page="./components/footer.jsp" />
        <script src="./js/header.js"></script>
    </body>
</html>