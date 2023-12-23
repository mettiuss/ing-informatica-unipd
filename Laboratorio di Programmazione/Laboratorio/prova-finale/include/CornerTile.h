#ifndef CORNER_TILE_H
#define CORNER_TILE_H

#include "./Tile.h"

class CornerTile : public Tile {
 public:
  enum CornerTileType { StartingCorner = true, Corner = false };

  CornerTile(CornerTileType t = Corner) : type{t} {};
  CornerTileType getType() { return type; };

 private:
  CornerTileType type;
};

#endif
