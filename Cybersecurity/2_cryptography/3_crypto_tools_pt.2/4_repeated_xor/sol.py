
with open('encrypted.txt', 'r') as f:
    secret = f.read()

def hex2dec(text):
    res = []
    for i in range(len(text)//2):
        #get the current pair of hex
        curr = text[i*2:(i+1)*2]

        #convert to int
        res.append(int(curr, 16))

    return res

secret_msg = hex2dec(secret)

#STEP 1: Key length identification
#shift string -> it allows us the comparison
def shift(text, key_length):
    return text[key_length:] + text[:key_length]

#freq counter
#we compare the original sentence with its shifted version
#we count the amount of same charcters in the same position
def freq_counter(s1, s2):
    freq = sum([1 for (x, y) in zip(s1, s2) if x == y])
    return freq

# L'hint dice che la lunghezza dovrebbe essere circa 10
# Verifico le frequenze per chiavi di lunghezza 5-15
for key_length in range(5, 16):
    shifted = shift(secret_msg, key_length)
    freq = freq_counter(secret_msg, shifted)
    print(f'Key length: {key_length}, Frequency: {freq}')

# Con una chiave di lunghezza 8 si ottiene la frequenza più alta

#STEP 2: Cryptoanalysis
key_length = 8

def splitter(text, key_length):
    res = []
    for i in range(key_length):
        res.append(text[i::key_length])
    return res

splitted_secret = splitter(secret_msg, key_length)

#we need to define a method that show us the k-th most frequent character in
#a given string
from collections import Counter
def k_char(text, k):
    #use the counter
    freq = Counter(text)

    #order
    ordered = sorted(freq.items(), key=lambda x: x[1], reverse=True)

    return ordered[k][0]

## we can now see the top N freuqent words

#we now work on the Cryptoanalysis, based on each column of the matrix M[secret//8 X 8]
#we can first assume that the most common character in each column is the space ' '.
key_sec = [k_char(splitted_secret[0], 0), k_char(splitted_secret[1], 0), k_char(splitted_secret[2], 0), k_char(splitted_secret[3], 0),
    k_char(splitted_secret[4], 0), k_char(splitted_secret[5], 0), k_char(splitted_secret[6], 0), k_char(splitted_secret[7], 0)]

#xor the key
real_key = [k ^ ord(' ') for k in key_sec]

#decode the secret
real_message = ''
for i, c in enumerate(secret_msg):
    key_pos = i % 8
    real_message+= chr(c ^ real_key[key_pos])

print(real_message)