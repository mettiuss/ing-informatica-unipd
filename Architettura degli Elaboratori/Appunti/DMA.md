Per alcuni dispositivi la gestione ad interrupt non è consigliabile, in particolare per dispositivi con un alto "data rate"

In casi particolari, con data rate che eccedono il tempo che il processore impiega a processare il dato, si può avere una perdita di alcuni dati
Anche se questo non accade è possibile che la cpu debba usare una gran parte del tempo nel processare dati da dispositivi di I/O invece che lavorare alle elaborazioni

Per risolvere questo problema si può dare _Direct Memory Access_ al dispositivo, così che esso possa scrivere direttamente i dati in memoria

Il processore quindi deve solo indicare:
- il device da cui leggere/scrivere
- l'indirizzo da cui iniziare
- quanti dati vanno trasferiti
- Dare il comando di start

Tutto il resto viene gestito dal DMA che si occupa del trasferimento usando la tecnica del busy waiting
Alla fine del processo di trasferimento invia un interrupt alla cpu

La memoria è collegata alla cpu ed al dma attraverso il bus
In questo caso si dà la precedenza al dma, il quale manda un segnale di HOLD alla cpu

Nel peggiore dei casi viene svolto un accesso dal dma e uno dalla cpu, alternativamente, quindi il ritardo massimo generato dal dma è un ciclo di accesso.
Questo viene chiamato _cycle stealing_
