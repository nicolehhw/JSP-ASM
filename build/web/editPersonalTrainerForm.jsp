<%@page import="ict.utils.Image"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="icon" href="./media/icon.png">
        <title>DGL | Edit Personal Trainer</title>
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
        <link rel="stylesheet" href="./css/commonStyle.css" />
        <link rel="stylesheet" href="./css/header.css" />
        <link rel="stylesheet" href="./css/footer.css" />
        <link rel="stylesheet" href="./css/editPersonalTrainerForm.css" />
    </head>
    <body>
        <jsp:useBean id="user" class="ict.bean.User" scope="session" />
        <jsp:useBean id="personalTrainer" class="ict.bean.PersonalTrainer" scope="request" />
        <jsp:include page="./components/header.jsp" />
        <div id="editPersonalTrainerForm" class="page-center-container">
            <form method="post" action="personalTrainer" enctype="multipart/form-data">
                <%if (request.getAttribute("registered") != null) {%>
                <div class="alert alert-danger" role="alert">
                    <%=request.getAttribute("registered")%>
                </div>
                <%}%>
                <input type="hidden" name="action" 
                       value="<%=personalTrainer.getName() != null ? "editPersonalTrainer" : "addPersonalTrainer"%>" />
                <%if (user.getRole().equals("personalTrainer")) {%>
                <input type="hidden" name="dispatch" 
                       value="./personalTrainer?action=getPersonalTrainerById&dispatch=personalTrainerProfile&id=<%=personalTrainer.getId()%>" />
                <%} else {%>
                <input type="hidden" name="dispatch" value="./personalTrainer?action=getAllPersonalTrainers" />
                <%}%>
                
                <input type="hidden" name="id" value="<%=personalTrainer.getId() != 0 ? personalTrainer.getId() : ""%>" />
                <%if(personalTrainer.getName() == null){%>
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
                <div class="form-group mb-3">
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
                <%}%>
                <div class="form-group mb-3">
                    <label class="mb-2">Status</label>
                    <select class="form-select" name="isEnabled" required>
                        <% if (personalTrainer.getIsEnabled() != null) {%>
                        <option selected hidden value="<%=personalTrainer.getIsEnabled()%>">
                            <%=personalTrainer.getIsEnabled() ? "Enabled" : "Disabled"%>
                        </option>
                        <%}%>
                        <option value="true">Enabled</option>
                        <option value="false">Disabled</option>
                    </select>
                </div>
                <div class="form-group mb-3">
                    <label class="mb-2">Personal Trainer Image</label>
                    <% if (personalTrainer.getImg() != null) {%>
                    <img src="<%=Image.covertBlobToImg(personalTrainer.getImg())%>" 
                         alt="personal-trainer-img" class="personal-trainer-img"
                         onerror="this.src='./media/defaultImg.png'"/>
                    <%}%>
                    <input type="file" class="form-control" name="image" />
                </div>
                <div class="form-group mb-3">
                    <label class="mb-2">Training Type</label>
                    <input type="text" class="form-control" name="trainingType" required
                           value="<%=personalTrainer.getTrainingType() != null ? personalTrainer.getTrainingType() : ""%>"/>
                </div>
                <div class="form-group mb-3">
                    <label class="mb-2">Hourly Rate</label>
                    <div class="input-group mb-3">
                        <span class="input-group-text">$</span>
                        <input
                            type="number"
                            class="form-control"
                            name="hourlyRate"
                            value="<%=personalTrainer.getHourlyRate() != null ? personalTrainer.getHourlyRate() : ""%>"
                            step="0.1"
                            required
                            />
                    </div>
                </div>
                <div class="form-floating">
                    <textarea
                        class="form-control"
                        style="height: 120px"
                        name="description"
                        ><%=personalTrainer.getDescription() != null ? personalTrainer.getDescription() : ""%></textarea>
                    <label for="floatingTextarea2">Description</label>
                </div>
                <button type="submit" class="btn btn-primary w-100 mt-3">
                    <%=personalTrainer.getName() != null ? "Edit Personal Trainer" : "Create Personal Trainer"%>
                </button>
            </form>
        </div>

        <jsp:include page="./components/footer.jsp" />
        <script src="./js/header.js"></script>
        <script>
            toastr.options = {
                "progressBar": true,
                "positionClass": "toast-top-right",
                "timeOut": "3000"
            };

            const params = new URLSearchParams(window.location.search).get("empty");
            if (params === "true") {
                toastr.error("Please upload image");
            }
        </script>
    </body>
</html>
