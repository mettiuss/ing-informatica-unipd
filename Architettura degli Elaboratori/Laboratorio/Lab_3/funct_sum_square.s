.text
@ Semplice funzione che calcola (r0+r1)^2 e salva il risultato in r0
sum_square: 
	add r2, r0, r1
	mul r0, r2, r2
	mov pc, lr

.global main
main:	mov r0, #5
	mov r1, #3
	bl sum_square
	nop
	mov pc,lr

