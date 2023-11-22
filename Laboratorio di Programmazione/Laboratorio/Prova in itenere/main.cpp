#include <iostream>

#include "./include/BookShelf.h"

// Date class tester
void test_date() {
  try {
    Date d1(2023, Date::jun, 31); // invalid day
  } catch (Date::Invalid) {
    std::cout << "Invalid date caught by day" << std::endl;
  }

  try {
    Date d2(2024, Date::feb, 29); // leap year check
  } catch (Date::Invalid) {
    std::cout << "Invalid date caught by leap year" << std::endl;
  }
  // default date
  Date d3;
  std::cout << d3 << std::endl;

  Date d4(1950, Date::dec, 30); // nascita di Bjarne Stroustrup
  std::cout << d4 << std::endl;
}

void test_book() {
  Book book("Jane", "Smith", "Another Book", "1234567890123");

  book.rentBook();

  book.returnBook();

  Book book1("Francesca", "Rossi", "Title1", "__CODICEISBN1");
  Book book2("Giulio", "Rossi", "Title1", "__CODICEISBN1");
  Book book3("Pietro", "Rossi", "Title2", "__CODICEISBN2");

  if (book1 == book2 && book1 != book3 && book2 != book3) {
    std::cout << "operator== okay" << std::endl;
  } else {
    std::cout << "Problema nell'operator==" << std::endl;
  }
  std::cout << book1;
}

void test_bookshelf() {
  Date invalidDate(2008, Date::feb, 29);
  Date copyright(2023, Date::nov, 22);
  Date d2(1950, Date::dec, 30);  // nascita di Bjarne Stroustrup

  BookShelf b = BookShelf();
  Book book1("Francesca", "Rossi", "Title1", d2, "__CODICEISBN1");
  Book book2("Giulio", "Rossi", "Title1", "__CODICEISBN1");
  Book book3("Pietro", "Rossi", "Title2", invalidDate, "__CODICEISBN2");

  book2.registerCopyright(copyright);
  book3.registerCopyright(d2);

  b.push_back(book1);
  b.push_back(book2);

  std::cout << "Posizione 1: " << b.safe_get(0) << std::endl;
  std::cout << "Posizione 2: " << b[1] << std::endl << std::endl;

  std::cout << "Risetto il libro nella seconda posizione..." << std::endl;
  b.safe_set(1, book3);
  b[1].rentBook();
  std::cout << "Posizione 2: " << b[1] << std::endl << std::endl;
  b[1].rentBook();
  b[1].returnBook();
  b[1].returnBook();
}

int main() {
  // test_date();
  // test_book();
  test_bookshelf();
  return 0;
}