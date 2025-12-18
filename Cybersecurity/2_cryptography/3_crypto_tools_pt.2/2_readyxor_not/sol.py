import base64

original_data = 'El Psy Congroo'
encrypted_data = 'IFhiPhZNYi0KWiUcCls='
encrypted_flag = 'I3gDKVh1Lh4EVyMDBFo='

print(f"Original Data: {original_data}")
enc_data = base64.b64decode(encrypted_data).decode('utf-8')
print(f"Encrypted Data: {enc_data}")
enc_flag = base64.b64decode(encrypted_flag).decode('utf-8')
print(f"Encrypted Flag: {enc_flag}")

key = ''.join(chr(ord(a) ^ ord(b)) for a, b in zip(original_data, enc_data))
print(f"Key: {key}")

flag = ''.join(chr(ord(a) ^ ord(b)) for a, b in zip(enc_flag, key))
print(f"Flag: {flag}")