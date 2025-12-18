
# Write Up

Lo script JS che gestisce l'handling del submit del form, presenta una funzione
che è stata offuscata tramite obfuscator.io.

Passando la funzione al deoffuscatore JS online, si ottiene una funzione leggibile:
```js
var _0x8d99=["","\x66\x72\x6F\x6D\x43\x68\x61\x72\x43\x6F\x64\x65","\x6C\x65\x6E\x67\x74\x68","\x73\x75\x62\x73\x74\x72"];
function x(_0x9aadx2,_0x9aadx3){var _0x9aadx4=[];var _0x9aadx5=_0x8d99[0];for(z=1;z<=255;z++){_0x9aadx4[String[_0x8d99[1]](z)]=z};
for(j=z=0;z<_0x9aadx2[_0x8d99[2]];z++){_0x9aadx5+=String[_0x8d99[1]](_0x9aadx4[_0x9aadx2[_0x8d99[3]](z,1)]^_0x9aadx4[_0x9aadx3[_0x8d99[3]](j,1)]);j=(j<_0x9aadx3[_0x8d99[2]])?j+1:0};
return _0x9aadx5}

function x(pass, key) {
  var str = [];
  var result = "";
  for (z = 1; z <= 255; z++) {
    str[String.fromCharCode(z)] = z;
  }
  ;
  for (j = z = 0; z < pass.length; z++) {
    result += String.fromCharCode(str[pass.substr(z, 1)] ^ str[key.substr(j, 1)]);
    j = j < key.length ? j + 1 : 0;
  }
  ;
  return result;
}
```

Sfrutto la proprietà `a ^ b = c <=> b ^ c = a` della XOR per ottenere la password
a partire dall'hash e la chiave: `_NeAM+bh_saaES_mFlSYYu}nYw} "6"`

Chiamando la funzione `x("_NeAM+bh_saaES_mFlSYYu}nYw}", "6")` ottengo la password
decriptata: `iNSA{+ThisWasSimpleYouKnow+}`

# Flag
```plain
iNSA{+ThisWasSimpleYouKnow+}
```