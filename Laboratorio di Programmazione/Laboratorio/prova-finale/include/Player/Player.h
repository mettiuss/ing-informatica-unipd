//
//  Player.h
//
//  Creato da Mattia Boscolo Meneguolo (2066700)
//

#ifndef PLAYER_H
#define PLAYER_H

#include <iostream>
#include <memory>

// La classe Player:
// Sta alla base delle 2 classi derivate HumanPlayer e AIPlayer,
// gestisce i vari giocatori del Monopoly tra cui:
//  - l'avanzamento nel tabellone, le transazioni e la decisione
//  sull'acquisto delle proprietà;
//  - memorizza le informazioni di ogni giocatore (posizione e bilancio)
//
// Tutte le funzioni, fatta eccezione per wantBuy, sono comuni per ogni
// derivata di Player wantBuy verrà overloadata all'interno delle classi
// HumanPlayer e AIPlayer

class Player {
 public:
  // Avanzamento giocatore
  void advance(int steps);

  // Gestione pagamenti
  void addBalance(int amount);
  void removeBalance(int amout);

  // Funzione virtuale wantBuy
  virtual bool wantBuy() const = 0;

  // Funzioni const get
  int getBalance() const { return balance; }
  int getPosition() const { return position; }
  int getId() const { return id; }

 protected:
  // Costruttore
  Player() : balance{100}, position{0}, id{playerId++} {}

  // Variabili membro
  int balance, position, id;
  static int playerId;
};

std::string getHumanPosition(std::shared_ptr<Player> player);

void pay(std::shared_ptr<Player> player1, std::shared_ptr<Player> player2,
         int amount);

bool operator==(std::shared_ptr<Player> p1, std::shared_ptr<Player> p2);

std::ostream& operator<<(std::ostream& os, std::shared_ptr<Player> p);

#endif