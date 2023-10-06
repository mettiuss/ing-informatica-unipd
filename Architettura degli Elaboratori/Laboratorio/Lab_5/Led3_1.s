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

	mov r2, r0
	bl read_slides

        cmp r2, #1   @ è premuto dx ?
	bleq write_led

        cmp r2, #2   @ è premuto sx?
	eoreq r0, r0, #0xF
	bleq write_led
 
 	mov r0, r1   @ inizializzo r0

        b loop

	@ Ripristina registri modificati
	pop {r0,r1,lr}
	@ Termina e ritorna dalla funzione main
	mov pc, lr


