# BOMB

Analizzando l'eseguibile con IDA si puo` notare che durante la fase di disinnesco
vengono chiamate diverse subroutine, per l'esattezza 6.
Ogni subroutine chiamata richiede un input dall'utente,
se l'input e' sbagliato la bomba esplode, mentre se e' corretto si passa
alla fase successiva.

Le stringhe comparate (dunque gli input corretti) nelle 6 fasi sono:
    1. Border relations with Canada have never been better.
    2. 1 2 4 8 16 32
    3. Piu' possibilita': [(1, 207), (2, 707), (3, 256), (4, 389), (5, 206), (6, 682), (7, 327)]
    4. [Valore compreso tra 0 e 14] 0