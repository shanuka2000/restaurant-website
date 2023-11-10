$(document).ready(function () {
    $("#simpleToast").hide();
    $(".customInput").on('input', function () {
        const inputValue = $(this).val();
        const outputId = $(this).data('output-id');
        const outputElement = $("#" + outputId);

        outputElement.text(inputValue === "" ? "n/a" : inputValue);
    })
})

$("#summary-form").submit(function (event) {
    event.preventDefault();

    const reservationDate = $("#iReservationDate").val();
    const numberOfGuests = $("#iNumPeople").val();
    const reservationType = $("#iReservationType").val();
    const toast = $("#simpleToast");
    const toastMessage = $("#toastMsg");

    $.get("http://localhost:8080/reservations/availability?reservationDate="+ reservationDate +"&reservationType="+ reservationType +"&guestCount="+numberOfGuests)
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
})

$("#reservation-form").submit(function (event) {
    event.preventDefault();

    const toast = $("#simpleToast");
    const toastMessage = $("#toastMsg");

    $.ajax({
        url: "http://localhost:8080/reservations",
        method: "POST",
        data: JSON.stringify({
            fullName: $("#iFullName").val(),
            contactNumber: $("#iContact").val(),
            email: $("#iEmail").val(),
            numberOfGuest: $("#iNumPeople").val(),
            reservationDate: $("#iReservationDate").val(),
            reservationType: $("#iReservationType").val(),
            specialRequests: $("#textarea").val(),
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
})