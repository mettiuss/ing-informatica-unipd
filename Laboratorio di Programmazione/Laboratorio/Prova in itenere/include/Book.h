#include <iostream>

#include "Date.h"

class Book {
  class Invalid {};

 public:
  enum Status { Disponibile = true, Fuori = false };

  Book() : authorName{""}, authorSurname{""}, bookTitle{""}, bookISBN{""} {}
  Book(std::string aName, std::string aSurname, std::string title,
       std::string ISBN);
  Book(std::string aName, std::string aSurname, std::string title, Date data,
       std::string ISBN);

  std::string getAuthor() { return authorName + " " + authorSurname; }
  std::string getAuthorName() { return authorName; }
  std::string getAuthorSurname() { return authorSurname; }
  std::string getTitle() { return bookTitle; }
  Date getDate() { return copyrightDate; }
  std::string getIsbn() { return bookISBN; }

  void registerCopyright(Date data);

  void rentBook();
  void returnBook();
  Status bookStatus() { return status; }

 private:
  std::string authorName, authorSurname, bookTitle, bookISBN;
  Date copyrightDate = Date();
  Status status = Disponibile;
};

bool operator==(Book firstBook, Book secondBook);
bool operator!=(Book firstBook, Book secondBook);
std::ostream &operator<<(std::ostream &os, Book book);
