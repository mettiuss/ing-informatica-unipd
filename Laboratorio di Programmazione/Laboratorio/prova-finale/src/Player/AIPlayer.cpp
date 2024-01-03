#include "../../include/Player/AIPlayer.h"

#include <ctime>
#include <iostream>

AIPlayer::AIPlayer() : Player() {
  std::srand(static_cast<unsigned int>(std::time(nullptr)));
}

bool AIPlayer::wantBuy() const {
  int probability = rand() % 4;

  return probability == 0;
}

std::ostream& operator<<(std::ostream& os, AIPlayer player) {
  return os << "Id: " << player.getId()
            << ", Posizione: " << player.getPosition()
            << ", Saldo: " << player.getMoney();
}