#ifndef GAME_H
#define GAME_H

#include <iostream>
#include <memory>
#include <vector>

#include "./Board.h"
#include "./Player/Player.h"
#include "./Tile.h"

class Game {
  enum Position{ A1 = 0, A2, A3, A4, A5, A6, A7, A8, B8, C8, D8, E8, F8, G8, H8, H7, H6, H5, H4, H3, H2, H1, G1, F1, E1, D1, C1, B1 };

 public:
  Game(bool humanPlayer = false);

  const std::vector<Tile>& getTiles() const { return board.getTiles(); };
  const std::vector<std::shared_ptr<Player>>& getPlayers() const {
    return players;
  };

  const std::vector<int>& getPlayerProperties(std::shared_ptr<Player> p) const;

 private:
  Board board;
  std::vector<std::shared_ptr<Player>> players;
};

std::ostream& operator<<(std::ostream& os, Game game);

#endif