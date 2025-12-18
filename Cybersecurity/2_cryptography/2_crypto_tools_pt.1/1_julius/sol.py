import base64

hint = "Q2Flc2FyCg=="
b64_decoded_hint = base64.b64decode(hint).decode('utf-8').strip()
print(f"Hint: {b64_decoded_hint}")

'''
    L'hint ottenuto decodificando la stringa Base64 è "Caesar".
    Potrebbe riferirsi al cifrario di Cesare, un semplice metodo di crittografia.
'''

ciphertext = "fYZ7ipGIjFtsXpNLbHdPbXdaam1PS1c5lQ=="
b64_decoded_ciphertext = base64.b64decode(ciphertext).decode('utf-8', errors='ignore').strip()
print(f"Base64 Decoded Ciphertext: {b64_decoded_ciphertext}")

def bruteForceCaesar(text):
    for shift in range(-30, 30):
        decrypted = ''.join([chr(ord(c) + shift) for c in text])
        print(f"Shift {shift}: {decrypted}")

bruteForceCaesar(b64_decoded_ciphertext)

'''
    Dopo il brute force al cifrario di Cesare effettuato sulla stringa puzzle decodificata da Base64,
    con un offset di -24 si ottiene una flag leggibile.
    La flag ottenuta è: ecCTF3T_7U_BRU73?!
'''