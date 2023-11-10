$(document).ready(function () {
    $("#simpleToast").hide();
})

$("#customer-query").submit(function (event) {
    event.preventDefault();

    const toast = $("#simpleToast");
    const toastMessage = $("#toastMsg");

    $.ajax({
        url: "http://localhost:8080/query",
        method: "POST",
        data: JSON.stringify({
            fullName: $("#username").val(),
            email: $("#email").val(),
            message: $("#message").val()
        }),
        contentType: "application/json",
    })
        .done(function () {
            toast.fadeIn("slow");
            toastMessage.text("Query Submitted. ")
            toastMessage.css({
                "color": "#15c215"
            });

            setTimeout(function () {
                toast.fadeOut();
            }, 3000);
        })
        .fail(function (error) {
            if (error.status === 422) {
                toast.fadeIn("slow");
                toastMessage.text("Query Submit Failed")
                toastMessage.css({
                    "color": "#bd1b1b"
                });

                setTimeout(function () {
                    toast.fadeOut();
                }, 3000);
            } else {
                toast.fadeIn("slow");
                toastMessage.text("Internal server error occurred. Please contact the administrator.")
                toastMessage.css({
                    "color": "#bd1b1b"
                });

                setTimeout(function () {
                    toast.fadeOut();
                }, 3000);
            }
        })

    $("#customer-query :input").val("")
})