#include "../include/Board.h"

#include <unistd.h>

#include <iostream>
#include <memory>
#include <vector>

#include "../include/Tile.h"

Board::Board() {
  srand(time(NULL));

  // set starting tile
  tiles.push_back(Tile(Tile::StartingCorner));

  // set the other tiles
  std::vector<int> count{8, 10, 6};
  for (int i = 1; i < 28; i++) {
    if (i % 7 == 0) {
      tiles.push_back(Tile(Tile::Corner));
      continue;
    }
    int randomNumber = std::rand() % 3;

    while (count[randomNumber] <= 0) {
      randomNumber = (randomNumber + 1) % 3;
    }

    count[randomNumber]--;

    Tile::TileType type;
    switch (randomNumber) {
      case 0:
        type = Tile::Economic;
        break;

      case 1:
        type = Tile::Standard;
        break;

      case 2:
        type = Tile::Luxury;
        break;
    }

    tiles.push_back(Tile(type));
  }
}

std::ostream& operator<<(std::ostream& os, Board board) {
  os << "   ";

  for (int i = 1; i < 9; i++) {
    os << " " << i << " ";
  }
  os << std::endl;

  for (int i = 0; i < 8; i++) {
    os << " " << char(i + 65) << " ";
    if (i == 0) {
      for (int j = 0; j < 8; j++) {
        os << board.getTiles()[j];
      }
      os << std::endl;
      continue;
    }

    if (i == 7) {
      for (int j = 21; j > 13; j--) {
        os << board.getTiles()[j];
      }
      os << std::endl;
      continue;
    }

    os << board.getTiles()[28 - i] << "   "
       << "   "
       << "   "
       << "   "
       << "   "
       << "   " << board.getTiles()[i + 7] << std::endl;
  }
  os << std::endl;

  return os;
}