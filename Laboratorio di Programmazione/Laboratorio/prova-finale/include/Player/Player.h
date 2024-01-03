//
//  Player.h
//
//  Creato da Mattia Boscolo Meneguolo (2066700)
//

#ifndef PLAYER_H
#define PLAYER_H

#include <iostream>
#include "../Game.h"

// La classe Player:
// Sta alla base delle 2 classi derivate HumanPlayer e AIPlayer,
// gestisce i vari giocatori del Monopoly tra cui:
//  - l'avanzamento nel tabellone, le transazioni e la decisione sull'acquisto delle proprietà;
//  - memorizza le informazioni di ogni giocatore (posizione e bilancio)
//
// Tutte le funzioni, fatta eccezione per wantBuy, sono comuni per ogni derivata di Player
// wantBuy verrà overloadata all'interno delle classi HumanPlayer e AIPlayer

class Player {
 public:
  enum Position{ A1 = 0, A2, A3, A4, A5, A6, A7, A8, B8, C8, D8, E8, F8, G8, H8, H7, H6, H5, H4, H3, H2, H1, G1, F1, E1, D1, C1, B1 };
  // Avanzamento giocatore
  void advance(int steps);

  // Gestione pagamenti
  void pay(int amout);
  void pay(int amount, Player &player);
  void getPayed(int amount);

  // Funzione virtuale wantBuy
  virtual bool wantBuy() const = 0;

  // Funzioni const get
  int getMoney() const { return money; }
  int getPosition() const { return position; }
  std::vector<Position> getProperties() const;

 protected:
  // Costruttore
  Player() : money{100}, position{A1} {}

  // Variabili membro
  int money;
  Position position;
};

#endif