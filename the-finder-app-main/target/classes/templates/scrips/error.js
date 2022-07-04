
function setError () {
    const queryString = window.location.search;
    let error_message = "Username or password incorrect";
    let error_param = queryString.split("=");
    if (error_param[0] == '?error' && error_param[1] == 'true') {
        var paragraph = document.getElementById("loginerror");
        paragraph.style.display = "block";
        paragraph.style.color = "red";
    } else {
        var paragraph = document.getElementById("loginerror");
        paragraph.style.display = "none";
    }
}

setError()