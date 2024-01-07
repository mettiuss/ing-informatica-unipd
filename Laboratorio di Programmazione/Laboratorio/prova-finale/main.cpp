#include <ctime>
#include <exception>
#include <iostream>
#include <memory>

#include "./include/Dice.h"
#include "./include/Game.h"

bool humanPlayer(int argc, char *argv[]) {
  if (argc != 2) {
    throw std::runtime_error(
        "Errore! Specificare uno e un solo parametro per chi giocherà la "
        "partita (human/computer)");
  }

  std::string stringa = argv[1];

  if (stringa != "human" && stringa != "computer")
    throw std::runtime_error(
        "Errore! Specificare uno e un solo parametro per chi giocherà la "
        "partita (human/computer)");

  return stringa == "human";
}

int main(int argc, char *argv[]) {
  std::srand(static_cast<unsigned int>(std::time(nullptr)));
  bool human;
  try {
    human = humanPlayer(argc, argv);
  } catch (const std::runtime_error &e) {
    std::cout << e.what() << std::endl;
    return 1;
  }

  // creare Dice
  Dice dice = Dice();

  Game game = Game(dice, human);

  // turno di gioco
  int iPlayer = 0;

  while (!isOver(game)) {
    auto currentPlayer = game.getPlayers()[iPlayer];
    int steps = dice.throwDice();
    std::cout << "Giocatore " << currentPlayer->getId()
              << " ha tirato i dadi ottenendo un valore di " << steps
              << std::endl;

    int previousPos = currentPlayer->getPosition();
    currentPlayer->advance(steps);

    if (previousPos > currentPlayer->getPosition()) {
      std::cout << "Giocatore " << currentPlayer->getId()
                << " è passato dal via e ha ritirato 20 fiorini" << std::endl;
      currentPlayer->addBalance(20);
    }

    auto &currentTile = game.getTiles()[currentPlayer->getPosition()];
    if (currentTile.getType() == Tile::Corner ||
        currentTile.getType() == Tile::StartingCorner) {
      continue;
    }

    if (currentTile.getOwner() == currentPlayer ||
        currentTile.getOwner() == nullptr) {
      if (currentTile.getBuilding() != Tile::Hotel &&
          currentPlayer->wantBuy()) {
        buyOrUpgrade(currentTile, currentPlayer);
      }
    } else {
      if (canRent(currentTile, currentPlayer))
        rent(currentTile, currentPlayer);
      else
        game.removePlayer(currentPlayer);
    }

    if ((iPlayer + 1) % game.getPlayers().size() == 3) game.nextTurn();
    iPlayer = (iPlayer + 1) % game.getPlayers().size();
  }

  // schermata vittoria o pareggio

  return 0;
}
