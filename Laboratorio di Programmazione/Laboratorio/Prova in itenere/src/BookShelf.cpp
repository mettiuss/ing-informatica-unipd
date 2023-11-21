#include "../include/BookShelf.h"

#include <iostream>

// Costruttore di default
BookShelf::BookShelf() {
  size = 0;
  max_size = 10;
  b = new Book[max_size];
}

// Costruttore di BookShelf
BookShelf::BookShelf(int dimension) {
  size = 0;
  max_size = dimension;
  b = new Book[dimension];
}

// Distruttore di BookShelf
BookShelf::~BookShelf() { delete[] b; }

// Safe_set
void BookShelf::safe_set(int index, Book value) {
  if (index < 0 || index >= size) throw Invalid();
  b[index] = value;
}

/* Permette di settare il valore di un elemento
 presente in una posizione tra 0 e size */
Book BookShelf::safe_get(int index) const {
  if (index < 0 || index >= size) throw Invalid();
  return b[index];
}

Book &BookShelf::at(int index) {
  // la posizione dev'essere compresa tra 0 e size
  if (index < 0 || index >= size) throw Invalid();
  return b[index];
}

const Book &BookShelf::at(int index) const {
  // la posizione dev'essere compresa tra 0 e size
  if (index < 0 || index >= size) throw Invalid();
  return b[index];
}

void BookShelf::push_back(Book value) {
  // ridimensiona il vettore
  if (max_size == 0) {
    resize(10);
  } else if (size == max_size) {
    resize(max_size * 2);
  }

  b[size] = value;
  size++;
}

Book BookShelf::pop_back() {
  size--;
  return b[size];
}

//===== Overload Operator[] =====//
// restituisce una reference all'elemento nell'indice index
Book &BookShelf::operator[](int index) { return b[index]; }

// restituisce un valore costante dell'elemento nell'indice index
Book BookShelf::operator[](int index) const { return b[index]; }
// Nota su overload: non controlla in che cella si sta scrivendo

//===== Reserve =====//
/* Riserva una dimensione minima al vector
Se l'attuale dimensione è maggiore di quella specificata,
non ridimensiona l'array, ma successivamente terrà
conto della dimensione minima */
void BookShelf::reserve(int dimension) {
  if (dimension > max_size) resize(dimension);
  reserved = dimension;
}

//===== Resize =====//
/* Ridimensiona il vettore, qualora si cercasse di inserire
un elemento al di fuori dei limiti */
void BookShelf::resize(int dimension) {
  Book *newArray;

  if (dimension > reserved) {
    newArray = new Book[dimension];
    max_size = dimension;
  } else {
    newArray = new Book[reserved];
    max_size = reserved;
  }

  std::copy(b, b + size, newArray);

  delete[] b;

  b = newArray;
}