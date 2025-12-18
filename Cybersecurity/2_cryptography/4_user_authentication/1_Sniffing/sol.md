# 🔑 Write-Up

L'hint suggerisce di usare wireshark

Inserisco il filtro HTTP per visualizzare solo il traffico che interessa a me,
ovvero il traffico HTTP

Le richieste utili: GET / HTTP/1.1 sono 2,
noto che la seconda richiesta all'interno del suo header
contiene l'header `Athorization`, il cui al suo interno
sono presenti le `Credentials: bashNinja:flag{help-me-obiwan}`

Ho trovato la flag cercata -> bashNinja:flag{help-me-obiwan}

### 🚩 Flag

```plain
bashNinja:flag{help-me-obiwan}
```