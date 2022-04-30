$(document).ready(function () {
    // scroll to change header color
    $(window).on("scroll", function () {
        if ($(window).scrollTop() > 10) {
            $("#header").addClass("active");
        } else {
            $("#header").removeClass("active");
        }
    })
    // show navbar menu 
    $("#header .menu-toggle-btn, #header .close-menu-btn").click(function () {
        $("#header .menu").toggleClass("active");
    })
})


