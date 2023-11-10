$(document).ready(function () {
    $("#simpleToast").hide();

    const loginLevel = localStorage.getItem("staffLevel");
    const staffLoggedIn = localStorage.getItem("loggedInStaff")

    if (!loginLevel || loginLevel === "2") {
        if (staffLoggedIn && staffLoggedIn === "true") {
            window.location.href = "/dashboard";
        } else {
            window.location.href = "/admin-sign-in";
        }
    }
})

$("#formAdminRegister").submit(function (event) {
    event.preventDefault();

    const toast = $("#simpleToast");
    const toastMessage = $("#toastMsg");

    $.ajax({
        url: "http://localhost:8080/register/staff",
        method: "POST",
        data: JSON.stringify({
            fullName: $("#fullName").val(),
            email: $("#email").val(),
            password: $("#password").val()
        }),
        contentType: "application/json",
    })
        .done(function (data) {
            toast.fadeIn("slow");
            toastMessage.text(data)
            toastMessage.css({
                "color": "#15c215"
            });

            setTimeout(function () {
                toast.fadeOut();
                // window.location.href = "/sign-in";
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