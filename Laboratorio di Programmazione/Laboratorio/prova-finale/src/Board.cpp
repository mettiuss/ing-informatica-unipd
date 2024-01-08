#include "../include/Board.h"

#include <unistd.h>

#include <iostream>
#include <map>
#include <memory>
#include <vector>

#include "../include/Tile.h"

// Inizializzazione della Board a valori casuali.
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
Board::Board() {
  srand(time(NULL));

  // Imposta la casella di partenza che deve avere posizione costante, in alto a
  // sinistra
  tiles.push_back(Tile(Tile::StartingCorner));

  // Questo vettore tiene in memoria quante caselle per tipo sono da piazzare,
  // 8 economiche, 10 standard e 6 luxury
  std::vector<int> count{8, 10, 6};
  for (int i = 1; i < 28; i++) {
    // se i == 7, 14, 21 allora la casella è in angolo, visualizzabile dallo
    // schema in alto
    if (i % 7 == 0) {
      tiles.push_back(Tile(Tile::Corner));
      continue;
    }

    // seleziona un numero casuale tra 0 e 2
    int randomNumber = std::rand() % 3;

    // se sono già state piazzate tutte le caselle di quel tipo somma 1
    // ricorsivamente al numero casuale
    while (count[randomNumber] <= 0) {
      randomNumber = (randomNumber + 1) % 3;
    }

    // aggiorna il vettore
    count[randomNumber]--;

    // associa un indice ad un tipo
    std::map<int, Tile::TileType> types{
        {0, Tile::Economic}, {1, Tile::Standard}, {2, Tile::Luxury}};

    // aggiunge la casella casuale
    tiles.push_back(Tile(types[randomNumber]));
  }
}

// Stampa questo output
/*
    1  2  3  4  5  6  7  8
 A |>||S||E||S||E||S||E|| |
 B |L|                  |E|
 C |L|                  |E|
 D |S|                  |L|
 E |S|                  |E|
 F |L|                  |L|
 G |S|                  |S|
 H | ||S||S||L||S||E||E|| |
*/
std::ostream& operator<<(std::ostream& os, Board board) {
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
        os << board.getTiles()[j];
      }
      os << std::endl;
      continue;
    }

    if (i == 7) {
      for (int j = 21; j > 13; j--) {
        os << board.getTiles()[j];
      }
      os << std::endl;
      continue;
    }

    // riempio le righe di indice 1...6
    os << board.getTiles()[28 - i] << "   "
       << "   "
       << "   "
       << "   "
       << "   "
       << "   " << board.getTiles()[i + 7] << std::endl;
  }
  os << std::endl;

  return os;
}

bool canBuyOrUpgrade(Board& board, std::shared_ptr<Player> player) {
  Tile tile = board.getTiles()[player->getPosition()];
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

void buyOrUpgrade(Board& board, std::shared_ptr<Player> player,
                  void (*writeLog)(std::shared_ptr<Player>, std::string)) {
  Tile tile = board.getTiles()[player->getPosition()];
  if (tile.getOwner() == nullptr) {
    // acquisto
    tile.setOwner(player);
    player->removeBalance(tile.propertyPrice[tile.getType()]);

    writeLog(player, "ha acquistato il terreno " +
                         board.positions[player->getPosition()]);
    return;
  }
  if (tile.getBuilding() == Tile::None) {
    // costruisce casa
    tile.setBuilding(Tile::House);
    player->removeBalance(tile.housePrice[tile.getType()]);
    writeLog(player, "ha costruito una casa sul terreno " +
                         board.positions[player->getPosition()]);
    return;
  }
  if (tile.getBuilding() == Tile::House) {
    // costruisce albergo
    tile.setBuilding(Tile::Hotel);
    player->removeBalance(tile.hotelPrice[tile.getType()]);
    writeLog(player, "ha migliorato una casa in albergo sul terreno" +
                         board.positions[player->getPosition()]);
    return;
  }
}

bool canRent(Board& board, std::shared_ptr<Player> player) {
  Tile tile = board.getTiles()[player->getPosition()];
  switch (tile.getBuilding()) {
    case Tile::House:
      return player->getBalance() >= tile.rentPriceHouse[tile.getType()];
    case Tile::Hotel:
      return player->getBalance() >= tile.rentPriceHotel[tile.getType()];
    default:
      return true;
  }
}

void rent(Board& board, std::shared_ptr<Player> player,
          void (*writeLog)(std::shared_ptr<Player>, std::string)) {
  Tile tile = board.getTiles()[player->getPosition()];
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
                       board.positions[player->getPosition()]);
}
