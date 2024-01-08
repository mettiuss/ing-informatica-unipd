#include <ctime>
#include <exception>
#include <iostream>
#include <memory>

#include "./include/Dice.h"
#include "./include/Game.h"

bool isValidInput(const std::string &input) {
  return input == "human" || input == "computer";
}

bool humanPlayer(int argc, char *argv[]) {
  if (argc != 2 || !isValidInput(argv[1])) {
    throw std::runtime_error(
        "Error! Specify one and only one parameter for the player "
        "(human/computer)");
  }
  return std::string(argv[1]) == "human";
}

void writeLog(std::shared_ptr<Player> p, std::string value) {
  std::cout << "Giocatore " << p->getId() << " " << value << std::endl;
}

void nextTurn(Game &game, std::shared_ptr<Player> currentPlayer,
              int &playerIndex) {
  writeLog(currentPlayer, "ha finito il turno");
  playerIndex = (playerIndex + 1) % game.getPlayers().size();
  if ((playerIndex + 1) % game.getPlayers().size() == 0) game.nextTurn();
}

int main(int argc, char *argv[]) {
  // inizializzazione rand
  std::srand(static_cast<unsigned int>(std::time(nullptr)));

  // Lettura args
  if (argc != 2 || !isValidInput(argv[1])) {
    std::cout << "Error! Specify one and only one parameter for the player "
                 "(human/computer)"
              << std::endl;
    return 1;
  }

  // creazione Dice
  Dice dice = Dice();

  // creazione Game
  bool human = (std::string(argv[1]) == "human");
  Game game = Game(dice, human);

  int playerIndex = 0;

  while (!isOver(game)) {
    std::shared_ptr<Player> currentPlayer = game.getPlayers()[playerIndex];

    int steps = dice.throwDice();
    writeLog(currentPlayer, "ha tirato i dadi ottenendo un valore di " +
                                std::to_string(steps));

    int previousPos = currentPlayer->getPosition();
    currentPlayer->advance(steps);

    if (previousPos > currentPlayer->getPosition()) {
      writeLog(currentPlayer, "è passato dal via e ha ritirato 20 fiorini");
      currentPlayer->addBalance(20);
    }

    Tile &currentTile =
        game.getBoard().getTiles()[currentPlayer->getPosition()];

    writeLog(currentPlayer,
             "è arrivato alla casella " +
                 game.getBoard().positions[currentPlayer->getPosition()]);

    // Check casella angolare
    if (currentTile.getType() == Tile::Corner ||
        currentTile.getType() == Tile::StartingCorner) {
      nextTurn(game, currentPlayer, playerIndex);
      continue;
    }

    // Check casella di un altro giocatore
    if (currentTile.getOwner() != nullptr &&
        currentTile.getOwner() != currentPlayer) {
      if (!canRent(game.getBoard(), currentPlayer)) {
        // eliminazione del giocatore
        game.removePlayer(currentPlayer);
        writeLog(currentPlayer, "è stato eliminato");
        nextTurn(game, currentPlayer, playerIndex);
        continue;
      }
      // pagamento affitto
      rent(game.getBoard(), currentPlayer, writeLog);
      nextTurn(game, currentPlayer, playerIndex);
      continue;
    }

    if (currentTile.getBuilding() != Tile::Hotel &&
        canBuyOrUpgrade(game.getBoard(), currentPlayer) &&
        currentPlayer->wantBuy()) {
      buyOrUpgrade(game.getBoard(), currentPlayer, writeLog);
    }

    nextTurn(game, currentPlayer, playerIndex);
  }

  // schermata vittoria o pareggio
  if (game.getPlayers().size() == 1) {
    writeLog(game.getPlayers()[0], "ha vinto la partita");
  } else {
    for (auto player : game.getPlayers()) {
      writeLog(player, "ha pareggiato");
    }
  }

  return 0;
}
