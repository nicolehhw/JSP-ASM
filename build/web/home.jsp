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
        <!-- import AOS -->
        <link href="https://unpkg.com/aos@2.3.1/dist/aos.css" rel="stylesheet">
        <script src="https://unpkg.com/aos@2.3.1/dist/aos.js"></script>
        <!-- import animate -->
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css"
            />
        <link rel="stylesheet" href="./css/commonStyle.css" />
        <link rel="stylesheet" href="./css/header.css" />
        <link rel="stylesheet" href="./css/footer.css" />
        <link rel="stylesheet" href="./css/home.css" />
    </head>
    <body>
        <jsp:include page="./components/header.jsp" />
        <section id="hero">
            <div class="content">
                <h2 class="animate__animated animate__backInDown">Your Best Choice</h2>
                <h1 class="animate__animated animate__lightSpeedInLeft animate__delay-1s"><span class="orange">Dream</span> Gym Limited</h1>
            </div>
            <video autoplay muted loop>
                <source src="./media/video.mp4" type="video/mp4" />
            </video>
        </section>

        <section id="services">
            <h2 class="title">Services</h2>
            <div class="service-box-container">
                <a  href="./gymCenter?action=getGymCenterByEnabled" class="service-box" data-aos="fade-down">
                    <i class="fa-solid fa-dumbbell"></i>
                    <h3>Rent Gym Center</h3>
                </a>
                <a href="./personalTrainer?action=getPersonalTrainerByEnabled" class="service-box" data-aos="fade-up">
                    <i class="fa-solid fa-people-carry-box"></i>
                    <h3>personal trainers' 1-on-1 coaching</h3>
                </a>
            </div>
        </section>
        <jsp:include page="./components/footer.jsp" />
        <script src="./js/header.js"></script>
        <script>
            AOS.init();
        </script>
    </body>
</html>
