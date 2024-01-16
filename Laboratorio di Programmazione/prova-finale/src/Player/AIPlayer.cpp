//
//  AIPlayer.cpp
//
//  Creato da Mattia Boscolo Meneguolo (2066700)
//

#include "../../include/Player/AIPlayer.h"

#include <ctime>
#include <iostream>

AIPlayer::AIPlayer() : Player() {}

// Esprime la volontà di acquistare / migliorare una
// proprietà per conto del giocatore IA
std::string AIPlayer::wantBuy(std::string question) const {
  
  // l'IA acquista / milgiora una proprietà con
  // una probabilità del 25%
  int probability = rand() % 4;

  return probability == 0 ? "si" : "no";
}