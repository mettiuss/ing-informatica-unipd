//
//  Tile.cpp
//
//  Creato da Matteo Cuzzolin (2066701)
//

#include "../include/Tile.h"

#include <iostream>
#include <memory>

#include "../include/Board.h"

std::ostream& operator<<(std::ostream& os, std::shared_ptr<Tile> t) {
  switch (t->getType()) {
    case Tile::Economic:
      os << "|E";
      break;
    case Tile::Standard:
      os << "|S";
      break;
    case Tile::Luxury:
      os << "|L";
      break;
    case Tile::Corner:
      os << "| ";
      break;
    case Tile::StartingCorner:
      os << "|>";
      break;
    default:
      break;
  }

  switch (t->getBuilding()) {
    case Tile::House:
      os << "*|";
      break;
    case Tile::Hotel:
      os << "^|";
      break;
    default:
      os << "|";
      break;
  }

  return os;
}
