$(document).ready(function () {
    $("#simpleToast").hide();
})

$("#customerSignUp").submit(function (event) {
    event.preventDefault();

    const toast = $("#simpleToast");
    const toastMessage = $("#toastMsg");

    $.ajax({
        url: "http://localhost:8080/register/customer",
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
            toastMessage.text("Sign in successful")
            toastMessage.css({
                "color": "#15c215"
            });

            setTimeout(function () {
                toast.fadeOut();
                window.location.href = "/sign-in";
            }, 3000);
        })
        .fail(function (error) {
            toast.fadeIn("slow");
            toastMessage.text("Something went wrong. Please try again")
            toastMessage.css({
                "color": "#bd1b1b"
            });

            setTimeout(function () {
                toast.fadeOut();
            }, 3000);
        })
})