.data
	addr_n: .word 15	@ Word in memoria con il valore di n 

.bss
	addr_sum: .skip 4	@ Word in memoria dove salvare il valore finale

.text
	.global main
main:
	MOV R0, #0
	MOV R1, #-5
	LDR R2, =addr_n
	LDR R3, [R2]
loop:	
	MUL R4, R1, R1
	ADD R0, R0, R4
	BL print_int
	ADD R1, R1, #1
	CMP R1, R3
	BLE loop

	LDR R5, =addr_sum
	STR R0, [R5]

   	bl exit_program
