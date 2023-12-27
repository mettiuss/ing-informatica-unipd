#ifndef TILE_H
#define TILE_H

#include <iostream>

#include "./Building.h"

class Tile {
 public:
  enum TileType { Corner, StartingCorner, Economic, Standard, Luxury };

  Tile(TileType t) : type{t} {};

  TileType getType() const { return type; };

  void setBuilding(Building b) { building = b; };
  Building getBuilding() { return building; };

 private:
  TileType type;
  Building building;
};

std::ostream& operator<<(std::ostream& os, Tile t);

#endif