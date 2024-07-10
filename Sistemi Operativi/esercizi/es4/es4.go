/*
Scrivere un programma in Go che simuli un'attività di trading di valute in un mercato
fittizio.


Il programma deve simulare usando la concorrenza tre coppie di valute: EUR/USD,
GBP/USD e JPY/USD, e simulare le operazioni di acquisto e vendita in parallelo.
Creare una funzione "simulateMarketData" che simuli il prezzo delle coppie di valute e

invii i dati simulati su un canale. In particolare:
• Il prezzo della coppia EUR/USD varia casualmente tra 1.0 e 1.5.
• Il prezzo della coppia GBP/USD varia casualmente tra 1.0 e 1.5.
• Il prezzo della coppia JPY/USD varia casualmente tra 0.006 e 0.009.

I prezzi vengono generati e inviati sul canale corrispondente ad intervalli regolari, in
particolare ogni secondo.


Creare una funzione "selectPair" che utilizza una "select" per gestire le operazioni di
vendita e acquisto in base alle condizioni specificate. In particolare:
• Se il prezzo di EUR/USD supera 1.20, deve vendere EUR/USD. Simulare la
vendita con un tempo di 4 secondi, cioè inserire un delay di 4 secondi prima di
confermare la vendita.
• Se il prezzo di GBP/USD scende sotto 1.35, deve acquistare GBP/USD. Simulare
l'acquisto con un tempo di 3 secondi, cioè inserire un delay di 3 secondi prima
di confermare l'acquisto.
• Se il prezzo di JPY/USD scende sotto 0.0085, deve acquistare JPY/USD.
Simulare l'acquisto con un tempo di 3 secondi, cioè inserire un delay di 3
secondi prima di confermare l'acquisto.


Il programma deve eseguire il ciclo di trading per un minuto e alla fine del ciclo deve
terminare.
*/

package main

import (
	"fmt"
	"math/rand"
	"time"
)

func generatePrice(min float64, max float64, prices chan float64) {
	for {
		price := min + rand.Float64()*(max-min)
		prices <- price
		time.Sleep(time.Second)
	}
}

func selectValue(eurUsd, gbpUsd, jpyUsd chan float64, done chan bool) {
	for {
		select {
		case eur := <-eurUsd:
			fmt.Println(eur, "EUR")
			if eur > 1.20 {
				fmt.Printf("Vendendo EUR/USD a %f\n", eur)
				time.Sleep(4 * time.Second)
				fmt.Println("Vendita EUR/USD confermata")
			}
		case gbp := <-gbpUsd:
			fmt.Println(gbp, "GBP")
			if gbp < 1.35 {
				fmt.Printf("Comprando GBP/USD a %f\n", gbp)
				time.Sleep(3 * time.Second)
				fmt.Println("Acquisto GBP/USD confermato")
			}
		case jpy := <-jpyUsd:
			fmt.Println(jpy, "JPY")
			if jpy < 0.0085 {
				fmt.Printf("Comprando JPY/USD a %f\n", jpy)
				time.Sleep(3 * time.Second)
				fmt.Println("Acquisto JPY/USD confermato")
			}
		case <-done:
			return
		}
	}
}

func main() {
	eurUsd := make(chan float64)
	gbpUsd := make(chan float64)
	jpyUsd := make(chan float64)
	done := make(chan bool)

	go generatePrice(1, 1.5, eurUsd)
	go generatePrice(1, 1.5, gbpUsd)
	go generatePrice(0.006, 0.009, jpyUsd)

	go selectValue(eurUsd, gbpUsd, jpyUsd, done)

	time.Sleep(time.Minute)
	done <- true
}
