@ Calcola la differenza di due numeri

.text @ marca l'inizio del segmento con il codice 

.global main		@ definisce il punto di inizio main come global
main:	mov r3, #16	@ carica il primo operando
		mov r4, #3	@ carica il secondo operando
		sub r1, r3, r4	@ esegue la sottrazione

		mov pc, lr	@ ritorna il controllo al sistema operativo
