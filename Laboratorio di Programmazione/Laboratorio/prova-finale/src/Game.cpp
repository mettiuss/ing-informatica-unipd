#include "../include/Game.h"

#include <iostream>
#include <map>
#include <memory>

#include "../include/Dice.h"
#include "../include/Player/AIPlayer.h"
#include "../include/Player/HumanPlayer.h"

Game::Game(Dice dice, bool humanPlayer) : human{humanPlayer} {
  board = Board();

  std::multimap<int, std::shared_ptr<Player>> playerMap;

  if (humanPlayer) {
    int res = dice.throwDice();
    playerMap.insert(std::make_pair(res, std::make_shared<HumanPlayer>()));
  };

  while (playerMap.size() < 4) {
    int res = dice.throwDice();
    playerMap.insert(std::make_pair(res, std::make_shared<AIPlayer>()));
  }

  for (auto pair = playerMap.rbegin(); pair != playerMap.rend(); ++pair) {
    players.push_back(pair->second);
  }
}

std::string printPlayers(int pos, Game game) {
  std::string value = "";
  switch (game.getTiles()[pos].getType()) {
    case Tile::Economic:
      value += "|E";
      break;
    case Tile::Standard:
      value += "|S";
      break;
    case Tile::Luxury:
      value += "|L";
      break;
    case Tile::Corner:
      value += "| ";
      break;
    case Tile::StartingCorner:
      value += "|>";
      break;
    default:
      break;
  }

  switch (game.getTiles()[pos].getBuilding()) {
    case Tile::House:
      value += "*";
      break;
    case Tile::Hotel:
      value += "^";
      break;
    default:
      break;
  }
  auto players = game.getPlayers();

  for (int i = 0; i < players.size(); i++) {
    if (players[i]->getPosition() == pos) value += std::to_string(i + 1);
  }
  value += "|";
  return value;
}

std::vector<int> Game::getPlayerProperties(std::shared_ptr<Player> p) const {
  auto tiles = getTiles();
  std::vector<int> properties;

  for (int i = 0; i < tiles.size(); i++) {
    if (tiles[i].getOwner() == p) {
      std::cout << i << std::endl;
      properties.push_back(i);
    }
  }

  return properties;
}

void Game::nextTurn() {
  if (!human) turn++;
}

bool isOver(Game& game) {
  return (game.getPlayers().size() <= 1 || (game.getTurn() > game.getLimit()));
}

std::ostream& operator<<(std::ostream& os, Game game) {
  os << "   ";

  // indici numerici 1...8
  for (int i = 1; i < 9; i++) {
    os << " " << i << " ";
  }
  os << std::endl;

  // stampa le colonne
  for (int i = 0; i < 8; i++) {
    // lettera iniziale A...H
    os << " " << char(i + 65) << " ";

    // gestisco separatamente i casi della prima e l'ultima riga, che vanno
    // riempite completamente
    if (i == 0) {
      for (int j = 0; j < 8; j++) {
        os << printPlayers(j, game);
      }
      os << std::endl;
      continue;
    }

    if (i == 7) {
      for (int j = 21; j > 13; j--) {
        os << printPlayers(j, game);
      }
      os << std::endl;
      continue;
    }

    // riempio le righe di indice 1...6
    os << printPlayers(28 - i, game) << "   "
       << "   "
       << "   "
       << "   "
       << "   "
       << "   " << printPlayers(i + 7, game) << std::endl;
  }
  os << std::endl;

  return os;
}

void Game::removePlayer(std::shared_ptr<Player> player) {
  auto properties = getPlayerProperties(player);

  for (auto property : properties) {
    getTiles()[property].setOwner(nullptr);
    getTiles()[property].setBuilding(Tile::None);
  }

  for (int i = 0; i < players.size(); i++) {
    if (players[i] == player) {
      players.erase(players.begin() + i);
    }
  }
}