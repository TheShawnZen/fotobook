const recherche = (() => {
    var input, filter, ul, li, a, i, txtValue;
    input = document.getElementById('inputRecherche');
    filter = input.value.toUpperCase();
    ul = document.getElementById("listeRecherche");
    li = ul.getElementsByTagName('li');

    for (i = 0; i < li.length; i++) {
        a = li[i].getElementsByTagName("a")[0];
        txtValue = a.textContent || a.innerText;
        if (txtValue.toUpperCase().indexOf(filter) > -1) {
            li[i].style.display = "";
        } else {
            li[i].style.display = "none";
        }
    }
});

$("#inputRecherche").focusin(() => {
    $("#listeRecherche").css("display", "block");
    $("#listeRecherche").css("position", "absolute");
});

$(".contenu").on("click", () => {
    $("#listeRecherche").css("position", "relative");
    $("#listeRecherche").css("display", "none");
});