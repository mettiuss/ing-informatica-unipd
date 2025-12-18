text = ""

# Carico il testo del file contenente il messaggio cifrato
with open('./encrpyted.txt', 'r') as f:
    text = f.read()
print(text)

# Creo una mappa per contare tutte le occorrenze dei caratteri
char_ctr = {}
for char in text:
    char_ctr[char] = char_ctr[char] + 1 if char_ctr.get(char) else 1

# Ordino la mappa in base al numero decrescente di occorrenze
char_ctr = sorted(char_ctr.items(), key=lambda x: x[1], reverse=True)
print(char_ctr)

'''
    Controllando il testo noto che la lettera K appare molto spesso da sola.
    Essendo il testo in lingua inglese, suppongo che K corrisponda alla lettera I.
'''
text = text.replace('K', 'i')

'''
    Dopo aver sostituito K con i, noto che esistono delle sequenze formate da "i'Q",
    che probabilmente corrisponde ad "i'm". Quindi sostituisco "Q" con "m".
'''
text = text.replace("Q", "m")

'''
    Un'altra lettera che appare spesso da sola è la F, che suppongo corrisponda alla lettera A.
'''
text = text.replace("F", "a")

''''
    Nel testo appare spesso la parola "WAaI", che suppongo corrisponda alla parola "flag".
    Quindi sostituisco W con f, A con l e I con g.
'''
text = text.replace("W", "f")
text = text.replace("A", "l")
text = text.replace("I", "g")

'''
    Nel testo appare spesso la sequenza "GiG", che suppongo corrisponda a "did".
    Quindi sostituisco G con d.
'''
text = text.replace("G", "d")

'''
    Nel testo appare spesso la parola "PDT", che suppongo corrisponda a "you".
    Quindi sostituisco P con y, D con o e T con u.
'''
text = text.replace("P", "y")
text = text.replace("D", "o")
text = text.replace("T", "u")

'''
    Ho trovato nel testo la parola "goiMg", che suppongo corrisponda a "going".
    Quindi sostituisco M con n.
'''
text = text.replace("M", "n")

'''
    Controllo se la pasola "VondUC" corrisponde a "wonder"
'''
text = text.replace("V", "w")
text = text.replace("U", "e")
text = text.replace("C", "r")

'''
    Controllo se la parole "Bure" corrisponde a "sure"
'''
text = text.replace("B", "s")

'''
    Sono sicuro che la parola "XryJNogram" corrisponde a "cryptogram"
'''
text = text.replace("X", "c")
text = text.replace("J", "p")
text = text.replace("N", "t")

'''
    La parola "solSing" corrisponde a "solving"
'''
text = text.replace("S", "v")

''' tLe = the e tLis = this '''
text = text.replace("L", "h")

''' Resta solo "Hy hand" = by hand '''
text = text.replace("H", "b")

print(text)

print("\n\nFLAG FOUND: doingthisbyhandismorefunthananonlinetool")