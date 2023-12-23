#ifndef LATERAL_TILE_H
#define LATERAL_TILE_H

#include "./Building.h"
#include "./Tile.h"

class LateralTile : public Tile {
 public:
  enum LateralTileType { Economic, Standard, Luxury };
  LateralTile(LateralTileType t = Standard) : type{t} {};
  LateralTileType getType() { return type; };
  void setBuilding(Building b) { building = b; };
  Building getBuilding() { return building; };

 private:
  LateralTileType type;
  Building building;
};

#endif