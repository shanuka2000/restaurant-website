$(document).ready(function () {
    $("#availabilityStatus").hide();
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
    const availabilityIndicator = $("#availabilityStatus")
    const toast = $("#simpleToast");
    const toastMessage = $("#toastMsg");

    availabilityIndicator.hide();

    $.get("http://localhost:8080/api/reservation/availability?reservationDate="+ reservationDate +"&reservationType="+ reservationType +"&guestCount="+numberOfGuests)
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
                toastMessage.text("Request rejected by server")
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


})