#include "../../include/Player/AIPlayer.h"

#include <ctime>
#include <iostream>

AIPlayer::AIPlayer(std::string n) : Player(n) {
  std::srand(static_cast<unsigned int>(std::time(nullptr)));
}

bool AIPlayer::wantBuy() const {
  int probability = rand() % 4;

  return probability == 0;
}

std::ostream& operator<<(std::ostream& os, AIPlayer player) {
  return os << "Nome: " << player.getName() << std::endl
            << "Posizione: " << player.getPosition()
            << ", Saldo: " << player.getMoney();
}