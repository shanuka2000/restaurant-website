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
        $(".avatar").text(firstLetter);
    }

    if (loginLevel) {
        if (loginLevel === "1") {
            $("#clearanceLevel").text("Admin");
        } else {
            $("#clearanceLevel").text("Staff");
        }
    }

    setReservationCount();
    setUserCount();
    setTicketCount();
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