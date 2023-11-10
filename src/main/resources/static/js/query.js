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
        .done(function (data) {
            toast.fadeIn("slow");
            toastMessage.text(data)
            toastMessage.css({
                "color": "#15c215"
            });

            setTimeout(function () {
                toast.fadeOut();
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

    $("#customer-query :input").val("")
})