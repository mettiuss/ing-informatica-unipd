
def intToBinary(number):    #returns the binary value of the number
    return format(number,'b')

#-------------------------------EXAMPLE#-------------------------------
n = 100 
print(intToBinary(n)) #Output: 1100100
#----------------------------------------------------------------------


def binaryToInt(number):
    return int(str(number),2)   #Simply returns the integer number 


#-------------------------------EXAMPLE--------------------------------
n = 1101010111010100011001
print(binaryToInt(n)) #Output: 3503385
#----------------------------------------------------------------------


def bit_to_string(binText):
    binText = binText.replace(" ", "")
    return "".join(chr(int(binText[8*i:8*i+8] , 2)) for i in range(len(binText)//8))

#Credit to this function: https://github.com/GiovanniMenon/Cipher_Tool/blob/main/binary_converter.py made by Giovanni Menon


#-----------------------------------------EXAMPLE-------------------------------------------------------
s = "01001000 01100101 01101100 01101100 01101111 00100000 01010111 01101111 01110010 01101100 01100100 "
s = s.replace(" ", "")
print(bit_to_string(s)) #Output: Hello World
#-------------------------------------------------------------------------------------------------------