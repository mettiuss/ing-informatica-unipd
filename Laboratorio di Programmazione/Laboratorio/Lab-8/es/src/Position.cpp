#include "../include/Position.h"

#include <iostream>
#include <vector>

void Position::setPos(int xx, int yy) {
  x = xx;
  y = yy;
}

std::ostream &operator<<(std::ostream &stream, Position operand) {
  return stream << "(" << operand.getX() << ", " << operand.getY() << ")";
}

bool operator==(Position firstPos, Position secondPos) {
  return firstPos.getX() == secondPos.getX() &&
         firstPos.getY() == secondPos.getY();
}