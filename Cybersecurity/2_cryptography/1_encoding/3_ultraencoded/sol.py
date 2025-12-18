import base64
    
with open("./zero_one.txt", "r") as f:
    data = f.read().strip()

bin_string = data.replace("ZERO", "0").replace("ONE", "1").replace(" ", "")

raw_string = ''.join(chr(int(bin_string[i*8:i*8+8],2)) for i in range(len(bin_string)//8))


b64_decoded = base64.b64decode(raw_string).decode('utf-8')

alpha2morse = {'A': '.-',     'B': '-...',   'C': '-.-.',
        'D': '-..',    'E': '.',      'F': '..-.',
        'G': '--.',    'H': '....',   'I': '..',
        'J': '.---',   'K': '-.-',    'L': '.-..',
        'M': '--',     'N': '-.',     'O': '---',
        'P': '.--.',   'Q': '--.-',   'R': '.-.',
        'S': '...',    'T': '-',      'U': '..-',
        'V': '...-',   'W': '.--',    'X': '-..-',
        'Y': '-.--',   'Z': '--..',

        '0': '-----',  '1': '.----',  '2': '..---',
        '3': '...--',  '4': '....-',  '5': '.....',
        '6': '-....',  '7': '--...',  '8': '---..',
        '9': '----.' }

morse2alpha = {value:key for key,value in alpha2morse.items()}

final_flag = ''.join(morse2alpha.get(i) for i in b64_decoded.split())

print(f"The flag is: {final_flag}")