#ifndef MAZE_H
#define MAZE_H

#include <iostream>
#include <vector>

class Maze {
 public:
  class Position {
   private:
    int x, y;

   public:
    Position() : x{0}, y{0} {};
    Position(int xPos, int yPos) : x{xPos}, y{yPos} {};
    std::vector<int> getPos();
    void setPos(int xPos, int yPos);
  };

  class Invalid {};
  Maze(std::string fileName);
  void setRobotPos(int x, int y);
  bool robotInExit();
  const std::vector<std::vector<bool>> getMatrix() const { return matrix; };
  const Position getRobot() const { return robot; };
  const std::vector<Position> getExits() const { return exits; };
  std::vector<Position> getAvaiableMoves();

 private:
  std::vector<std::vector<bool>> matrix;
  Position robot;
  std::vector<Position> exits;
  bool isPosValid(int x, int y);
};

std::ostream &operator<<(std::ostream &stream, Maze operand);

std::ostream &operator<<(std::ostream &stream, Maze::Position operand);
bool operator==(Maze::Position firstPos, Maze::Position secondPos);

#endif
