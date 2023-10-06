

@ Costanti per gli esercizi del laboratorio
        .equ    GPIO_MEM,0xF00000	@ inizio area di memoria con mappatura GPIO
	.equ	GPIO_SEL0,GPIO_MEM+0x00	@ indirizzo registro SEL0
	.equ	GPIO_SEL1,GPIO_MEM+0x04	@ indirizzo registro SEL1
	.equ	GPIO_SEL2,GPIO_MEM+0x08	@ indirizzo registro SEL2
	.equ	GPIO_SEL3,GPIO_MEM+0x0C	@ indirizzo registro SEL3
	.equ	GPIO_SEL4,GPIO_MEM+0x10	@ indirizzo registro SEL4
	.equ	GPIO_SEL5,GPIO_MEM+0x14	@ indirizzo registro SEL5

	.equ	GPIO_SET0,GPIO_MEM+0x1C	@ indirizzo registro SET0
	.equ	GPIO_SET1,GPIO_MEM+0x20	@ indirizzo registro SET1
	.equ	GPIO_CLR0,GPIO_MEM+0x28	@ indirizzo regisstro CLR0
	.equ	GPIO_CLR1,GPIO_MEM+0x2C	@ indirizzo regisstro CLR1
	.equ	GPIO_LEV0,GPIO_MEM+0x34	@ indirizzo regisstro LEV0
	.equ	GPIO_LEV1,GPIO_MEM+0x38	@ indirizzo regisstro LEV1
	
@ Costanti per definire l'apertura del file /dev/gpiomem; 
@ I valori sono specificati in ottale (i numeri iniziano con 0)
        .equ    O_FLAGS,  04010002	@ apri il file in lettura/scrittura e in modalita' sincrona
@ Costanti per definire la memoria virtuale
        .equ    PROT_RDWR,0x3   @ apri la pagina in modalita' lettura/scrittura
        .equ    PAGE_SIZE,4096  @ dimensione pagina in Raspberry 
        .equ    MAP_SHARED,0x01 @ la pagina è condivsa

@ Costanti per mappare i registri GPIO in memoria virtuale
        .equ    PERIPH,0x3f000000	@ indirizzo di inizio nella memoria fisica per le periferiche
        .equ    GPIO_OFFSET,0x200000  	@ offset per l'inizio dell'area periferiche riservate a GPIO


@ Dati del progrmma
	.data
/*A string required by function print_int*/
str_print_int: .asciz "%d\n"

device:	
	.asciz  "/dev/gpiomem"		@ url del file da aprire
deviceAddr: 
	.word   device			@ indirizzo della stringa con l'url del file
openMode: 
	.word   O_FLAGS			@ word con i parametri di apertura del file
gpio:	
	.word   PERIPH+GPIO_OFFSET	@ word con l'indirizzo della memoria che mappa il GPIO

debounce_time:	.word 0
handler_adr:	.word 0

string: .asciz "time %d\n"
string4: .asciz "gap %d\n"
string2: .asciz "time old %d\n"
string3: .asciz "all\n"
  .align  4
count:	.word 0

@ Funzioni per gestire I/O via GPIO
        .text
        .align  4

.global read_int
read_int:
	push {r1-r12, lr}
	sub sp, sp, #4
	mov r1, sp
	bl  scanf
	ldr r0, [sp]
	add sp, sp, #4
	pop {r1-r12, lr}
	mov pc, lr


/* A function to end a program and to return the control to the operating system.*/
.global exit_program
exit_program:
    /* syscall exit(int status) */
    mov     r0, #0     /* status := 0 */
    mov     r7, #1     /* exit is syscall #1 */
    swi     #0          /* invoke syscall */

/* A function to print in the output standard an
 integer stored in register R0. */
.global print_int
print_int:
	push {r0-r12, lr}
	mov r1, r0
	ldr r0, =str_print_int
	bl printf
	pop  {r0-r12, lr}
	mov pc, lr

  .align  4

@ set_gpio: mappa gpiomem nella memoria virtuale
        .global set_gpio
        .type   set_gpio, %function
set_gpio:
	push 	{fp, lr}	@ creo nuovo framw
	mov	fp, sp
	push	{r0-r5}		@ salvo registri usati
	

@ Apre file /dev/gpiomem in lettura/scrittura e sincronizzato
@ Preparo input della funzione open
	ldr 	r0, =deviceAddr
	ldr     r0, [r0]  	@ indirizzo dell'url /dev/gpiomem
	ldr	r1, =openMode
        ldr     r1, [r1]    	@ indirizzo con paramtri di apertura
        bl      open		@ invoco open dalle librerie standard
        mov     r1, r0          @ metto il file descriptor (outout di open in r0) on r1
	
@ Mappo i registri GPIO nella memoria virtuale all'indirizzo GPIO_MEM
@ Preparo i sei input di mmap (4 input dei registri, 2 input nello stack)        
	ldr 	r0, =gpio
	ldr     r0, [r0]        @ indirizzo memoria fisica dei registri GPIO 
        push	{r0}		@ inserisco nello stack l'indirizzo fisico
	push	{r1}		@ descrittore del file /dev/gpiomem nello stack
        mov     r0, #GPIO_MEM   @ indirizzo dei registri nella memoria virtuale
  @ set starting point in  mem
        mov     r1, #PAGE_SIZE  @ carico solo una pagina di memoria
        mov     r2, #PROT_RDWR  @ memoria in lettura/scrittura
        mov     r3, #MAP_SHARED @ memoria condivisa con altri processi
        bl      mmap		@ invoco mmap dalla libreria standard
	add	sp, sp, #8	@ svuoto lo stack di due elementi
        
	pop	{r0-r5}		@ ripristino registri
	pop	{fp, lr}	@ ripristino fp, lr
	mov	pc, lr		@ esci




@ write_number: scrive una cifra esadecimale su display a 7 segmenti
@ assume che la funzione set_gpio sia gia' stata chiamata
        .global write_number
        .type   write_number, %function


@ costanti per funzione write_number
	.equ RESET_SEGMENTS,07777777700
	.equ SET_SEGMENTS,01111111100	@ Imposta i pin BCM 2-9 come output; in ottale
	.equ SHIFT_SEGMENTS, 2
	.equ N0, 0b00111111
	.equ N1, 0b00000110
	.equ N2, 0b01011011
	.equ N3, 0b01001111
	.equ N4, 0b01100110
	.equ N5, 0b01101101
	.equ N6, 0b01111101
	.equ N7, 0b00000111
	.equ N8, 0b01111111
	.equ N9, 0b01101111
	.equ NA, 0b01110111
	.equ NB, 0b01111100
	.equ NC, 0b00111001
	.equ ND, 0b01011110
	.equ NE, 0b01111001
	.equ NF, 0b01110001
	.equ NZ, 0b00000000
	.equ NN, 0b10000000
@ Vettore con le codifiche
codici: .word N0, N1, N2, N3, N4, N5, N6, N7, N8, N9, NA, NB, NC, ND, NE, NF, NZ, NN


write_number:
	push 	{fp, lr}	@ creo nuovo framw
	mov	fp, sp
	push	{r0-r5}		@ salvo registri usati
	
	ldr r1, =RESET_SEGMENTS
	ldr r2, =GPIO_SEL0
	ldr r3, [r2]
	orr r3, r3, r1
	ldr r1, =SET_SEGMENTS
	and r3, r3, r1
	str r3, [r2]
	
	and r0, r0, #0xF
	ldr r1, =codici
	ldr r1, [r1, r0, lsl #2]
	lsl r1, r1, #SHIFT_SEGMENTS
	ldr r2, =GPIO_SET0
	str r1, [r2]	
	ldr r2, =GPIO_CLR0
	mvn r3, #0
	bic r3, r3, r1
	and r3, r3, #0x3FC
	str r3, [r2]
 
	pop	{r0-r5}		@ ripristino registri
	pop	{fp, lr}	@ ripristino fp, lr
	mov	pc, lr		@ esci


@ read_slides: legge il valore dei 4 bottoni a scorrimento
@ assume che la funzione set_gpio sia gia' stata chiamata
        .global read_slides
        .type   read_slides, %function
	
	.equ SET_SLIDES, 077770000
	.equ MASK_SLIDES, 0b111100000000000000
	.equ SHIFT_SLIDES, 14
read_slides:	
	push 	{fp, lr}	@ creo nuovo frame
	mov	fp, sp
	push	{r1-r5}		@ salvo registri usati
	
	ldr r1, =SET_SLIDES
	ldr r2, =GPIO_SEL1
	ldr r3, [r2]
	bic r3, r3, r1
	str r3, [r2]
	ldr r1, =GPIO_LEV0
	ldr r0, [r1]
	ldr r2, =MASK_SLIDES
	and r0, r0, r2
	mov r0, r0, lsr #SHIFT_SLIDES
	pop	{r1-r5}		@ ripristino registri
	pop	{fp, lr}	@ ripristino fp, lr
	mov	pc, lr		@ esci



@ read_buttons: legge il valore dei 2 bottoni a pressione
@ assume che la funzione set_gpio sia gia' stata chiamata
        .global read_buttons
        .type   read_buttonss, %function
	
	.equ SET_BUTTONS, 077
	.equ MASK_BUTTONS, 0x300000
	.equ SHIFT_BUTTONS, 20
read_buttons:	
	push 	{fp, lr}	@ creo nuovo frame
	mov	fp, sp
	push	{r1-r5}		@ salvo registri usati
	
	ldr r1, =SET_BUTTONS
	ldr r2, =GPIO_SEL2
	ldr r3, [r2]
	bic r3, r3, r1
	str r3, [r2]

	ldr r1, =GPIO_LEV0
	ldr r0, [r1]
	ldr r2, =MASK_BUTTONS
	and r0, r0, r2
	mov r0, r0, lsr #SHIFT_BUTTONS
	push 	{r0}
	@debouncing
	mov r0, #1
	bl sleep
	pop 	{r0}
	pop	{r1-r5}		@ ripristino registri
	pop	{fp, lr}	@ ripristino fp, lr
	mov	pc, lr		@ esci



@ write_led: scrive un valore sui 4 led passati nel registro r0
@ assume che la funzione set_gpio sia gia' stata chiamata
        .global write_led
        .type   write_led, %function
	
	.equ LED_SET, 01111
	.equ LED_RESET, 07777
	.equ LED_SHIFT, 10
write_led:	
	push 	{fp, lr}	@ creo nuovo frame
	mov	fp, sp
	push	{r1-r5}		@ salvo registri usati
	
	ldr r1, =LED_RESET
	ldr r2, =GPIO_SEL1
	ldr r3, [r2]
	orr r3, r3, r1
	ldr r1, =LED_SET
	and r3, r3, r1
	str r3, [r2]
	
	and r0, #0xF
	lsl r1, r0, #LED_SHIFT 	
	ldr r2, =GPIO_SET0
	str r1, [r2]	
	ldr r2, =GPIO_CLR0
	mvn r3, #0
	bic r3, r3, r0
	and r3, r3, #0xF
	lsl r3, r3, #LED_SHIFT
	str r3, [r2]
 
	pop	{r1-r5}		@ ripristino registri
	pop	{fp, lr}	@ ripristino fp, lr
	mov	pc, lr		@ esci


@ Funzione set_led: imposta i 4 led usando una word all'indirizzo puntato
@   dall'indirizzo nel registro r0 .global set_led
@ Input: l'indirizzo della word contentente i 4 bit con lo stato dei led (4 bit
@   meno significativi). L'i-esimo bit indica lo stato del i-esimo led (da destra).
@ Output: accende i 4 led utilizzando i 4 bit (bit a 1: led acceso; bit a 0: led spento).  
.global set_led
set_led:
	push {r0,lr}
@	bl set_gpio	@ Prepara l'ambiente gpio
	ldr r0, [r0]	@ Carica la word dall'indirizzo dato in input
	bl write_led	@ Attiva led
	pop {r0,lr}
	mov pc, lr



.global handler_adr

interrupt_start:
	push {r0,r1,lr}
	@bl gpioInitialise
	bl wiringPiSetup
	bl millis
	ldr r1, =debounce_time
	str r0, [r1]	
	pop {r0,r1,lr}
	mov pc, lr

interrupt_handler:
	push {r3-r4,lr}
	ldr r4, =handler_adr
	str r2, [r4]
	mov r2, #0
	ldr r2, =handler
	bl wiringPiISR
	pop {r3-r4,lr}
	mov pc, lr

handler:
	push {r0-r3,lr}
	push {r0}
	bl millis
	ldr r2, =debounce_time
	ldr r1, [r2]
	str r0, [r2]
	sub r1, r0, r1
	cmp r1, #200
	pop {r0}
	bls end_handler
	ldr r1, =handler_adr
	ldr lr, =end_handler
	ldr pc, [r1]

end_handler:
	pop {r0-r3,lr}
	mov pc, lr

increment_button:
	push {r0-r2,lr}
	ldr r2, =count
	ldr r1, [r2]
	cmp r0, #21
	addeq r1, r1, #1
	cmp r0, #20
	subeq r1, r1, #1
	str r1, [r2]
	mov r0, r1
	bl write_number
	pop {r0-r2,lr}
	mov pc, lr


@ Funzione rand_word: Restituisce un valore intero senza segno random nel range [0, 2^32-1] 
@ Input: nessun parametro
@ Output: un valore random nel registro R0
.global rand_word
rand_word:
        push {lr}	
        mov r0, #0	@ Prepara input per time (0 equivale a null)
        bl time		@ Ottiene il numero di secondi dal 1970 per usarlo come seed
        bl srand	@ Il seed si trova in r0 e viene impostato il generatore random
        bl rand		@ Scrive in r0 un numero random
        pop {lr}	@ Ritorna con il valore random in r0
        mov pc, lr
