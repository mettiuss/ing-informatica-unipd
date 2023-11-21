#include "./include/BookShelf.h"
#include <iostream>

// Date class tester


void test_bookshelf() {
  Date invalidDate(2008, Date::feb, 29);
  Date d2(1950, Date::dec, 30); // nascita di Bjarne Stroustrup

  BookShelf b = BookShelf();
  Book book1("Francesca", "Rossi", "Title1", d2, "__CODICEISBN1");
  Book book2("Giulio", "Rossi", "Title1", "__CODICEISBN1");
  Book book3("Pietro", "Rossi", "Title2", invalidDate, "__CODICEISBN2");

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