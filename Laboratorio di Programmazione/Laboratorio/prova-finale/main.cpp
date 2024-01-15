#include <algorithm>
#include <ctime>
#include <exception>
#include <fstream>
#include <iostream>
#include <memory>

#include "./include/Dice.h"
#include "./include/Game.h"

// Controlla la validità dell'argomento
bool isValidInput(const std::string &input) {
  return input == "human" || input == "computer";
}

// Controlla se c'è un giocatore umano o no
bool humanPlayer(int argc, char *argv[]) {
  if (argc != 2 || !isValidInput(argv[1])) {
    throw std::runtime_error(
        "Error! Specify one and only one parameter for the player "
        "(human/computer)");
  }
  return std::string(argv[1]) == "human";
}

// Inizializzazione file di output per i logs
std::ofstream fout("log.txt");

// Scrittura in console e su file di output
void writeLog(std::shared_ptr<Player> p, std::string value) {
  std::cout << "Giocatore " << p->getId() << " " << value << std::endl;
  fout << "Giocatore " << p->getId() << " " << value << std::endl;
}

// Passa al turno successivo, dunque al prossimo giocatore
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

  bool quit = false;

  // check fine partita
  while (!isOver(game)) {
    std::shared_ptr<Player> currentPlayer = game.getPlayers()[playerIndex];

    // chiede al giocatore se vuole lanciare i dadi
    // o visualizzare il tabellone
    while (true) {
      std::string action = currentPlayer->beginTurn();

      if (action == "tira") break;

      if (action == "quit") {
        quit = true;
        break;
      };
      std::cout << game;
    }

    // se il giocatore vuole uscire, la partita si interrompe
    if (quit) break;

    // lancia i dadi -> fa avanzare il giocatore
    int steps = dice.throwDice();
    writeLog(currentPlayer, "ha tirato i dadi ottenendo un valore di " +
                                std::to_string(steps));

    int previousPos = currentPlayer->getPosition();
    // il giocatore avanza di n steps
    currentPlayer->advance(steps);

    // check passaggio dal via, se passa ritira 20 fiorini
    if (previousPos < 14 && currentPlayer->getPosition() >= 14) {
      writeLog(currentPlayer, "è passato dal via e ha ritirato 20 fiorini");
      currentPlayer->addBalance(20);
    }

    // stampa la casella d'arrivo
    std::shared_ptr<Tile> currentTile =
        game.getBoard().getTiles()[currentPlayer->getPosition()];

    writeLog(currentPlayer,
             "è arrivato alla casella " +
                 game.getBoard().positions[currentPlayer->getPosition()]);

    // Check casella angolare
    if (currentTile->getType() == Tile::Corner ||
        currentTile->getType() == Tile::StartingCorner) {
      nextTurn(game, currentPlayer, playerIndex);
      continue;
    }

    // Check casella di un altro giocatore
    if (currentTile->getOwner() != nullptr &&
        currentTile->getOwner() != currentPlayer) {
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

    // check proprietà, se non è un hotel e la proprietà
    // appartiene al giocatore, chiede se vuole comprare/migliorare
    while (currentTile->getBuilding() != Tile::Hotel &&
           canBuyOrUpgrade(game.getBoard(), currentPlayer)) {
      std::string nextAction = currentPlayer->wantBuy(
          questionBuyOrUpgrade(game.getBoard(), currentPlayer));

      if (nextAction == "no") {
        // il giocatore non vuole acquistare
        break;
      } else if (nextAction == "si") {
        // il giocatore vuole acquistare
        buyOrUpgrade(game.getBoard(), currentPlayer, writeLog);
        break;
      } else if (nextAction == "quit") {
        quit = true;
        break;
      } else {
        // il giocatore vuole vedere il tabellone
        std::cout << game;
      }
    }

    nextTurn(game, currentPlayer, playerIndex);
  }

  // schermata vittoria o pareggio
  if (game.getPlayers().size() == 1) {
    writeLog(game.getPlayers()[0], "ha vinto la partita");
  } else {
    auto players = game.getPlayers();

    std::sort(players.begin(), players.end());
    std::reverse(players.begin(), players.end());
    for (int i = 0; i < players.size(); i++) {
      writeLog(players[i], "ha pareggiato arrivando in posizione " +
                               std::to_string(i + 1) + " con " +
                               std::to_string(players[i]->getBalance()) +
                               " fiorini");
    }
  }

  return 0;
}
