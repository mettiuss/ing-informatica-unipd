//
// AIPlayer.h
//
//  Creato da Mattia Boscolo Meneguolo (2066700)
//

#ifndef AIPLAYER_H
#define AIPLAYER_H

#include <iostream>

#include "Player.h"

// Classe HumanPlayer: (estende Player)
// Rapresenta il giocatore umano, 
// eredita da player tutte le funzioni e le variabili membro.
// Overloada la funzione wantBuy(), la quale decide se acquistare
// una proprietà, con una probabilità d'acquisto del 25%

class AIPlayer : public Player {
 public:
  AIPlayer();

  bool wantBuy() const override;
};

std::ostream& operator<<(std::ostream& os, AIPlayer player);

#endif