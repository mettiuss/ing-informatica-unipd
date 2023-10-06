@ Calcola la somma di due numeri.

.text	@ marca l'inizio del segmento con il codice 

.global main		@ definisce il punto di inizio main come global
main:	mov r1, #10	@ carica il primo operando
		mov r2, #15	@ carica il secondo operando
		add r0, r1, r2	@ esegue la somma
	
		mov pc, lr	@ ritorna il controllo al sistema operativo
