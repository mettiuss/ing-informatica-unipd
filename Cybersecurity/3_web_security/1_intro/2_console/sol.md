
# Write Up

controllando il codice sorgente della pagina, trovo lo script che gestisce il submit
del input box:

```js
var foo = document.getElementById("p");

function what() {
    var input = document.getElementById("pass").value;
    if (md5(input) == "7b1ece53a46f4a5a2995b9cf901bf457") {
        getThat('Y');
    } else {
        getThat('N')
    }
}
function getThat(string) {
    if (string == 'Y') {
        $.ajax({
            type: 'GET',
            url: '1/key.php',
            success: function(file_html) {
                // success
                foo.innerHTML = (file_html)
            }
        });
    } else {
        foo.innerHTML = "Nope";

    }
}
...
```

Una volta eseguito il submit, lo script effettua il check della password (hashata con md5),
se il check ha successo chiama getThat('Y'), altrimenti chiama getThat('N').

Prendo la funzione getThat(string) e la modifico in:
```js
function getThat(string) {
    if (string == 'Y' || true) {
        $.ajax({
            type: 'GET',
            url: '1/key.php',
            success: function(file_html) {
                // success
                foo.innerHTML = (file_html)
            }
        });
    } else {
        foo.innerHTML = "Nope";

    }
}
```

Una volta inviata questa funzione sulla console, sovrascrivo quella sul sorgente della pagina,
quindi ad ogni submit successivo chiamerà la mia funzione, che indipendentemente dall'input stringa
che riceve, esegue sempre la funzione `success`, ottendendo così la flag dal server

# Flag

```plain
flag{console_controls_js}
```