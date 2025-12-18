import base64

def base64tostring(text):
    return base64.b64decode(text).decode('UTF-8','ignore')

# La stringa trovata nel pacchetto TPC che ha generato l'errore è:
secret = 'Vmxkd1NrNVhVbk5qUlZKU1ltdGFjRlJYZEhOaWJF NVhWR3RPV0dKVmJE WldiR1JyV1ZkS1ZX RXphRnBpVkVaVFYy cEtVMU5IUmtobFJY QlRUVmhDTmxZeFdt dGhhelZ5WWtWYWFW SlViRmRVVlZaYVRU RmFjbFpyT1ZaV2JX UTJWa1pvYTFkck1Y VlVhbHBoVWxack1G UlZaRXRqVmxaMVZH MTRXRkpVUlRCWFdI QkdUbGRHY2s1VmFF OVdNWEJoV1Zkek1X SldaSFJPVm1SclZs ZDRXbFJWVm5wUVVU MDk='.replace(' ','')

# Sembrea una tringa encodata in base64, la decodicifo
secret = base64tostring(secret)
print("Stringa decodificata: ", secret)
# La stringa trovata non ha minimamente senso, ma sembra essere ancora in base64


# Itero una serie di decodifiche base64 finché non trovo una stringa significativa
for i in range(1,11):
    
    try:
        dec = base64tostring(secret)
        print(dec)
        print('')
        secret = dec
    except Exception as e:
        print("Decoding error:", e)
        break

print(f"Trovata una flag: {secret}")