#include "../include/Book.h"

#include <iostream>

// Costruttore dell'oggetto book
Book::Book(std::string aName, std::string aSurname, std::string title,
           std::string ISBN)
    : authorName{aName},
      authorSurname{aSurname},
      bookTitle{title},
      bookISBN{ISBN} {
  // L'ISBN deve rispettare lo standard a 13 caratteri, se non è valido, lancia
  // l'eccezione Invalid
  if (bookISBN.length() != 13) throw Invalid();
}

// Questo costruttore registra anche la data di copyright
Book::Book(std::string aName, std::string aSurname, std::string title,
           Date data, std::string ISBN)
    : authorName{aName},
      authorSurname{aSurname},
      bookTitle{title},
      copyrightDate{data},
      bookISBN{ISBN} {
  // Check standard ISBN a 13 caratteri
  if (bookISBN.length() != 13) throw Invalid();
}

// Funzione per registrare la data di copyright
void Book::registerCopyright(Date data) {
  /* La data viene impostata solo se la data di registrazione copyright del
     libro è una data nulla */
  if (copyrightDate == Date()) {
    copyrightDate = data;
  } else {
    std::cout << "Errore! La data di copyright era già stata impostata"
              << std::endl;
  }
}

// Funzione che gestisce il prestito del libro
void Book::rentBook() {
  // Il libro viene prestato solo se non è già in prestito
  if (status) {
    std::cout << "Il libro " << bookTitle << " è stato prestato" << std::endl;
    status = Fuori;
  } else {
    std::cout << "Il libro " << bookTitle << " non disponibile per il prestito"
              << std::endl;
  }
}

// Funzione che gestisce il ritiro dei libri in prestito
void Book::returnBook() {
  // Se il libro non è in prestito, non può essere ritirato
  if (!status) {
    std::cout << "Il libro " << bookTitle << " è stato ritirato" << std::endl;
    status = Disponibile;
  } else {
    std::cout << "Il libro " << bookTitle << " non era stato prestato"
              << std::endl;
  }
}

// Overload operatore di confronto (uguaglianza)
bool operator==(Book firstBook, Book secondBook) {
  return firstBook.getIsbn() == secondBook.getIsbn();
}

// Overload operatore di confronto (diversità)
bool operator!=(Book firstBook, Book secondBook) {
  return firstBook.getIsbn() != secondBook.getIsbn();
}

// Overload operatore di print a stream
std::ostream &operator<<(std::ostream &os, Book book) {
  // Scrittura su stringa dello stato attuale del prestito
  std::string stato = book.bookStatus() ? "Disponibile" : "Fuori";

  return os << "Title: " << book.getTitle() << std::endl
            << "Author: " << book.getAuthor() << std::endl
            << "ISBN: " << book.getIsbn() << std::endl
            << "Copyrigth®: " << book.getCopyrightDate() << std::endl
            << "Disponibilità: " << stato << std::endl;
}