# Writeup

Il programma debugmenot implementa diverse tecniche antidebug:
- ptrace
- vdso
- noasrlr
- env
- parent
- ldhook
- nearheap
Deassemblando il programma con IDA, noto che i risultati dei test vengono presi
tramite la funzione `call qword ptr [rbx]`, la quale inserisce il valore del
test nel registro `eax`.
Per forzare il passaggio dei test e' sufficiente sostituire la quella chiamata
a funzione con `xor eax, eax`, la quale resituira' sempre il valore 0,
permettendo il passaggio dei test.

Le modifiche applicate sono scritte sul file `patch.diff`

# Flag
```plain
No flag in this challenge
```