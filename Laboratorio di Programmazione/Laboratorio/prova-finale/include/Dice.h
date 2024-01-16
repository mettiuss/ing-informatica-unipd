//
// Dice.h
//
// Creato da Mattia Boscolo Meneguolo (2066700)
//

#ifndef DICE_H
#define DICE_H

#include <iostream>

// La classe Dice viene utilizzata per creare i 2
// dadi, essenziali per lo svolgimento del gioco.
// Il suo scopo è quelli di definire, attraverso la 
// funzione throwDice(), il risultato del lancio dei dadi
// da parte dei giocatori

class Dice {
 public:
  Dice();

  int throwDice() const;
};

#endif