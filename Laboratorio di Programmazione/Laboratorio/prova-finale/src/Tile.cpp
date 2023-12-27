#include "../include/Tile.h"

#include <iostream>

std::ostream& operator<<(std::ostream& os, Tile t) {
  switch (t.getType()) {
    case Tile::Economic:
      return os << "|E|";
    case Tile::Standard:
      return os << "|S|";
    case Tile::Luxury:
      return os << "|L|";
    case Tile::Corner:
      return os << "| |";
    case Tile::StartingCorner:
      return os << "|>|";
    default:
      return os;
  }
}
