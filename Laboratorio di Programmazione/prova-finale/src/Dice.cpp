//
//  Dice.cpp
//
//  Creato da Mattia Boscolo Meneguolo (2066700)
//

#include "../include/Dice.h"

#include <ctime>
#include <iostream>

Dice::Dice() {}

// Lancia 2 dadi, genera due numeri pseudo-randomici
// ne ricava il resto della divisione per 6 e aggiunge 1,
// si ottengono 2 numeri da 1 a 6 e si sommano
int Dice::throwDice() const {
  int dice1 = (rand() % 6) + 1;
  int dice2 = (rand() % 6) + 1;

  return dice1 + dice2;
}