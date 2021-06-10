$(function () {
    hentAlle();
})

const ut = document.getElementById("ut");

function lagre() {
    let vareInn = document.getElementById("vare").value;
    let antallInn = document.getElementById("antall").value;

    const vare = {
        vare: vareInn,
        antall: antallInn
    };

    $.post("/lagre", vare, function () {
        hentAlle();
    }).fail(function (jqXHR){
        const json = $.parseJSON(jqXHR.responseText);
        $("#feil").html(json.message);
    })
}

function hentAlle() {
    $.get("/hentAlle", function (data) {
        formaterTekst(data);
    }).fail(function (jqXHR){
        const json = $.parseJSON(jqXHR.responseText);
        $("#feil").html(json.message);
    })
}

function formaterTekst(varer) {
    let utprint =
        "<table class='table table-striped'>" +
        "<tr></tr>" +
        "<th>Varer</th>" +
        "<th>Antall</th>" +
        "</tr>";

    for(let vare of varer) {

        utprint +=
            "<tr>" +
            "<td>" + vare.vare + "</td>" +
            "<td>" + vare.antall + "</td>" +
            "<td>" + vare.id + "</td>" +
            "<td> <a class='btn btn-primary' href='endreEnVare.html?id="+vare.id+"'>Endre vare</a></td>"+
            "<td><button class='btn btn-danger' onclick='slettEnVare("+vare.id+")'>Slett vare</button></td>" +
            "</tr>"
    }
    utprint += "</table>"
    ut.innerHTML = utprint;
}

function slettEnVare(id) {
    const url = "/slettEnVare?id="+id;
    $.get(url, function (){
        window.location.href = "index.html";
    }).fail(function (jqXHR){
        const json = $.parseJSON(jqXHR.responseText);
        $("#feil").html(json.message);
    })
}

function slettAlle() {
    $.get("/slettAlle", function () {
        ut.innerHTML = "";
        hentAlle();
    }).fail(function (jqXHR){
        const json = $.parseJSON(jqXHR.responseText);
        $("#feil").html(json.message);
    })
}