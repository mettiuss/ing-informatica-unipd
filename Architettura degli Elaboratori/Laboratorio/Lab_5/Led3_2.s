.bss

@ subroutine a disposizione
@ set_gpio : inizializza la GPIO
@ read_slides : leggere il valore impostato nei 4 switch e lo restituisce nei 4 bit meno significativi in R0
@ write_led : visualizza nei led i 4 bit meno significativi di R0
@ read_buttons : legge lo stato dei 2 pulsani e lo restituisce nei 2 bit meno significativi di R0.
@                 ogni bit corrisponde allo stato di un pulsante.


.text

.global main
main: 
	@ Salva i registri modificati
	push {r0,r1,lr}
	
	@ inizializza la GPIO
	bl set_gpio
	
	mov r1, #1  @ valore iniziale led

loop:   bl read_buttons  @ leggo lo stato dei pulsanti
        cmp r0, #1   @ è premuto dx ?
        moveq   r1, r1, ror #1      @ si: ruota verso destra
	cmp r1, #0x80000000
	moveq r1, #0x8
        cmp     r0, #2   @ è premuto sx?
	moveq   r1, r1, ror #31     @ si: ruota verso sinistra
	cmp r1, #0x10
	moveq r1, #1

 	mov r0, r1   @ inizializzo r0

	bl write_led @ aggiorno i led

        b loop

	@ Ripristina registri modificati
	pop {r0,r1,lr}
	@ Termina e ritorna dalla funzione main
	mov pc, lr

