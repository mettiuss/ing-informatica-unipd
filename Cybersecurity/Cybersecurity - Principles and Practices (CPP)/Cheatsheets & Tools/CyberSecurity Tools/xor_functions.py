import base64 #Useful only for the last example


def xorInt (num, key):
    return num ^ key #xor between two integers

#--------------------------EXAMPLE---------------------------
num = 20
key = 4
print(xorInt(num, key)) #It prints 16
#------------------------------------------------------------

def xorString(s1, s2): #Returns the xor of two strings
    xor = "".join([chr(ord(x) ^ ord(y)) for x, y in zip(s1, s2)])
    return xor

#--------------------------EXAMPLE------------------------------------------------
encodedFlag = "I3gDKVh1Lh4EVyMDBFo=" #Encypted flag with Base64
Key = "e4Bne4Bne4Bne4"  #Key used for the xor
translated = base64.b64decode(encodedFlag).decode('UTF-8') #Decode the encodedFlag 
print(xorString(translated, Key)) #Output: FLAG=Alpacaman
#--------------------------------------------------------------------------------