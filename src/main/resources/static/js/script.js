function showBookDetails(id) {
    window.location.href = "/book?id=" + id + "&" + "editMode=false";
}

function showSearchResult() {
    var inputField = document.getElementById("keyword");
    var inputValue = inputField.value
    window.location.href = "/search?keyword=" + inputValue;
}