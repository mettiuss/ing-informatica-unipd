@ Calcola la differenza assoluta di due numeri.

.text	@ marca l'inizio del segmento con il codice 

.global main		@ definisce il punto di inizio main come global
main:	mov r1, #16	@ carica il primo operando
		mov r2, #3	@ carica il secondo operando
		mov r3, #-1	@ carica una costante (-1)
		subs r0, r1, r2	@ esegue la sottrazione
		mulmi r0, r0, r3@ inverte il segno se la differenza è negativa

		mov pc, lr	@ ritorna il controllo al sistema operativo
