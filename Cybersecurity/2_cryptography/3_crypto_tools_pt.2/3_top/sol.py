import random
import sys
import time

cur_time = str(time.time()).encode('ASCII')
random.seed(cur_time)

with open(sys.argv[1], "rb") as f:
    secret = f.read()

secret_time = secret[-len(cur_time):]
# Gli ultimi len(cur_time) byte del file contengono
# il cur_time concatenato al messaggio cifrato con XOR 0x88
plain_time = ''.join([chr(m ^ k) for (m, k) in zip(secret_time, [0x88]*len(cur_time))])
print(f"plain time:\t{plain_time}")

# Reimpostiamo il seme del generatore di numeri casuali
random.seed(plain_time)

keys_secret = [random.randrange(256) for _ in secret[:-len(cur_time)]]
plain_text = ''.join([chr(m ^ k) for (m, k) in zip(secret[:-len(cur_time)], keys_secret)])
print(f"Plaint text: {plain_text}")