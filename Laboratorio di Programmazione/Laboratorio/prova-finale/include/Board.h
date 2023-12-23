#ifndef BOARD
#define BOARD

#include <vector>

#include "./Tile.h"

class Board {
 public:
  Board();

 private:
  std::vector<Tile> tiles;
};

#endif