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

  std::string getAuthor() const { return authorName + " " + authorSurname; }
  std::string getAuthorName() const { return authorName; }
  std::string getAuthorSurname() const { return authorSurname; }
  std::string getTitle() const { return bookTitle; }
  Date getCopyrightDate() const { return copyrightDate; }
  std::string getIsbn() const { return bookISBN; }

  void registerCopyright(Date data);

  void rentBook();
  void returnBook();
  Status bookStatus() const { return status; }

 private:
  std::string authorName, authorSurname, bookTitle, bookISBN;
  Date copyrightDate = Date();
  Status status = Disponibile;
};

bool operator==(Book firstBook, Book secondBook);
bool operator!=(Book firstBook, Book secondBook);
std::ostream &operator<<(std::ostream &os, Book book);
