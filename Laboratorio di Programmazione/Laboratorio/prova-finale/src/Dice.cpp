#include "../include/Dice.h"

#include <ctime>
#include <iostream>

Dice::Dice(){
  std::srand(static_cast<unsigned int>(std::time(nullptr)));
}

int Dice::throwDice() const {

  int result = 0;

  while (result < 1) {
    result = rand() % 12;
    std::cout << result << std::endl;
  }

  return result;
}