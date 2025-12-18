

def string2binary(text):
    return ''.join(f"{ord(c):08b}" for c in text)

def binary2string(text):
    return ''.join(f"{ord(c):08b}" for c in text)

def splitblock(block):
    Lr = block[:len(block)//2]
    Rr = block[len(block)//2:]
    return Lr, Rr

def expand(Rr):
    '''
        Do this by remapping the values using their index, e.g.
        1 2 3 4 5 6 -> 1 2 4 3 4 3 5 6
    '''
    return Rr[0:2] + Rr[3] + Rr[2] + Rr[3] + Rr[2] + Rr[4:]

def xor(a, b):
    res = int(a, 2) ^ int(b, 2)
    return f"{res:08b}"

def rule9b0(b):
    row = int(b[0])
    col = int(b[1:], 2)

    matrix = [
        ['101','010','001','110','011','100','111','000'],
        ['001','100','110','010','000','111','101','011']
    ]

    return matrix[row][col]

def rule9b1(b):
    row = int(b[0])
    col = int(b[1:], 2)

    matrix = [
        ['100','000','110','101','111','001','011','010'],
        ['101','011','000','111','110','010','001','100']
    ]

    return matrix[row][col]

def encrypt(plaintext, key, R):
    text_encr = ''

    # Rule 1: Choose a plaintext that is divisible into 12bit 'blocks'
    text_bin = string2binary(plaintext)
    if len(text_bin) % 12 != 0:
        raise ValueError("Plaintext length in bits must be divisible by 12.")
    
    # Rule 2: Choose a key at least 8bits in length
    key_bin = string2binary(key)
    if len(key_bin) < 8:
        raise ValueError("Key length in bits must be at least 8.")
    
    # Rule 3: For each block from i=0 while i<N perform the following operations
    for i in range(len(text_bin) // 12):
        block = text_bin[i*12:(i+1)*12]
    
        # Rule 4: Repeat the following operations on block i, from r=0 while r<R
        for r in range(R):

            # Rule 5: Divide the block into 2 6bit sections Lr,Rr
            Lr, Rr = splitblock(block)

            # Rule 6: Using Rr, "expand" the value from 6bits to 8bits
            Rr_expanded = expand(Rr)
            
            # Rule 7: XOR the result of this with 8bits of the Key beginning with Key[iR+r] and wrapping back to the beginning if necessary.
            curr_key = ''.join(key_bin[((i*R + r) + k) % len(key_bin)] for k in range(8))
            Rr_exp_xor_key = xor(Rr_expanded, curr_key)

            # Rule 8: Divide the result into 2 4bit sections S1, S2
            S1, S2 = splitblock(Rr_exp_xor_key)

            # Rule 9: Calculate the 2 3bit values using the two "S boxes" below, using S1 and S2 as input respectively.
            Rr_exp_xor_key_0_conv = rule9b0(S1)
            Rr_exp_xor_key_1_conv = rule9b1(S2)

            # Rule 10: Concatenate the results of the S-boxes into 1 6bit value
            Rr_sboxes = Rr_exp_xor_key_0_conv + Rr_exp_xor_key_1_conv
            if len(Rr_sboxes) != 6:
                raise Exception("Error on Rule 10")
            
            # Rule 11: XOR the result with Lr
            Rr_sboxes_xor_Lr = xor(Rr_sboxes, Lr)[2:]

            # Rule 12: Use Rr as Lr and your altered Rr (result of previous step) as Rr for any further computation on block i
            Lr, Rr = Rr, Rr_sboxes_xor_Lr
            block = Lr + Rr

        text_encr += block

    return text_encr

def decrypt(ciphertext, key, R):
    text_decr = ''

    text_bin = ciphertext
    key_bin = string2binary(key)
    if len(text_bin) % 8 != 0:
        raise ValueError("Plaintext has to be in bit format.")
    
    for i in range(len(text_bin) // 12):
        block = text_bin[i*12:(i+1)*12]

        # Rule 4: Repeat the following operations on block i, from r=R-1 down to 0
        # Gli step vanno ripercorsi al contrario, dalla fine all'inizio
        for r in range(R-1, -1, -1):
            
            # Rule 12: Use Rr as Lr and your altered Rr (result of previous step) as Rr for any further computation on block i
            # Rl = Lr, Rr = Rr_alt, tenendo conto del fatto che block = Lr + Rr
            Rr, Rr_sboxes_xor_Lr = splitblock(block)

            # Rule 11: XOR the result with Lr
            # Ricordando la proprietà dell'XOR, A xor B = C  =>  A = B xor C
            Rr_expanded = expand(Rr)
            # Rule 7: XOR the result of this with 8bits of the Key beginning with Key[iR+r] and wrapping back to the beginning if necessary.
            # Ricalcolo la key usata nella cifratura e faccio la XOR inversa
            curr_key = ''.join(key_bin[((i*R + r) + k) % len(key_bin)] for k in range(8))
            Rr_exp_xor_key = xor(Rr_expanded, curr_key)

            # Rule 8: Divide the result into 2 4bit sections S1, S2
            S1, S2 = splitblock(Rr_exp_xor_key)

            # Rule 9: Calculate the 2 3bit values using the two "S boxes" below, using S1 and S2 as input respectively.
            Rr_exp_xor_key_0_conv = rule9b0(S1)
            Rr_exp_xor_key_1_conv = rule9b1(S2)

            # Rule 10: Concatenate the results of the S-boxes into 1 6bit value
            Rr_sboxes = Rr_exp_xor_key_0_conv + Rr_exp_xor_key_1_conv
            if len(Rr_sboxes) != 6:
                raise Exception("Error on Rule 10")
            
            # Riottengo Lr facendo la XOR inversa con Rr_sboxes_xor_Lr
            Lr = xor(Rr_sboxes, Rr_sboxes_xor_Lr)[2:]

            block = Lr + Rr

        text_decr += block
    
    res = ""
    for i in range(len(text_decr) // 8):
        byte = text_decr[i*8:(i+1)*8]
        res += chr(int(byte, 2))

    return res


if __name__ == "__main__":
    puzzle = "011001010010001010001100010110000001000110000101"
    plaintext = "MiN0n!"
    key = "Mu"
    R = 2

    try:
        encrypted = encrypt(plaintext, key, R)
        print(f"Puzzle: {puzzle}")
        print(f"Encrypted binary: {plaintext} -> {encrypted}")
        print(f"Is the encrypted text equal to the puzzle? {'Yes' if encrypted == puzzle else 'No'}")

        decrypted = decrypt(puzzle, key, R)
        print(f"Decrypted binary: {puzzle} -> {decrypted}")
        
    except ValueError as e:
        print(e)