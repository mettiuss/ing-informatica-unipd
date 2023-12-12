#ifndef MAZE_H
#define MAZE_H

#include <iostream>
#include <vector>

#include "./Position.h"

class Maze {
 public:
  class Invalid {};

  Maze(std::string fileName);

  const std::vector<std::vector<bool>> getMatrix() const { return matrix; };
  const Position getRobot() const { return robot; };
  const std::vector<Position> getExits() const { return exits; };

  void setRobotPos(Position pos);
  bool robotInExit();
  bool isRobotValidPos(Position pos);

 private:
  std::vector<std::vector<bool>> matrix;
  Position robot;
  std::vector<Position> exits;
  bool isPosValid(Position pos);
};

std::ostream &operator<<(std::ostream &stream, Maze operand);

#endif
