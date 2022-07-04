
function getViewData() {
    const Http = new XMLHttpRequest();
    const url = 'http://localhost:9000/view_data'
    Http.open("GET", url);
    Http.send();

    Http.onreadstatechange = (e) => {
        console.log(Http.requestText);
    }
}

getViewData()