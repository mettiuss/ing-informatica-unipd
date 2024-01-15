//
//  Player.cpp
//
//  Creato da Mattia Boscolo Meneguolo (2066700)
//

#include "../../include/Player/Player.h"

#include <iostream>
#include <memory>

#include "../../include/Game.h"

int Player::playerId = 0;

// Funzione che gestisce l'avanzamento del giocatore nel tabellone
void Player::advance(int steps) { position = (position + steps) % 28; }

// Funzione che rimuove una quantità di soldi da conto del giocatore
void Player::removeBalance(int amount) { balance -= amount; }

// Funzione che aggiunge una quantità di soldi da conto del giocatore
void Player::addBalance(int amount) { balance += amount; }

// Funzione che gestisce il pagamento di una certa quantità
// di soldi ad un altro giocatore
void pay(std::shared_ptr<Player> player1, std::shared_ptr<Player> player2,
         int amount) {
  player1->removeBalance(amount);
  player2->addBalance(amount);
}

// Operatore di confronto, confronta l'ID tra due player
bool operator==(std::shared_ptr<Player> p1, std::shared_ptr<Player> p2) {
  if (p1 == nullptr && p2 == nullptr) return true;
  if (p1 == nullptr || p2 == nullptr) return false;
  return p1->getId() == p2->getId();
}

// Operatore <, confronta il bilancio di due giocatori
bool operator<(std::shared_ptr<Player> p1, std::shared_ptr<Player> p2) {
  if (p1 == nullptr || p2 == nullptr) return false;
  return p1->getBalance() < p2->getBalance();
}