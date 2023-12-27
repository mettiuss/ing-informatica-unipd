#ifndef BOARD
#define BOARD

#include <iostream>
#include <memory>
#include <vector>

#include "./Tile.h"

class Board {
 public:
  Board();
  const std::vector<Tile>& getTiles() const { return tiles; };

 private:
  std::vector<Tile> tiles;
};

std::ostream& operator<<(std::ostream& os, Board board);

#endif