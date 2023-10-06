
.bss
@ subroutine da utilizzare
@ set_gpio : inizializza la GPIO
@ read_slides : leggere il valore impostato nei 4 switch e lo restituisce nei 4 bit meno significativi in R0
@ write_led : visualizza nei led i 4 bit meno significativi di R0


.text
.global main
main: 
	@ inizializza la GPIO
	bl set_gpio

loop:
	@ legge il valore degli switch
	bl read_slides

	@ e lo visualizza nei led
	bl write_led

        b loop

	@ Termina e ritorna dalla funzione main
	mov pc, lr


