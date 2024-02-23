#include <algorithm>
#include <ctime>
#include <exception>
#include <fstream>
#include <iostream>
#include <memory>

#include "./include/Dice.h"
#include "./include/Game.h"

/* --------------------------------- Logging -------------------------------- */
// Inizializzazione file di output per i logs
std::ofstream fout("log.txt");

// Scrittura in console e su file di output
void writeLog(std::shared_ptr<Player> p, std::string value) {
  std::cout << "Giocatore " << p->getId() << " " << value << std::endl;
  fout << "Giocatore " << p->getId() << " " << value << std::endl;
}

/* ---------------------------- Input validation ---------------------------- */
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

// Passa al turno successivo, dunque al prossimo giocatore
void nextTurn(Game &game, std::shared_ptr<Player> currentPlayer,
              int &playerIndex) {
  writeLog(currentPlayer, "ha finito il turno");
  playerIndex = (playerIndex + 1) % game.getPlayers().size();
  if ((playerIndex + 1) % game.getPlayers().size() == 0) game.nextTurn();
}

bool beginTurn(Game &game, std::shared_ptr<Player> currentPlayer) {
  while (true) {
    std::string action = currentPlayer->beginTurn();

    if (action == "tira") return false;
    if (action == "quit") return true;
    if (action == "show") std::cout << game;
  }
}

bool askBuy(Game &game, std::shared_ptr<Player> currentPlayer) {
  while (true) {
    std::string nextAction = currentPlayer->wantBuy(
        questionBuyOrUpgrade(game.getBoard(), currentPlayer));

    // il giocatore non vuole acquistare
    if (nextAction == "no") return false;
    // il giocatore vuole acquistare
    if (nextAction == "si") {
      buyOrUpgrade(game.getBoard(), currentPlayer, writeLog);
      return false;
    }
    // il giocatore vuole uscire
    if (nextAction == "quit") return true;
    // il giocatore vuole vedere il tabellone
    if (nextAction == "show") std::cout << game;
  }
}

/* ---------------------------------- Main ---------------------------------- */
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

  /* ------------------------------- Game Loop ------------------------------ */
  while (!isOver(game)) {
    std::shared_ptr<Player> currentPlayer = game.getPlayers()[playerIndex];

    // chiede al giocatore se vuole lanciare i dadi
    // o visualizzare il tabellone
    bool quit = beginTurn(game, currentPlayer);

    // se il giocatore vuole uscire, la partita si interrompe
    if (quit) break;

    /* -------------------- Aggiornamento posizione -------------------- */

    // lancia i dadi -> fa avanzare il giocatore
    int steps = dice.throwDice();
    writeLog(currentPlayer, "ha tirato i dadi ottenendo un valore di " +
                                std::to_string(steps));

    int previousPos = currentPlayer->getPosition();
    currentPlayer->advance(steps);

    // stampa la casella d'arrivo
    std::shared_ptr<Tile> currentTile =
        game.getBoard().getTiles()[currentPlayer->getPosition()];

    writeLog(currentPlayer,
             "è arrivato alla casella " +
                 game.getBoard().positions[currentPlayer->getPosition()]);

    // check passaggio dal via, se passa ritira 20 fiorini
    if (previousPos < 14 && currentPlayer->getPosition() >= 14) {
      writeLog(currentPlayer, "è passato dal via e ha ritirato 20 fiorini");
      currentPlayer->addBalance(20);
    }

    /* ---------------- Gestione all'arrivo sulla casella ---------------- */

    // Se è una casella angolare, nulla si può fare
    if (currentTile->getType() == Tile::Corner ||
        currentTile->getType() == Tile::StartingCorner) {
      nextTurn(game, currentPlayer, playerIndex);
      continue;
    }

    // Se è una casella di un'altro giocatore va pagato l'affitto o il giocatore
    // viene eliminato
    if (currentTile->getOwner() != nullptr &&
        currentTile->getOwner() != currentPlayer) {
      // eliminazione del giocatore se non può pagare
      if (!canRent(game.getBoard(), currentPlayer)) {
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

    // chiede al giocatore se vuole comprare o migliorare una proprietà
    if (currentTile->getBuilding() != Tile::Hotel &&
        canBuyOrUpgrade(game.getBoard(), currentPlayer)) {
      bool quit = askBuy(game, currentPlayer);
      if (quit) break;
    }

    nextTurn(game, currentPlayer, playerIndex);
  }
  /* ---------------------------- Fine Game Loop ---------------------------- */

  /* --------------------------- schermata finale --------------------------- */
  if (game.getPlayers().size() == 1) {
    writeLog(game.getPlayers()[0], "ha vinto la partita");
    return 0;
  }
  auto players = game.getPlayers();

  std::sort(players.begin(), players.end());
  std::reverse(players.begin(), players.end());

  writeLog(players[0], "ha vinto la partita con " +
                           std::to_string(players[0]->getBalance()) +
                           " fiorini");

  int position = 1;

  for (int i = 1; i < players.size(); i++) {
    bool tie = false;
    for (int j = 0; j < players.size(); j++) {
      if (i != j && players[j]->getBalance() == players[i]->getBalance())
        tie = true;
    }

    if (tie) {
      writeLog(players[i], "ha pareggiato arrivando in posizione " +
                               std::to_string(position) + " con " +
                               std::to_string(players[i]->getBalance()) +
                               " fiorini");
    } else {
      position++;
      writeLog(players[i], "e' arrivato in posizione " +
                               std::to_string(position) + " con " +
                               std::to_string(players[i]->getBalance()) +
                               " fiorini");
    }
  }

  return 0;
}
