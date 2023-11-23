#include "../include/BookShelf.h"

#include <iostream>

BookShelf::BookShelf() {
  size = 0;
  max_size = 10;
  b = new Book[max_size];
}

BookShelf::BookShelf(int dimension) {
  if (dimension <= 0) throw Invalid();
  size = 0;
  max_size = dimension;
  b = new Book[dimension];
}

BookShelf::~BookShelf() { delete[] b; }

void BookShelf::safe_set(int index, Book value) {
  if (index < 0 || index >= size) throw Invalid();
  b[index] = value;
}

Book BookShelf::safe_get(int index) const {
  if (index < 0 || index >= size) throw Invalid();
  return b[index];
}

Book &BookShelf::at(int index) {
  if (index < 0 || index >= size) throw Invalid();
  return b[index];
}

const Book &BookShelf::at(int index) const {
  if (index < 0 || index >= size) throw Invalid();
  return b[index];
}

void BookShelf::push_back(Book value) {
  if (max_size == 0) {
    resize(10);
  } else if (size == max_size) {
    resize(max_size * 2);
  }

  b[size] = value;
  size++;
}

Book BookShelf::pop_back() {
  size--;
  return b[size];
}

Book &BookShelf::operator[](int index) { return b[index]; }

Book BookShelf::operator[](int index) const { return b[index]; }

void BookShelf::reserve(int dimension) {
  if (dimension > max_size) resize(dimension);
  reserved = dimension;
}

void BookShelf::resize(int dimension) {
  Book *newArray;

  if (dimension > reserved) {
    newArray = new Book[dimension];
    max_size = dimension;
  } else {
    newArray = new Book[reserved];
    max_size = reserved;
  }

  std::copy(b, b + size, newArray);

  delete[] b;

  b = newArray;
}