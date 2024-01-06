#ifndef GAME_H
#define GAME_H

#include <iostream>
#include <memory>
#include <vector>

#include "./Board.h"
#include "./Dice.h"
#include "./Player/Player.h"
#include "./Tile.h"

class Game {
 public:
  Game(Dice dice, bool humanPlayer);

  std::vector<Tile>& getTiles() { return board.getTiles(); };
  const std::vector<Tile>& getTiles() const { return board.getTiles(); };
  const std::vector<std::shared_ptr<Player>>& getPlayers() const {
    return players;
  };
  void removePlayer(std::shared_ptr<Player> player);

  int getTurn() const { return turn; }
  int getLimit() const { return limit; }

  std::vector<int> getPlayerProperties(std::shared_ptr<Player> p) const;

  void nextTurn();

 private:
  bool human;
  int turn = 0, limit = 20;
  Board board;
  std::vector<std::shared_ptr<Player>> players;
};

bool isOver(Game& game);

std::ostream& operator<<(std::ostream& os, Game game);

#endif