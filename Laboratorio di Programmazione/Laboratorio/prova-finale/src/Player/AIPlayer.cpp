#include "../../include/Player/AIPlayer.h"

#include <ctime>
#include <iostream>

AIPlayer::AIPlayer() : Player() {}

bool AIPlayer::wantBuy() const {
  int probability = rand() % 4;

  return probability == 0;
}