/*Scrivete un programma in GO che simuli un’agenzia di noleggi d’auto che deve gestire
le prenotazioni di 10 clienti. Ogni cliente noleggia un veicolo tra quelli disponibili:
Berlina, SUV o Station Wagon.
• Creare la struttura Cliente con il campo "nome"
• Creare la struttura Veicolo con il campo "tipo"
• Creare la function "noleggia" che prende come input un cliente e che prenota
uno a caso tra i veicoli. Questa function deve anche stampare che il cliente x ha
noleggiato il veicolo y.
• Creare una function "stampa" che, alla fine del processo, stampa il numero di
Berline, SUV e Station Wagon noleggiati.
• Ogni cliente può noleggiare un veicolo contemporaneamente ad altri.
Si noti che si possono creare ulteriori funzioni per risolvere il problema, oltre alle due
obbligatorie, descritte sopra.*/

package main

import (
	"fmt"
	"math/rand"
	"sync"
)

type Cliente struct {
	nome string
}

type Veicolo struct {
	tipo string
}

func noleggia(cliente *Cliente, veicoli *[]Veicolo, noleggi *map[string]int, lock *sync.Mutex) {
	veicolo := (*veicoli)[rand.Intn(3)]
	lock.Lock()
	(*noleggi)[veicolo.tipo]++
	lock.Unlock()

	fmt.Printf("Il cliente %s ha noleggiato una vettura di tipo %s \n", cliente.nome, veicolo.tipo)
}

func stampa(noleggi *map[string]int) {
	for veicolo, n := range *noleggi {
		fmt.Printf("Sono state noleggiate %d vetture di tipo %s \n", n, veicolo)
	}
}

func main() {
	clienti := []Cliente{{nome: "Pino"}, {nome: "Giorgio"}, {nome: "Andrea"}, {nome: "Pina"}, {nome: "Gio"}, {nome: "Matteo"}, {nome: "Giulio"}, {nome: "Giulia"}, {nome: "Sara"}, {nome: "Ale"}}
	veicoli := []Veicolo{{tipo: "Berlina"}, {tipo: "SUV"}, {tipo: "Station Wagon"}}

	noleggi := make(map[string]int)

	var wg sync.WaitGroup
	var lock sync.Mutex

	for _, cliente := range clienti {
		wg.Add(1)

		go func() {
			defer wg.Done()
			noleggia(&cliente, &veicoli, &noleggi, &lock)
		}()
	}

	wg.Wait()

	stampa(&noleggi)
}
