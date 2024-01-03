//
//  Player.h
//
//  Creato da Mattia Boscolo Meneguolo (2066700)
//

#ifndef PLAYER_H
#define PLAYER_H

#include <iostream>

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
  int getId() const { return id; }

 protected:
  // Costruttore
  Player() : money{100}, position{0}, playerid{0}{}

  // Variabili membro
  int money, position, playerid;
  
};

#endif