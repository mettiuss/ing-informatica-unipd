#include "../../include/Player/HumanPlayer.h"

#include <iostream>

#include "../../include/Game.h"

HumanPlayer::HumanPlayer() : Player() {}

bool HumanPlayer::wantBuy(std::string question) const {
  std::cout << question;

  while (true) {
    char input;
    std::cin >> input;

    input = std::tolower(input);

    if (input != 's' && input != 'n') {
      std::cout << "Errore! Rispondere solo con 's' o 'n'";
      continue;
    }

    return input == 's';
  }
}

bool HumanPlayer::showBoard() const {
  std::cout << "Giocatore " << id
            << " specifica l'azione da eseguire:" << std::endl
            << "Tira i dadi (D)" << std::endl
            << "Visualizza il tabellone (S)";

  while (true) {
    char input;
    std::cin >> input;

    input = std::tolower(input);

    if (input != 's' && input != 'd') {
      std::cout << "Errore! Rispondere solo con 'S' o 'D'";
      continue;
    }

    return input == 's';
  }
}