flag = ""

with open("./challenge.txt", "r") as f:

    char = f.read(1)
    while char:
        if char.isupper():
            flag += char
        char = f.read(1)

flag = flag.replace("ZERO", "0")
flag = flag.replace("ONE", "1")

result=''.join(chr(int(flag[i*8:i*8+8],2)) for i in range(len(flag)//8))

print(f"The flag is: {result}")