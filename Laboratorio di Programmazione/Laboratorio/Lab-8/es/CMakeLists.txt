cmake_minimum_required(VERSION 2.8...3.5 FATAL_ERROR)

project(maze VERSION 1.0.0 LANGUAGES CXX)

add_library(Maze include/Maze.h src/Maze.cpp)
add_library(Position include/Position.h src/Position.cpp)
add_library(Robot include/Robot.h src/Robot.cpp)
add_library(RandomRobot include/RandomRobot.h src/RandomRobot.cpp)
add_library(RightHandRuleRobot include/RightHandRuleRobot.h src/RightHandRuleRobot.cpp)
configure_file(maze.txt maze.txt COPYONLY)

add_executable(Main main.cpp)

target_link_libraries(Main Maze Position Robot RandomRobot RightHandRuleRobot)