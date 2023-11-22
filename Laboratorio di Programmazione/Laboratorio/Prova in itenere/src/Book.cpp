#include "../include/Book.h"

#include <iostream>

void Book::registerCopyright(Date data){
  if(copyrightDate == Date()){
    copyrightDate = data;
  } else {
    std::cout << "Errore! La data di copyright era già stata impostata" << std::endl;
  }
}

Book::Book(std::string aName, std::string aSurname, std::string title,
           std::string ISBN)
    : authorName{aName},
      authorSurname{aSurname},
      bookTitle{title},
      bookISBN{ISBN} {
  if (bookISBN.length() != 13) throw Invalid();
}

Book::Book(std::string aName, std::string aSurname, std::string title,
           Date data, std::string ISBN)
    : authorName{aName},
      authorSurname{aSurname},
      bookTitle{title},
      copyrightDate{data},
      bookISBN{ISBN} {
  if (bookISBN.length() != 13) throw Invalid();
}

void Book::rentBook() {
  if (status) {
    std::cout << "Il libro " << bookTitle << " è stato prestato" << std::endl;
    status = Fuori;
  } else {
    std::cout << "Il libro " << bookTitle << " non disponibile per il prestito"
              << std::endl;
  }
}

void Book::returnBook() {
  if (!status) {
    std::cout << "Il libro " << bookTitle << " è stato ritirato" << std::endl;
    status = Disponibile;
  } else {
    std::cout << "Il libro " << bookTitle << " non era stato prestato"
              << std::endl;
  }
}

bool operator==(Book firstBook, Book secondBook) {
  return firstBook.getIsbn() == secondBook.getIsbn();
}

bool operator!=(Book firstBook, Book secondBook) {
  return firstBook.getIsbn() != secondBook.getIsbn();
}

std::ostream &operator<<(std::ostream &os, Book book) {
  std::string stato = book.bookStatus() ? "Disponibile" : "Fuori";

  return os << "Title: " << book.getTitle() << std::endl
            << "Author: " << book.getAuthor() << std::endl
            << "ISBN: " << book.getIsbn() << std::endl
            << "Copyrigth®: " << book.getDate() << std::endl
            << "Disponibilità: " << stato << std::endl;
}