cmake_minimum_required(VERSION 3.0 FATAL_ERROR)

project(Bookshelf VERSION 1.0.0 LANGUAGES CXX)

add_library(Date include/Date.h src/Date.cpp)
add_library(Book include/Book.h src/Book.cpp)
add_library(Bookshelf include/Bookshelf.h src/Bookshelf.cpp)

add_executable(MyBookShelf main.cpp)

target_link_libraries(MyBookShelf Date Book Bookshelf)