$(document).ready(function () {
    $("#simpleToast").hide();
})

$("#customerSignIn").submit(function (event) {
    event.preventDefault();

    const toast = $("#simpleToast");
    const toastMessage = $("#toastMsg");

    $.ajax({
        url: "http://localhost:8080/auth/customer",
        method: "POST",
        data: JSON.stringify({
            email: $("#email").val(),
            password: $("#password").val(),
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
                localStorage.setItem("loggedIn", "true")
                window.location.href = "/";
            }, 3000);
        })
        .fail(function (error) {
            toast.fadeIn("slow");
            toastMessage.text("Email or Password Incorrect!")
            toastMessage.css({
                "color": "#bd1b1b"
            });

            setTimeout(function () {
                toast.fadeOut();
            }, 3000);
        })

})