/**
 * Book Class
 * Copyright (c) 2023 group: Laboratorio della Latrina
 *
 * Questa classe permette la creazione di oggetti di tipo Book
 * Ogni Book deve contenere diverse informazioni, quali:
 *  -Autore    (nome e cognome sono 2 parametri separati)
 *  -Titolo
 *  -ISBN      (codice univoco generato secondo lo standard ISBN-13 (13 char))
 * -Copyright  (data in cui è stato registrato il copyright (se registrato))
 *
 * Un Book può essere prestato, l'operazione di prestito è disponibile solo
 * per libri che non sono già stati prestati
 */

#include <iostream>

#include "Date.h"

class Book {
  class Invalid {};

 public:
  enum Status { Disponibile = true, Fuori = false };

  //============= Costruttori ======================
  /* Costruttore di default, i membri string vengono settati come stringhe
       vuote, mentre la data di copyrigth viene settata a 01/01/0000 */
  Book()
      : authorName{""},
        authorSurname{""},
        bookTitle{""},
        copyrightDate{nullDate},
        bookISBN{""} {}

  /* Costruttore per Book non coperti da copyright, viene settato tutto tranne
       tranne la data, che viene settata a 01/01/0000 */
  Book(std::string aName, std::string aSurname, std::string title,
       std::string ISBN);

  /* Costruttore per Book protetti da copyright, viene settato ogni dato */
  Book(std::string aName, std::string aSurname, std::string title, Date data,
       std::string ISBN);

  //============= Funzioni get (const) =============
  /* Funzioni che permettono di ottenere le variabili membro private
       Le seguenti funzioni sono const => non possono in alcun modo
       modificare lo stato dell'oggetto Book */

  /* getAuthor -> ritorna una string contenente la concatenazione di nome e
   * cognome dell'autore */
  std::string getAuthor() const { return authorName + " " + authorSurname; }

  /* getAuthorName -> ritorna una string contenente il nome dell'autore del
   * libro*/
  std::string getAuthorName() const { return authorName; }

  /* getAuthorSurame -> ritorna una string contenente il cognome dell'autore del
   * libro*/
  std::string getAuthorSurname() const { return authorSurname; }

  /* getTitle -> ritorna una string contenente il titolo del libro*/
  std::string getTitle() const { return bookTitle; }

  /* getCopyrightDate -> ritorna un Date contenente la data di registrazione del
   * copyright */
  Date getCopyrightDate() const { return copyrightDate; }

  /* getIsbn -> ritorna una string contenente l'ISBN del libro*/
  std::string getIsbn() const { return bookISBN; }

  /* getBookStatus -> ritorna uno Status contenente lo stato del libro
       (Disponibile/Fuori) */
  Status bookStatus() const { return status; }

  //============= Registrazione copyright ==========
  /* Funzione che permette la registrazione del copyright
       in seguito alla creazione del libro */
  void registerCopyright(Date data);

  //============= Gestione prestito ================
  /* Funzione che permette il prestito del libro, se il libro
       è già stato prestato, assicurarsi che sia rientrato prima di
     riprestarlo*/
  void rentBook();

  /* Funzione che permette la restituzione del libro, se il libro
       non è in prestito, non fa nulla */
  void returnBook();

 private:
  std::string authorName, authorSurname, bookTitle, bookISBN;
  Date copyrightDate, nullDate = Date();
  Status status = Disponibile;
};

//============= Overload operatori ===============
/* Overload operatore di confronto uguaglianza
   ritorna true <=> l'ISBN dei due Book è uguale */
bool operator==(Book firstBook, Book secondBook);

/* Overload operatore di confronto diversità
   ritorna true <=> l'ISBN dei due Book è diverso */
bool operator!=(Book firstBook, Book secondBook);

/* Overload operatore di out stream
   permette di stampare a stream l'oggetto Book */
std::ostream &operator<<(std::ostream &os, Book book);
