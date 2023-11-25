#include "../include/BookShelf.h"

#include <iostream>

// Costruttore default dell'oggetto Bookshelf [su vettore di 10 elementi]
BookShelf::BookShelf() {
  size = 0;
  max_size = 10;
  b = new Book[max_size];
}

// Costruttore con dimensione vettore
BookShelf::BookShelf(int dimension) {
  if (dimension <= 0) throw Invalid();
  size = 0;
  max_size = dimension;
  b = new Book[dimension];
}

// Distruttore di Bookshelf
BookShelf::~BookShelf() { delete[] b; }

// Set elemento, con controllo validità indice
void BookShelf::safe_set(int index, Book value) {
  if (index < 0 || index >= size) throw Invalid();
  b[index] = value;
}

// Get elemento, con controllo validità indice
Book BookShelf::safe_get(int index) const {
  if (index < 0 || index >= size) throw Invalid();
  return b[index];
}

// Get reference di un elemento, con controllo validità indice
Book &BookShelf::at(int index) {
  if (index < 0 || index >= size) throw Invalid();
  return b[index];
}

// Get costante della reference di un elemento, con controllo validità indice
const Book &BookShelf::at(int index) const {
  if (index < 0 || index >= size) throw Invalid();
  return b[index];
}

// Funzione per aggiunta elementi in coda al vettore Bookshelf
void BookShelf::push_back(Book value) {
  // Resize vettore nel caso in cui sia pieno
  if (max_size == 0) {
    resize(10);
  } else if (size == max_size) {
    resize(max_size * 2);
  }

  b[size] = value;
  size++;
}

// Get + Rimozione dell'ultimo elemento del vettore Bookshelf
Book BookShelf::pop_back() {
  size--;
  return b[size];
}

// Overload operatore [], per get e set tramite offset dall'indirizzo di b
Book &BookShelf::operator[](int index) { return b[index]; }

// Overload operatore [] const, per solo get tramite offset dall'indirizzo di b
Book BookShelf::operator[](int index) const { return b[index]; }

// Funzione per riserva spazio del vettore Bookshelf
void BookShelf::reserve(int dimension) {
  /* Lo spazio viene riservato solo se la nuova dimensione è
  maggiore della dimensione attuale del vettore */
  if (dimension > max_size) resize(dimension);
  
  reserved = dimension;
}

// Ridimensionamento del vettore Bookshelf
void BookShelf::resize(int dimension) {
  Book *newArray;

  newArray = new Book[dimension];
  max_size = dimension;

  // Copia del vecchio vettore Bookshelf in quello nuovo
  std::copy(b, b + size, newArray);

  // Liberazione memoria riservata al vecchio vettore Bookshelf
  delete[] b;

  b = newArray;
}