import base64

def base64ToText(t):    #Simply returns the text decoded 
    return base64.b64decode(t).decode('UTF-8')

#------------------------------------Example#------------------------------------
s = "UHl0aG9uIGlzIGZ1bg=="  
print(base64ToText(s)) #Print result: Python is fun 

#String example got from https://stackabuse.com/encoding-and-decoding-base64-strings-in-python/