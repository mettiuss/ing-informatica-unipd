//
// AIPlayer.h
//
//  Creato da Mattia Boscolo Meneguolo (2066700)
//

#ifndef AIPLAYER_H
#define AIPLAYER_H

#include <iostream>

#include "Player.h"

// Classe AIPlayer: (estende Player)
// Rapresenta il giocatore computer, eredita da player tutte le funzioni e le
// variabili membro.
// Overload della funzione wantBuy(), la quale decide se
// acquistare una proprietà, con una probabilità d'acquisto del 25%

class AIPlayer : public Player {
 public:
  AIPlayer();

  std::string wantBuy(std::string question) const override;

  std::string beginTurn() const override { return "tira"; }
};

#endif