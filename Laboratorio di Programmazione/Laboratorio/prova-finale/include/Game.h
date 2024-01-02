#ifndef GAME_H
#define GAME_H

#include <iostream>
#include <memory>
#include <vector>

#include "./Board.h"
#include "./Player/Player.h"
#include "./Tile.h"

class Game {
 public:
  Game(bool humanPlayer = false);

  const std::vector<Tile>& getTiles() const { return board.getTiles(); };
  const std::vector<std::shared_ptr<Player>>& getPlayers() const {
    return players;
  };

 private:
  Board board;
  std::vector<std::shared_ptr<Player>> players;
};

std::ostream& operator<<(std::ostream& os, Game game);

#endif