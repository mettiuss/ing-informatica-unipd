#ifndef POSITION_H
#define POSITION_H

#include <iostream>
#include <vector>

class Position {
 public:
  // constructors
  Position() : x{0}, y{0} {};
  Position(int xx, int yy) : x{xx}, y{yy} {};

  int getX() { return x; }
  int getY() { return y; }
  void setPos(int xx, int yy);

 private:
  int x, y;
};

std::ostream &operator<<(std::ostream &stream, Position operand);
bool operator==(Position firstPos, Position secondPos);

#endif