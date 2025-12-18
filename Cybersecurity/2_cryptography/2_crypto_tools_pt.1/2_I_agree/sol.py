'''
    Dall'hint ottenuto da "caesar is everything. But he took it to the next level."
    con livello superiore posso pensare ci si possa riferire ad un'evoluzione del cifrario di Cesare,
    ovvero il cifrario di Vigenère.
    Infatti, il cifrario di Vigenère utilizza una parola chiave per determinare gli spostamenti delle lettere,
    rendendolo più complesso rispetto al semplice cifrario di Cesare.
'''

ciphered_text = "vhixoieemksktorywzvhxzijqni"
key = "caesar"

shift = []
for c in key:
    shift.append(ord(c) - ord('a'))

print(f"Shift values from key '{key}': {shift}")

flag = ""

for i in range(len(ciphered_text)):

    ascii = ord(ciphered_text[i]) - shift[i % len(key)]

    if ascii >= ord('a'):
        flag += chr(ascii)
    else:
        diff = ord('a') - ascii - 1
        flag += chr(ord('z') - diff)
        
print(f"The flag is: {flag}")