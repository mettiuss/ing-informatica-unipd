
from collections import Counter

def getFreq(text):  #Gets the frequecies of every character given a string
    text = text.replace(" ", "") # Removes all the spaces
    return Counter(text)

#---------------------------EXAMPLE----------------------------
example = "Getting to know what Counter functions does"
print(getFreq(example))
#Output: Counter({'t': 6, 'n': 5, 'o': 5, 'e': 3, 'i': 2, 'w': 2, 'u': 2, 's': 2, 'G': 1, 'g': 1, 'k': 1, 'h': 1, 'a': 1, 'C': 1, 'r': 1, 'f': 1, 'c': 1, 'd': 1})
#--------------------------------------------------------------
