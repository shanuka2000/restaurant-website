$(document).ready(function () {
    $("#simpleToast").hide();

    const staffLoggedIn = localStorage.getItem("loggedInStaff")

    if (staffLoggedIn && staffLoggedIn === "true") {
        window.location.href = "/dashboard";
    }
})

$("#adminSignIn").submit(function (event) {
    event.preventDefault();

    const toast = $("#simpleToast");
    const toastMessage = $("#toastMsg");

    const email = $("#email").val();
    const password = $("#password").val();
    const level = $("#staffLevel").val();

    let cusUrl;

    if (level === "1") {
        cusUrl = "http://localhost:8080/auth/admin?email="+ email +"&password="+ password;
    } else {
        cusUrl = "http://localhost:8080/auth/staff?email="+ email +"&password="+ password;
    }

    $.ajax({
        url: cusUrl,
        method: "POST",
        contentType: "application/json",
    })
        .done(function (data) { // 1 = admin, 2 = staff
            toast.fadeIn("slow");
            toastMessage.text(data)
            toastMessage.css({
                "color": "#15c215"
            });

            setTimeout(function () {
                toast.fadeOut();
                localStorage.setItem("loggedInStaff", "true")
                localStorage.setItem("staffLevel", level.toString());
                localStorage.setItem("staffEmail", email);
                window.location.href = "/dashboard";
            }, 3000);
        })
        .fail(function (error) {
            toast.fadeIn("slow");
            toastMessage.text(error)
            toastMessage.css({
                "color": "#bd1b1b"
            });

            setTimeout(function () {
                toast.fadeOut();
            }, 3000);
        })
})