cmake_minimum_required(VERSION 2.8...3.5 FATAL_ERROR)

project(Monopoli VERSION 1.0.0 LANGUAGES CXX)


add_library(Player include/Player/Player.h src/Player/Player.cpp)
add_library(HumanPlayer include/Player/HumanPlayer.h src/Player/HumanPlayer.cpp)
add_library(AIPlayer include/Player/AIPlayer.h src/Player/AIPlayer.cpp)
add_library(Dice include/Dice.h src/Dice.cpp)
add_library(Board include/Board.h src/Board.cpp)
add_library(Tile include/Tile.h src/Tile.cpp)
add_library(Game include/Game.h src/Game.cpp)

add_executable(Tester tester.cpp)

target_link_libraries(Tester Player HumanPlayer AIPlayer Dice Board Tile Game)