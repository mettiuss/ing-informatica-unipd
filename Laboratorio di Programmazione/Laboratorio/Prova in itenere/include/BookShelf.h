#include "Book.h"

class BookShelf {
  class Invalid {};

 public:
  BookShelf();
  BookShelf(int dimension);  // constructor with reserve
  ~BookShelf();              // destructor

  int getSize() { return size; }  // return how many items are saved
  void safe_set(int index, Book value);
  Book safe_get(int index) const;

  Book &operator[](int index);
  Book operator[](int index) const;

  Book &at(int index);  // return a reference to a specific address
  const Book &at(
      int index) const;  // return a const_reference to a specific address

  void push_back(Book value);  // add an element at the end of the vector
  Book pop_back();             // remove and return the last element

  void reserve(int dimension);  // set the minimum dimension for the buffer

 private:
  int size = 0, max_size = 0, reserved = 0;
  Book *b = nullptr;
  void resize(int newDim);
};
