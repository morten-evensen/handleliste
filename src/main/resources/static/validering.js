function validerVare(vare) {
    const regexp = /^[a-zA-ZæøåÆØÅ. \-]{1,20}$/;
    const ok = regexp.test(vare)
    if(!ok) {
        $("#vareError").html("Varenavnet må bestå av 1 til 20 bokstaver");
        return false;
    } else {
        $("#vareError").html("");
        return true;
    }
}

function validerAntall(antall) {
    const regexp = /^[0-9]{1,20}$/;
    const ok = regexp.test(antall)
    if(!ok) {
        $("#antallError").html("Antallet må ha et siffer");
        return false;
    } else {
        $("#antallError").html("");
        return true;
    }
}