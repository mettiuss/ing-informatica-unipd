//
//  Building.h
//
//  Creato da Matteo Cuzzolin (2066701)
//

#ifndef BOARD_H
#define BOARD_H

#include <iostream>
#include <memory>
#include <vector>

#include "./Tile.h"

// Il Tabellone di Gioco, una collezione di 28 tiles, con un costruttore che
// permette di generare facilmente il tabellone con caselle casuali.
// Board utilizza un'un semplice array per memorizzare il tabellone, gli indici
// partono dalla casella in alto a sinistra, per poi seguire verso destra in
// senso orario.
/*
     1   2   3   4   5   6   7   8
 A | 0|| 1|| 2|| 3|| 4|| 5|| 6|| 7|
 B |27|                        | 8|
 C |26|                        | 9|
 D |25|                        |10|
 E |24|                        |11|
 F |23|                        |12|
 G |22|                        |13|
 H |21||20||19||18||17||16||15||14|
*/
class Board {
 public:
  // Link da indici al nome della posizione
  std::string positions[28] = {"A1", "A2", "A3", "A4", "A5", "A6", "A7",
                               "A8", "B8", "C8", "D8", "E8", "F8", "G8",
                               "H8", "H7", "H6", "H5", "H4", "H3", "H2",
                               "H1", "G1", "F1", "E1", "D1", "C1", "B1"};
  Board();

  // getter
  std::vector<std::shared_ptr<Tile>>& getTiles() { return tiles; };
  const std::vector<std::shared_ptr<Tile>>& getTiles() const { return tiles; };

 private:
  std::vector<std::shared_ptr<Tile>> tiles;
};

// Controlla se il giocatore, nella sua posizione attuale e con il suo attuale
// denaro può comprare o migliorare il terreno
bool canBuyOrUpgrade(Board& board, std::shared_ptr<Player> player);

// Acquista o migliorara il terreno su cui il giocatore si trova
void buyOrUpgrade(Board& board, std::shared_ptr<Player> player,
                  void (*writeLog)(std::shared_ptr<Player>, std::string));

// Ritorna la corretta domanda da porre al giocatore umano, data la sua
// posizione
std::string questionBuyOrUpgrade(Board& board, std::shared_ptr<Player> player);

// Controlla se il giocatore ha abbastanza fiorini per soggiornare
bool canRent(Board& board, std::shared_ptr<Player> player);

// Paga l'altro giocatore per il soggiorno
void rent(Board& board, std::shared_ptr<Player> player,
          void (*writeLog)(std::shared_ptr<Player>, std::string));

#endif