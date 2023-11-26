#include <iostream>

#include "./include/BookShelf.h"

// Date class tester
void test_date() {
  try {
    Date d1(2023, Date::jun, 31);  // giorno non valido
    std::cout << "Test Fallito! Data invalida per giorno non trovata"
              << std::endl;
  } catch (Date::Invalid) {
    std::cout << "Successo! Data invalida per giorno trovata" << std::endl;
  }

  try {
    Date d2(2023, Date::feb, 29);
    std::cout << "Test Fallito! Data invalida per anno bisestile non trovata"
              << std::endl;
  } catch (Date::Invalid) {
    std::cout << "Successo! Data invalida per giorno trovata" << std::endl;
  }
  std::cout << "Testing output:" << std::endl;

  // default date
  Date d3;
  std::cout << d3 << std::endl;

  Date d4(1950, Date::dec, 30);  // nascita di Bjarne Stroustrup
  std::cout << d4 << std::endl << std::endl;
}

void test_book() {
  Book book("Jane", "Smith", "Another Book", "1234567890123");

  book.rentBook();

  book.returnBook();

  Book book1("Francesca", "Rossi", "Title1", "__CODICEISBN1");
  Book book2("Giulio", "Rossi", "Title1", "__CODICEISBN1");
  Book book3("Pietro", "Rossi", "Title2", "__CODICEISBN2");

  if (book1 == book2 && book1 != book3 && book2 != book3) {
    std::cout << "Successo! operator== okay" << std::endl;
  } else {
    std::cout << "Test Fallito! Problema nell'operator==" << std::endl;
  }
  std::cout << "Testing output:" << std::endl << book1 << std::endl;
}

void test_bookshelf() {
  Date testDate(1950, Date::dec, 30);  // nascita di Bjarne Stroustrup

  BookShelf b = BookShelf();
  Book book1("Francesca", "Rossi", "Book1Title", testDate, "__CODICEISBN1");
  Book book2("Giulio", "Rossi", "Book2Title", "__CODICEISBN2");

  b.push_back(book1);
  b.push_back(book2);

  // test getters
  std::cout << "Posizione 1: " << b.safe_get(0).getTitle() << std::endl;
  std::cout << "Posizione 1: " << b.at(0).getTitle() << std::endl;
  std::cout << "Posizione 2: " << b[1].getTitle() << std::endl;

  // test setting
  std::cout << "Metto Book1 al posto di Book2" << std::endl;
  b.safe_set(1, book1);
  std::cout << "Posizione 2: " << b[1].getTitle() << std::endl << std::endl;
}

int main() {
  std::cout << "Date tester:" << std::endl;
  test_date();

  std::cout << "Book tester:" << std::endl;
  test_book();

  std::cout << "Bookshelf tester:" << std::endl;
  test_bookshelf();
  return 0;
}