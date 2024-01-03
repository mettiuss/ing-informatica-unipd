//
//  HumanPlayer.h
//
//  Creato da Mattia Boscolo Meneguolo (2066700)
//

#ifndef HUMANPLAYER_H
#define HUMANPLAYER_H

#include <iostream>

#include "Player.h"

// Classe HumanPlayer: (estende Player)
// Rapresenta il giocatore umano, 
// eredita da player tutte le funzioni e le variabili membro.
// Overloada la funzione wantBuy(), la quale chiede al giocatore
// la conferma per l'acquisto di una proprietà

class HumanPlayer : public Player {
 public:
  HumanPlayer();

  bool wantBuy() const override;
};

std::ostream& operator<<(std::ostream& os, HumanPlayer player);

#endif