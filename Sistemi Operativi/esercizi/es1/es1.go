/*Scrivete un programma in Go che conta il numero di volte in cui un determinato
carattere "x" compare in una stringa. Il programma deve utilizzare la concorrenza,
avviando una goroutine per ogni carattere nella stringa e verificando se il carattere
corrisponde al carattere cercato.

Esempio: se la stringa è "aaaaaaaaaaaaabbbbbbbbcccccddddccccccfff" e il carattere
da cercare è 'c', il programma dovrebbe avviare una goroutine per ogni carattere della
stringa e utilizzare un meccanismo di sincronizzazione (come un WaitGroup) e un
channel per tenere traccia del conteggio totale dei caratteri corrispondenti.

Inizializzare nel main una stringa di test e il carattere da cercare, e.g.:
stringa := "aaaaaaaaaaaaabbbbbbbbcccccddddccccccfff"
carattere := 'c'

Alla fine del processo, il programma deve stampare il conteggio finale dei caratteri
corrispondenti. Nel nostro esempio, il conteggio finale è 11, poiché il carattere 'c'
compare 11 volte nella stringa.*/

package main

import (
	"fmt"
	"sync"
)

func count(char rune, check rune, ch chan int) {
	if (char == check) {
		curr := <- ch
		ch <- curr + 1
	}
}

func main() {
	stringa := "aaaaaaaaaaaaabbbcbbbbbcccccddddccccccfff"
	carattere := 'c'

	ch := make(chan int, 1)
	ch <- 0
	var wg sync.WaitGroup

	for _, el := range stringa {
		wg.Add(1)

		go func () {
			defer wg.Done()
			count(rune(el), carattere, ch)
		}()
	}

	wg.Wait()
	close(ch)
	fmt.Println(<-ch)
}