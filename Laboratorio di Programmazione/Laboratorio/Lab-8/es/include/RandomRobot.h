#ifndef RANDOM_ROBOT_H
#define RANDOM_ROBOT_H
#include "./Maze.h"
#include "./Robot.h"

class RandomRobot : public Robot {
 public:
  void move(Maze& maze);
};

#endif