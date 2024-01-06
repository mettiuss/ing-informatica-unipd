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
  Board();

  std::vector<Tile>& getTiles() { return tiles; };
  const std::vector<Tile>& getTiles() const { return tiles; };

 private:
  std::vector<Tile> tiles;
};

std::ostream& operator<<(std::ostream& os, Board board);

#endif