//
//  Building.h
//
//  Creato da Matteo Cuzzolin (2066701)
//

#ifndef BOARD
#define BOARD

#include <iostream>
#include <memory>
#include <vector>

#include "./Tile.h"

// Il Tabellone di Gioco, una collezione di 28 tiles, con un costruttore che
// permette di generare facilmente il tabellone con caselle casuali.
class Board {
 public:
  Board();

  const std::vector<Tile>& getTiles() const { return tiles; };

 private:
  std::vector<Tile> tiles;
};

std::ostream& operator<<(std::ostream& os, Board board);

#endif