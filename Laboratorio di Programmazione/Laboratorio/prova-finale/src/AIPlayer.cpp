#include "../include/AIPlayer.h"

#include <ctime>
#include <iostream>

bool AIPlayer::wantBuy() const {
  std::srand(static_cast<unsigned int>(std::time(nullptr)));

  int probability = rand() % 4;

  return probability == 0;
}

std::ostream& operator<<(std::ostream& os, AIPlayer player){
  return os << "Nome: " << player.getName() << std::endl << "Posizione: " << player.getPosition() << ", Saldo: " << player.getMoney();
}