#ifndef RIGHT_ROBOT_H
#define RIGHT_ROBOT_H
#include "./Maze.h"
#include "./Robot.h"

class RightHandRuleRobot : public Robot {
 public:
  RightHandRuleRobot() : dir{Position(0, 0)} {};
  void move(Maze& maze);

 private:
  Position dir;
};

#endif