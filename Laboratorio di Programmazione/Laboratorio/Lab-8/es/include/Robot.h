#ifndef ROBOT_H
#define ROBOT_H
#include "./Maze.h"

class Robot {
 protected:
  Robot(){};

 public:
  virtual void move(Maze& maze) = 0;
};

#endif