//
//  Game.h
//
//  Creato da Matteo Cuzzolin (2066701)
//

#ifndef GAME_H
#define GAME_H

#include <iostream>
#include <memory>
#include <vector>

#include "./Board.h"
#include "./Dice.h"
#include "./Player/Player.h"

// Classe che gestisce la partita (quindi i suoi turni e i giocatori) e coordina
// le interazioni tra tutte le altre classi.

class Game {
 public:
  Game(Dice& dice, bool humanPlayer);

  // getters
  int getTurn() const { return turn; }
  int getLimit() const { return limit; }
  Board& getBoard() { return board; }
  const Board& getBoard() const { return board; }
  const std::vector<std::shared_ptr<Player>>& getPlayers() const {
    return players;
  };

  // funzione per eliminare un giocatore, ne libera tutte le caselle e lo
  // rimuove dal vettore di giocatori
  void removePlayer(std::shared_ptr<Player> player);

  // triggher per tenere conto del turno, utile per limitare le partite AI
  void nextTurn();

 private:
  bool human;
  int turn = 0, limit = 20;
  Board board;
  std::vector<std::shared_ptr<Player>> players;
};

// true se il gico è finito, quindi se è rimasto solo un giocatore o se i turni
// sono finito
bool isOver(Game& game);

// Ritorna una lista con le proprietà di uno specifico giocatore
std::vector<int> getPlayerProperties(Game& game,
                                     std::shared_ptr<Player> player);

std::ostream& operator<<(std::ostream& os, Game game);

#endif