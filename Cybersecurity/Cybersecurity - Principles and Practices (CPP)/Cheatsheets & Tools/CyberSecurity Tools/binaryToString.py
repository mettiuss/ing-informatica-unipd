

def convertBinaryToText(binaryText): #Version that includes the spaces in the binaryText passed to the function
    convertedText = ""
    values = binaryText.split()
    for c in values:
        convertedText+= chr(int(c,2))
    return convertedText

#Credits for this function: https://www.adamsmith.haus/python/answers/how-to-convert-binary-to-string-in-python

#-------------------------------------EXAMPLE WITH convertBinaryToText-------------------------------------
example = "01001000 01100101 01101100 01101100 01101111 00100000 01010111 01101111 01110010 01101100 01100100 00100001"
         #010010000110010101101100011011000110111100100000010101110110111101110010011011000110010000100001
print(convertBinaryToText(example)) #Output: Hello World
#----------------------------------------------------------------------------------------------------------



def binaryToText(binaryText): #version that needs to be used when there are no group of 8 bits
    return ''.join(chr(int(''.join(c), 2)) for c in zip(*[iter(binaryText)]*8))

#Credits for this function: Josh Lee on https://stackoverflow.com/questions/9916334/bits-to-string-python


#-------------------------------------EXAMPLE WITH binaryToText--------------------------------------------
secondExample = "01010000011110010111010001101000011011110110111000100000011010010111001100100000011001100111010101101110"
print(binaryToText(secondExample)) #Output: Python is fun
#----------------------------------------------------------------------------------------------------------


def stringToBinary(text): #Converts a string in a sequence of bits
    res = ''.join(format(ord(i), '08b') for i in text)
    return res
#Credit for this function : https://www.geeksforgeeks.org/python-convert-string-to-binary/

#-------------------------------------EXAMPLE WITH stringToBinary-------------------------------------------
bits = stringToBinary("BitsToString")
print(bits) #Output : 010000100110100101110100011100110101010001101111010100110111010001110010011010010110111001100111
            #You can check if the output is correct using online tools with ASCII Character Encoding
#----------------------------------------------------------------------------------------------------------
