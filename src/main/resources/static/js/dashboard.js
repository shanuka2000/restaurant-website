function setReservationCount() {
    const today = new Date();

    const year = today.getFullYear();
    const month = today.getMonth() + 1;
    const day = today.getDate();

    const toast = $("#simpleToast");
    const toastMessage = $("#toastMsg");

    const formattedDate = `${year}-${month < 10 ? '0' + month : month}-${day < 10 ? '0' + day : day}`;


    $.get("http://localhost:8080/reservations/count?date="+formattedDate)
        .done(function (data) {
            console.log(data)
            let formattedNumber = data < 10 ? '0' + data : data;
            $("#reservationCount").text(formattedNumber);
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
}

function setUserCount() {
    $.get("http://localhost:8080/customer")
        .done(function (data) {
            let formattedNumber = data < 10 ? '0' + data : data;
            $("#totUsers").text(formattedNumber);
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
}

function setTicketCount() {
    $.get("http://localhost:8080/query/count")
        .done(function (data) {
            let formattedNumber = data < 10 ? '0' + data : data;
            $("#ticketCount").text(formattedNumber);
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
}

function getQueryList(displayAll) {
    $.get("http://localhost:8080/query")
        .done(function (data) {
            console.log(data)

            let counter = 0

            $.each(data, function (index, item) {

                if (!displayAll && counter >= 3) {
                    return false;
                }

                let queryItemHtml =
                    '<tr>' +
                        '<td>#' + item.id + '</td>' +
                        '<td>' + item.fullName + '</td>' +
                        '<td id="shortenText">' + item.message + '</td>' +
                    '</tr>'
                $("#queryBody").append(queryItemHtml);

                counter++;
            })
        })
        .fail(function (error) {
            console.log(error)
        })
}

function getReservations(displayAll) {
    $.get("http://localhost:8080/reservations")
        .done(function (data) {
            console.log(data)

            let counter = 0

            $.each(data, function (index, item) {

                if (!displayAll && counter >= 3) {
                    return false;
                }

                let reservationItemHtml =
                    '<tr>' +
                        '<td>#' + item.id +  '</td>' +
                        '<td>' + item.fullName + '</td>' +
                        '<td>' + item.contactNumber + '</td>' +
                        '<td>' + item.reservationType + '</td>' +
                        '<td>' + item.numberOfGuest + '</td>' +
                    '</tr>'

                $("#bookingBody").append(reservationItemHtml);

                counter++;
            })
        })
        .fail(function (error) {
            console.log(error)
        })
}

function getTodayReservations() {
    const today = new Date();

    const year = today.getFullYear();
    const month = today.getMonth() + 1;
    const day = today.getDate();

    const toast = $("#simpleToast");
    const toastMessage = $("#toastMsg");

    const formattedDate = `${year}-${month < 10 ? '0' + month : month}-${day < 10 ? '0' + day : day}`;

    $.get("http://localhost:8080/reservations/byDate?date="+formattedDate)
        .done(function (data) {
            console.log(data)

            $.each(data, function (index, item) {

                let reservationItemHtml =
                    '<tr>' +
                    '<td>#' + item.id +  '</td>' +
                    '<td>' + item.fullName + '</td>' +
                    '<td>' + item.contactNumber + '</td>' +
                    '<td>' + item.reservationType + '</td>' +
                    '<td>' + item.numberOfGuest + '</td>' +
                    '</tr>'

                $("#bookingBodyT").append(reservationItemHtml);
            })
        })
        .fail(function (error) {
            console.log(error)
        })
}

$(document).ready(function () {
    $("#simpleToast").hide();
    $(".user-details-wrapper").hide();

    const loginLevel = localStorage.getItem("staffLevel");
    const staffLoggedIn = localStorage.getItem("loggedInStaff")
    const email = localStorage.getItem("staffEmail");

    if (!staffLoggedIn) {
        window.location.href = "/admin-sign-in";
    }

    if (username) {
        const username = email.split('@')[0];
        const firstLetter = username.charAt(0);

        $("#username").text(username);
        $(".avatar").text(firstLetter.toUpperCase());
    }

    if (loginLevel) {
        if (loginLevel === "1") {
            $("#clearanceLevel").text("Admin");
            $("#lockedItem01, #lockedItem02").hide();
            getQueryList(true)
            getReservations(true);
        } else {
            $("#clearanceLevel").text("Staff");
            $(".register-wrapper").hide();
            getQueryList(false)
            getReservations(false);
            $(".query-wrapper, .bookings-wrapper").css({
                "opacity": "20%",
            })
            $("#resHisToday").css({
                "opacity": "100%",
            })
            $("#lockedItem01, #lockedItem02").show();
        }
    }

    setReservationCount();
    setUserCount();
    setTicketCount();
    getTodayReservations();
})

$("#profileBtn").click(function () {
    const profileDiv = $(".user-details-wrapper")
    if (profileDiv.is(':hidden')) {
        profileDiv.show();
    } else {
        profileDiv.hide();
    }
})

$("#signOut").click(function () {
    localStorage.removeItem("loggedInStaff");
    localStorage.removeItem("staffLevel");
    localStorage.removeItem("staffEmail")
    window.location.href = "/admin-sign-in";
})