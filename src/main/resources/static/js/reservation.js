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

    $.get("http://localhost:8080/reservation/availability?reservationDate="+ reservationDate +"&reservationType="+ reservationType +"&guestCount="+numberOfGuests)
        .done(function (data) {
            toast.fadeIn("slow");
            toastMessage.text("Check Complete: Reservation Available")
            toastMessage.css({
                "color": "#15c215"
            });

            setTimeout(function () {
                toast.fadeOut();
            }, 3000);
        })
        .fail(function (error) {

            if (error.status === 409) {
                toast.fadeIn("slow");
                toastMessage.text("Check Complete: Reservation Not Available")
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
})

$("#reservation-form").submit(function (event) {
    event.preventDefault();

    const toast = $("#simpleToast");
    const toastMessage = $("#toastMsg");

    $.ajax({
        url: "http://localhost:8080/reservation",
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
        .done(function () {
            toast.fadeIn("slow");
            toastMessage.text("Reservation Successful. ")
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
                toastMessage.text("Selected date is not available!")
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
})