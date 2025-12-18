import re

flag = ""
keys = [(1, 9, 4), (4, 2, 8), (4, 8, 3), (7, 1, 5), (8, 10, 1)]

with open("./book.txt", "r") as f:
    book = f.read()
    
    paragraphs = [paragraph.split("\n") for paragraph in book.split("\n\n")]
    words = list(
        map(
            lambda paragraph: list(map(lambda line: re.sub(r"[^\sa-zA-Z]", "", line).split(" "), paragraph)), paragraphs
        )
    )

    for key in keys:
        flag += words[key[0] - 1][key[1] - 1][key[2] - 1] + " "

print(f"The flag is: {flag.strip()}")