/**
 * BookShelf Class
 * Copyright (c) 2023 group: Laboratorio della Latrina
 *
 * Questa classe implementa una lista simile a std::vector per variabili di tipo
 * Book.
 *
 * Utilizza un array in stile c riempito in parte e alloca lo spazio in modo
 * dinamico, aumentando e diminuendo la dimensione in modo automatico.
 */

#include "Book.h"

class BookShelf {
  class Invalid {};

 public:
  //============= Costruttori ======================
  /* Costruttore di default, equivalente a BookShelf(10)*/
  BookShelf();

  /* Costruttore che permette all'utente di definire una dimensione,
     lancia Invalid se dimension <= 0*/
  BookShelf(int dimension);

  /* Distruttore per la classe */
  ~BookShelf();  // destructor

  int getSize() const { return size; }

  //============= getter e setter ==================
  /* I metodi permettono di ottenere un dato e di modificarlo
     in modo sicuro, senza mai modificare la memoria non allocata
     al vettore, lanciano Invalid se l'index non è valido */
  void safe_set(int index, Book value);
  Book safe_get(int index) const;

  //============= operator[] =======================
  /* Implementazione dell'operator[], permette di ottenere
     e modificare un dato senza alcun check aggiunto */
  Book &operator[](int index);
  Book operator[](int index) const;

  //============= at ===============================
  /* simile a safe get, ma ritorna reference o
     const reference invece che una copia dell'oggetto */
  Book &at(int index);
  const Book &at(int index) const;

  //============= push_back, pop_back ==============
  /* Permettono di inserire o rimuovere elementi dalla struttura dati*/
  void push_back(Book value);
  Book pop_back();

  //============= reserve ==========================
  /* Riserva una dimensione minima al vector
  Se l'attuale dimensione è maggiore di quella specificata,
  non ridimensiona l'array, ma successivamente terrà
  conto della dimensione minima */
  void reserve(int dimension);

 private:
  int size = 0, max_size = 0, reserved = 0;
  Book *b = nullptr;
  void resize(int newDim);
};
