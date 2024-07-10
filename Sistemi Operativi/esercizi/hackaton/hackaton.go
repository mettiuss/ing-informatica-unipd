package main

import (
	"fmt"
	"math/rand"
	"sync"
	"time"
)

type Order struct {
	id      int
	client  int
	weight  int
	product string
}

type Scooter struct {
	id       int
	capacity int
	used     bool
}

var (
	kebabWorker sync.Mutex
	pizzaWorker sync.Mutex
)

const (
	NUMOFSCOOTERS int = 30
	NUMOFORDERS   int = 120
)

func creaOrdine(ch chan Order) {
	for i := range NUMOFORDERS {
		weight := rand.Intn(10) + 1
		var product string
		if rand.Intn(1) == 0 {
			product = "pizza"
		} else {
			product = "kebab"
		}
		ch <- Order{id: i, weight: weight, client: i + 1000, product: product}
		fmt.Println("Nuovo Ordine", i, " inserito")
		time.Sleep(time.Second * 2)
	}
}

func consegnaOrdine(scooter *Scooter, order Order) {
	fmt.Println("Ordine ", order.id, " assegnato")
	scooter.used = true
	var worker *sync.Mutex
	if order.product == "kebab" {
		worker = &kebabWorker
	} else {
		worker = &pizzaWorker
	}

	worker.Lock()
	delay := rand.Intn(5) + 1
	time.Sleep(time.Duration(delay) * time.Second)
	scooter.used = false
	worker.Unlock()
	fmt.Println("Ordine ", order.id, " consegnato")
}

func assegnaOrdine(chOrder chan Order, scooters *[30]Scooter) {
	var wg sync.WaitGroup
	wg.Add(NUMOFORDERS)
	for range NUMOFORDERS {

		order := <-chOrder
		done := false

		for !done {
			for i := range *scooters {
				if !(*scooters)[i].used && (*scooters)[i].capacity >= order.weight {
					go func() {
						defer wg.Done()
						consegnaOrdine(&(*scooters)[i], order)
					}()
					done = true
					break
				}
			}

		}
		time.Sleep(time.Second)
	}
	wg.Wait()
}

func main() {
	scooters := [NUMOFSCOOTERS]Scooter{}
	orderChan := make(chan Order, NUMOFORDERS)

	wg := new(sync.WaitGroup)

	for i := range NUMOFSCOOTERS {
		size := rand.Intn(11) + 5
		scooters[i] = Scooter{id: i, capacity: size, used: false}
	}

	wg.Add(2)
	go func() {
		defer wg.Done()
		creaOrdine(orderChan)
	}()

	go func() {
		defer wg.Done()
		assegnaOrdine(orderChan, &scooters)
	}()

	wg.Wait()
}
