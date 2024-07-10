/*Scrivere un programma in Go che simuli la produzione di 5 torte da parte di 3
pasticceri. La produzione di ogni torta richiede 3 fasi che devono avvenire in ordine:
prima la torta viene cucinata, poi guarnita e infine decorata.

Il primo pasticcere si occupa solo di cucinare le torte e ci mette 1 secondo per ogni torta.
Questo pasticcere ha a disposizione 2 spazi per appoggiare le torte una volta che ha
finito di cucinarle.
Se ci sono spazi liberi, può iniziare a cucinare la torta successiva senza aspettare
che il secondo pasticcere si liberi per guarnire quella appena cucinata.

Il secondo pasticcere si occupa solo di guarnire le torte e ci mette 4
secondi per ogni torta. Anche questo pasticcere ha a disposizione 2 spazi per
appoggiare le torte una volta che ha finito di guarnirle.

Il terzo pasticcere si occupa solo di decorare le torte e ci mette 8 secondi per ogni torta.

I tre pasticceri lavorano contemporaneamente.*/

package main

import (
	"fmt"
	"sync"
	"time"
)

func pasticcere1(stazione12 chan bool) {
	for i := range 5 {
		fmt.Printf("%v Inizio a cucinare la torta %d \n", time.Since(start), i+1)
		time.Sleep(time.Second)
		stazione12 <- true
		fmt.Printf("%v Finito di cucinare la torta %d \n", time.Since(start), i+1)
	}
}

func pasticcere2(stazione12 chan bool, stazione23 chan bool) {
	for i := range 5 {
		<-stazione12
		fmt.Printf("%v Inizio a guarnire la torta %d \n", time.Since(start), i+1)
		time.Sleep(time.Second * 4)
		stazione23 <- true
		fmt.Printf("%v Finito di guarnire la torta %d \n", time.Since(start), i+1)
	}
}

func pasticcere3(stazione23 chan bool) {
	for i := range 5 {
		<-stazione23
		fmt.Printf("%v Inizio a decorare la torta %d \n", time.Since(start), i+1)
		time.Sleep(time.Second * 8)
		fmt.Printf("%v Finito di decorare la torta %d \n", time.Since(start), i+1)
	}
}

var start = time.Now()

func main() {
	stazione12 := make(chan bool, 2)
	stazione23 := make(chan bool, 2)

	var wg sync.WaitGroup

	wg.Add(3)

	go func() {
		defer wg.Done()
		pasticcere1(stazione12)
	}()

	go func() {
		defer wg.Done()
		pasticcere2(stazione12, stazione23)
	}()

	go func() {
		defer wg.Done()
		pasticcere3(stazione23)
	}()

	wg.Wait()
}
