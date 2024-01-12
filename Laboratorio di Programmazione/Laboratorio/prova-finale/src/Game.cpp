//
//  Game.cpp
//
//  Creato da Matteo Cuzzolin (2066701)
//

#include "../include/Game.h"

#include <iostream>
#include <map>
#include <memory>

#include "../include/Dice.h"
#include "../include/Player/AIPlayer.h"
#include "../include/Player/HumanPlayer.h"

// Costruttore, gestisce la creazione dell'array dei players, decidendone
// l'ordine di gioco
Game::Game(Dice dice, bool humanPlayer) : human{humanPlayer} {
  board = Board();

  // utilizzo di una multimappa che contiene {risultato del tiro: giocatore}
  std::multimap<int, std::shared_ptr<Player>> playerMap;

  // riempimento della multimappa
  if (humanPlayer) {
    int res = dice.throwDice();
    playerMap.insert(std::make_pair(res, std::make_shared<HumanPlayer>()));
  };

  while (playerMap.size() < 4) {
    int res = dice.throwDice();
    playerMap.insert(std::make_pair(res, std::make_shared<AIPlayer>()));
  }

  // la multimappa in automatico tiene in ordine le sue chiavi, quindi è poi
  // sufficiente iterare sulle coppie chiave valore per ottenere i giocatori
  // ordinati in base al risultato del loro tiro dei dadi
  for (auto pair : playerMap) {
    players.push_back(pair.second);
  }
}

// Restituisce una stringa contenente i giocatori presenti
// all'interno della cella passata tramite posizione
std::string printPlayers(int pos, Game game) {
  std::string value = "";
  switch (game.getBoard().getTiles()[pos]->getType()) {
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
      value += "|P";
      break;
    default:
      break;
  }

  switch (game.getBoard().getTiles()[pos]->getBuilding()) {
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
    if (players[i]->getPosition() == pos)
      value += std::to_string(players[i]->getId());
  }
  value += "|";
  return value;
}

// Stampa la lista delle proprietà possedute dai vari giocatori
std::vector<int> getPlayerProperties(Game& game, std::shared_ptr<Player> p) {
  auto tiles = game.getBoard().getTiles();
  std::vector<int> properties;

  for (int i = 0; i < tiles.size(); i++) {
    if (tiles[i]->getOwner() != nullptr && tiles[i]->getOwner() == p) {
      properties.push_back(i);
    }
  }

  return properties;
}

// Passa al prossimo turno
void Game::nextTurn() {
  // Se esiste un giocatore umano, non incrementa turn
  // poiché non c'è un numero max di turni per il gioco
  if (!human) turn++;
}

// Controlla se il gioco è terminato
bool isOver(Game& game) {
  // Il gioco termina quando:
  //    - C'è un giocatore umano e tutti i giocatori tranne 1 hanno finito i
  //    soldi
  //    - Non c'è un giocatore umano, tutti i giocatori tranne 1 hanno finito i
  //    soldi
  //      oppure i giocatori hanno raggiunto il numero massimo di turni
  return (game.getPlayers().size() <= 1 || (game.getTurn() > game.getLimit()));
}

// Stampa a schermo il gioco
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

  os << "Fiorini posseduti dai giocatori:" << std::endl;
  for (auto player : game.getPlayers()) {
    os << "Giocatore " << player->getId() << ": " << player->getBalance()
       << " fiorini" << std::endl;
  }

  os << "Proprietà possedute dai giocatori:" << std::endl;
  for (auto player : game.getPlayers()) {
    os << "Giocatore " << player->getId() << ": ";
    auto properties = getPlayerProperties(game, player);
    for (int property : properties) {
      std::shared_ptr<Tile> tile = game.getBoard().getTiles()[property];
      os << game.getBoard().positions[property] << "("
         << (tile->getBuilding() == Tile::House ? "House" : "")
         << (tile->getBuilding() == Tile::Hotel ? "Hotel" : "") << ")"
         << ", ";
    }

    os << std::endl;
  }

  return os;
}

// Rimuove un giocatore dal gioco
void Game::removePlayer(std::shared_ptr<Player> player) {
  auto properties = getPlayerProperties(*this, player);

  // Resetta i gli owner delle proprietà possedute dal giocatore eliminato
  for (auto property : properties) {
    board.getTiles()[property]->setOwner(nullptr);
    board.getTiles()[property]->setBuilding(Tile::None);
  }

  // Rimuove il giocatore dalla coda dei giocatori
  for (int i = 0; i < players.size(); i++) {
    if (players[i] == player) {
      players.erase(players.begin() + i);
    }
  }
}