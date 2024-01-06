//
//  Tile.h
//
//  Creato da Matteo Cuzzolin (2066701)
//

#ifndef TILE_H
#define TILE_H

#include <iostream>
#include <map>
#include <memory>

#include "./Player/Player.h"

// Specifica una singola Casella della tabella di Gioco, memorizza
// le informazioni statiche della casella, quindi il tipo e gli eventuali
// edifici.
// Memorizza anche a chi appartiene la casella, utilizzando uno shared_ptr,
// soluzione particolarmente comoda perché permette di non specificare qui se
// viene scelto HumanPlayer o AIPlayer. E lo shared_ptr può essere condiviso tra
// più oggetti di tipo Tile, cosa che accade di continuo nel gioco.
// Non memorizza la posizione dei giocatori, compito della classe Player

class Tile {
 public:
  enum TileType { Corner, StartingCorner, Economic, Standard, Luxury };
  enum Building { None, House, Hotel };
  std::map<TileType, int> propertyPrice = {
      {Economic, 6}, {Standard, 10}, {Luxury, 20}};
  std::map<TileType, int> housePrice = {
      {Economic, 3}, {Standard, 5}, {Luxury, 10}};
  std::map<TileType, int> hotelPrice = {
      {Economic, 3}, {Standard, 5}, {Luxury, 10}};
  std::map<TileType, int> rentPriceHouse = {
      {Economic, 2}, {Standard, 4}, {Luxury, 7}};
  std::map<TileType, int> rentPriceHotel = {
      {Economic, 4}, {Standard, 8}, {Luxury, 14}};

  Tile(TileType t) : type{t}, building{None}, owner{nullptr} {};

  TileType getType() const { return type; };

  void setBuilding(Building b) { building = b; };
  Building getBuilding() const { return building; };

  void setOwner(std::shared_ptr<Player> p) { owner = p; };
  std::shared_ptr<Player> getOwner() const { return owner; };

 private:
  TileType type;
  Building building;
  std::shared_ptr<Player> owner;
};

void buyOrUpgrade(Tile& tile, std::shared_ptr<Player> player);

void rent(Tile& tile, std::shared_ptr<Player> player);

bool canRent(Tile& tile, std::shared_ptr<Player> player);

std::ostream& operator<<(std::ostream& os, Tile t);

#endif