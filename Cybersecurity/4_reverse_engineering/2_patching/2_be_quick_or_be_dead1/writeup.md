# writeup

Dal momento che il programma fornisce una chiave solo se essa viene
generata entro un tot. di secondi, controllo tramite IDA se esso avvia qualche
timer. Effettivamente viene chiamata una fuzione `set_timer` che allo
scadere tel tempo da un errore e termina il programma.
Patcho il programma mettero delle `nop` al posto della funzione di avvio del timer,
in modo che esso possa eseguire all' infinito.
La funzione main risultante e':
```asm
; Attributes: bp-based frame

; int __fastcall main(int argc, const char **argv, const char **envp)
public main
main proc near

var_10= qword ptr -10h
var_4= dword ptr -4

; __unwind {
push    rbp
mov     rbp, rsp
sub     rsp, 10h
mov     [rbp+var_4], edi
mov     [rbp+var_10], rsi
mov     eax, 0
call    header
nop
nop
nop
nop
nop
nop
nop
nop
nop
nop
mov     eax, 0
call    get_key
mov     eax, 0
call    print_flag
mov     eax, 0
leave
retn
; } // starts at 400827
main endp
```

I Bytes cambiati sono segnati in `patch.diff`

# Flag
```plain
picoCTF{why_bother_doing_unnecessary_computation_27f28e71}
```