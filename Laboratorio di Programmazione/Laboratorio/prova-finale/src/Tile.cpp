//
//  Tile.cpp
//
//  Creato da Matteo Cuzzolin (2066701)
//

#include "../include/Tile.h"

#include <iostream>

std::ostream& operator<<(std::ostream& os, Tile t) {
  switch (t.getType()) {
    case Tile::Economic:
      os << "|E";
      break;
    case Tile::Standard:
      os << "|S";
      break;
    case Tile::Luxury:
      os << "|L";
      break;
    case Tile::Corner:
      os << "| ";
      break;
    case Tile::StartingCorner:
      os << "|>";
      break;
    default:
      break;
  }

  switch (t.getBuilding()) {
    case Tile::House:
      os << "*|";
      break;
    case Tile::Hotel:
      os << "^|";
      break;
    default:
      os << "|";
      break;
  }

  return os;
}

void buyOrUpgrade(Tile& tile, int tileIndex, std::shared_ptr<Player> player,
                  void (*writeLog)(std::shared_ptr<Player>, std::string)) {
  if (tile.getOwner() == nullptr) {
    // acquisto
    tile.setOwner(player);
    player->removeBalance(tile.propertyPrice[tile.getType()]);

    writeLog(player,
             "ha acquistato il terreno " + player->positions[tileIndex]);
    return;
  }
  if (tile.getBuilding() == Tile::None) {
    // costruisce casa
    tile.setBuilding(Tile::House);
    player->removeBalance(tile.housePrice[tile.getType()]);
    writeLog(player, "ha costruito una casa sul terreno " +
                         player->positions[tileIndex]);
    return;
  }
  if (tile.getBuilding() == Tile::House) {
    // costruisce albergo
    tile.setBuilding(Tile::Hotel);
    player->removeBalance(tile.hotelPrice[tile.getType()]);
    writeLog(player, "ha migliorato una casa in albergo sul terreno" +
                         player->positions[tileIndex]);
    return;
  }
}

bool canBuyOrUpgrade(Tile& tile, std::shared_ptr<Player> player) {
  if (tile.getOwner() == nullptr) {
    return player->getBalance() > tile.propertyPrice[tile.getType()];
  }
  if (tile.getBuilding() == Tile::None) {
    return player->getBalance() > tile.housePrice[tile.getType()];
  }
  if (tile.getBuilding() == Tile::House) {
    return player->getBalance() > tile.hotelPrice[tile.getType()];
  }
  return false;
}

bool canRent(Tile& tile, std::shared_ptr<Player> player) {
  switch (tile.getBuilding()) {
    case Tile::House:
      return player->getBalance() >= tile.rentPriceHouse[tile.getType()];
    case Tile::Hotel:
      return player->getBalance() >= tile.rentPriceHotel[tile.getType()];
    default:
      return true;
  }
}

void rent(Tile& tile, std::shared_ptr<Player> player,
          void (*writeLog)(std::shared_ptr<Player>, std::string)) {
  int payedAmount;
  switch (tile.getBuilding()) {
    case Tile::House:
      pay(player, tile.getOwner(), tile.rentPriceHouse[tile.getType()]);
      payedAmount = tile.rentPriceHouse[tile.getType()];
      break;
    case Tile::Hotel:
      pay(player, tile.getOwner(), tile.rentPriceHotel[tile.getType()]);
      payedAmount = tile.rentPriceHotel[tile.getType()];
      break;
    default:
      return;
  }
  writeLog(player, "ha pagato " + std::to_string(payedAmount) +
                       " fiorini a giocatore " +
                       std::to_string(tile.getOwner()->getId()) +
                       " per pernottamento nella casella " +
                       player->positions[player->getPosition()]);
}