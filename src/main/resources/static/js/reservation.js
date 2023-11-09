$(document).ready(function () {
    $("#availabilityStatus").hide();
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
    const availabilityIndicator = $("#availabilityStatus");

    availabilityIndicator.hide();

    $.get("http://localhost:8080/api/reservation/availability?reservationDate="+ reservationDate +"&reservationType="+ reservationType +"&guestCount="+numberOfGuests)
        .done(function (data) {
            availabilityIndicator.show();
            availabilityIndicator.removeClass().addClass("availability-success");
            availabilityIndicator.text("Available")
        })
        .fail(function (error) {

            if (error.status === 409) {
                availabilityIndicator.show();
                availabilityIndicator.removeClass().addClass("availability-failed");
                availabilityIndicator.text("Not Available")
            } else {
                availabilityIndicator.show();
                availabilityIndicator.removeClass().addClass("availability-failed");
                availabilityIndicator.text("Not Enough Data")
            }
        })
})

$("#reservation-form").submit(function (event) {
    event.preventDefault();

    // TODO: Implement code to book a reservation
})