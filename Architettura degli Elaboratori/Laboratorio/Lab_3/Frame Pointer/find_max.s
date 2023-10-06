.text
.global find_max

@ Input:
@   * r0: indirizzo in memoria del vettore di interi con segno;
@   * r1: numero di elementi del vettore;

@ Output:
@   * r0: il valor massimo del vettore

find_max:
	@@@ istruzioni della funzione max
	push {r1-r3, lr}
	ldr r0, [fp, #16]
	ldr r1, [fp, #12]
	mov r2, r0
	ldr r0, [r0]
loop:	ldr r3, [r2], #4
	cmp r3, r0
	movgt r0, r3
	sub r1, r1, #1
	cmp r1, #0
	bgt loop
	pop {r1-r3, lr}
  	mov pc, lr   @ ritorna alla funzione chiamante.
	