//
//  Tile.h
//
//  Creato da Matteo Cuzzolin (2066701)
//

#ifndef TILE_H
#define TILE_H

#include <iostream>
#include <memory>

#include "./Player/Player.h"

// Specifica una singola Casella della tabella di Gioco, memorizza
// le informazioni statiche della casella, quindi il tipo e gli eventuali
// edifici. Non memorizza la posizione dei giocatori, compito della classe
// Player
class Tile {
 public:
  enum TileType { Corner, StartingCorner, Economic, Standard, Luxury };
  enum Building { None, House, Hotel };

  Tile(TileType t) : type{t}, building{None}, owner{nullptr} {};

  TileType getType() const { return type; };

  void setBuilding(Building b) { building = b; };
  Building getBuilding() { return building; };

  void setOwner(std::shared_ptr<Player> p) { owner = p; };
  std::shared_ptr<Player> getOwner() { return owner; };

 private:
  TileType type;
  Building building;
  std::shared_ptr<Player> owner;
};

std::ostream& operator<<(std::ostream& os, Tile t);

#endif