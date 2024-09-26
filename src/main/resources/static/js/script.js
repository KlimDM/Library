function showBookDetails(id) {
    window.location.href = "/book?id=" + id;
}

function showSearchResult() {
    var inputField = document.getElementById("keyword");
    var inputValue = inputField.value
    window.location.href = "/search?keyword=" + inputValue;
}