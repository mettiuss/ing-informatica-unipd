#include "../../include/Player/HumanPlayer.h"

#include <iostream>

HumanPlayer::HumanPlayer() : Player() {}

bool HumanPlayer::wantBuy() const {
  char response = 'y';
  char input;

  while (input != 'y' && input != 'n') {
    std::cin >> input;

    input = std::tolower(input);

    if (input == 'y' || input == 'n') {
      response = input;

    } else if (input != ' ') {
      std::cout << "Errore! Rispondere solo con 'y' o 'n'";
    }
  }

  return response == 'y';
}

std::ostream& operator<<(std::ostream& os, HumanPlayer player) {
  return os << "Posizione: " << player.getPosition()
            << ", Saldo: " << player.getMoney();
}