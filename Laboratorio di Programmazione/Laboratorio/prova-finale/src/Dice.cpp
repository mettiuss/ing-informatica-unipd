#include "../include/Dice.h"

#include <ctime>
#include <iostream>

Dice::Dice() {  }

int Dice::throwDice() const {
  int dice1 = (rand() % 6) + 1;
  int dice2 = (rand() % 6) + 1;

  return dice1 + dice2;
}