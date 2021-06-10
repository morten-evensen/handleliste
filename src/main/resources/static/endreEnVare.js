$(function () {
    const id = window.location.search.substring(0);
    const url = "/hentEnVare"+id;

    $.get(url, function (vare) {
        $("#id").val(vare.id);
        $("#vare").val(vare.vare);
        $("#antall").val(vare.antall);
    }).fail(function (jqXHR){
        const json = $.parseJSON(jqXHR.responseText);
        $("#feil").html(json.message);
    })
})

function endreEnVare() {
    const vare = {
        id : $("#id").val(),
        vare : $("#vare").val(),
        antall : $("#antall").val()
    }
    $.post("/endreEnVare", vare, function () {
        window.location.href = "index.html";
    }).fail(function (jqXHR){
        const json = $.parseJSON(jqXHR.responseText);
        $("#feil").html(json.message);
    })
}