#include "../../include/Player/Player.h"

#include <iostream>
#include <memory>

#include "../../include/Game.h"

int Player::playerId = 0;

void Player::advance(int steps) { position = (position + steps) % 28; }

void Player::removeBalance(int amount) { money -= amount; }

void Player::addBalance(int amount) { money += amount; }

void pay(std::shared_ptr<Player> player1, std::shared_ptr<Player> player2,
         int amount) {
  player1->removeBalance(amount);
  player2->addBalance(amount);
}

bool operator==(std::shared_ptr<Player> p1, std::shared_ptr<Player> p2) {
  return p1->getId() == p2->getId();
}

std::ostream& operator<<(std::ostream& os, std::shared_ptr<Player> p) {
  return os << "Id: " << p->getId()
            << ", Posizione: " << p->positions[p->getPosition()]
            << ", Saldo: " << p->getMoney();
}