$(document).ready(function () {
    $(".customInput").on('input', function () {
        const inputValue = $(this).val();
        const outputId = $(this).data('output-id');
        const outputElement = $("#" + outputId);

        outputElement.text(inputValue === "" ? "n/a" : inputValue);
    })
})

$("#check-avb").submit(function (event) {
    event.preventDefault();

    const reservationDate = $("#iReservationDate").val();
    const numberOfGuests = $("#iNumPeople").val();

    // TODO: Implement code to check availability
})

$("#reservation-form").submit(function (event) {
    event.preventDefault();

    // TODO: Implement code to book a reservation
})