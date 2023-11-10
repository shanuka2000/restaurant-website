$(document).ready(function () {
    const loggedIn = localStorage.getItem("loggedIn");

    if (loggedIn) {
        $(".userAuthed").show();
        $(".nav-user-opt").hide();
        console.log("Has logged in details")
    } else {
        $(".userAuthed").hide()
        $(".nav-user-opt").show();
        console.log("No logged in details")
    }
})