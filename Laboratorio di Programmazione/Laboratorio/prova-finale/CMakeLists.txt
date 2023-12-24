cmake_minimum_required(VERSION 2.8...3.5 FATAL_ERROR)

project(Monopoli VERSION 1.0.0 LANGUAGES CXX)

set(CMAKE_RUNTIME_OUTPUT_DIRECTORY "${CMAKE_CURRENT_SOURCE_DIR}/build")

add_library(Player include/Player/Player.h src/Player/Player.cpp)
add_library(HumanPlayer include/Player/HumanPlayer.h src/Player/HumanPlayer.cpp)
add_library(AIPlayer include/Player/AIPlayer.h src/Player/AIPlayer.cpp)
add_library(Dice include/Dice.h src/Dice.cpp)

add_executable(Tester tester.cpp)

target_link_libraries(Tester Player HumanPlayer AIPlayer Dice)