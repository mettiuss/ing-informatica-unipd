
def ceaserCipher (s, shift): #s is a string, shift is the key used to shift the characters
    result = ""
    for char in s:
        if ord(char)>= 65 and ord(char) <=90:   #Checks if the character is from A to Z
            result += chr((ord(char) - 65 + shift)%26 + 65)
        elif ord(char) >=97 and ord(char) <=122:
            result += chr((ord(char) - 97 + shift)%26 + 97) #Checks if the character is from a to z
        else:
            result+=char #Special simbols will be avoided and not shifted
    return result

def ceaserCipherBrutal(s, shift): #Called this function brutal because every character will be shifted
    result = ""
    for char in s:
        result+= chr(ord(char)+ shift) #Here every character is shifted
    return result



#-------------------------------------------------Examples#-------------------------------------------------
s = "}{[l^KlwOmwZjmOKW9"    #Example of ceaserCipherBrutal 
for i in range (-30, 30):
    print("Level:", i, ceaserCipherBrutal(s, i), "\n")


st = "Hello World"       #Example of ceaserCipher
for i in range (0,10):
    print("Level:", i,ceaserCipher(st, i),"\n")


encrypted = "Olssv Dvysk" #If for example we know the shift, we can decrypt it using ceasercipher function passing -shift
shift = 7
print(ceaserCipher(encrypted, -7))
