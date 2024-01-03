#include "../../include/Player/Player.h"
#include "../../include/Game.h"

#include <iostream>

void Player::advance(int steps) { 
  position = static_cast<Player::Position>((position + steps) % 28);  
}

void Player::pay(int amount) { money -= amount; }

void Player::pay(int amount, Player &player) {
  money -= amount;

  player.getPayed(amount);
}

std::vector<Player::Position> Player::getProperties() const {
  
}

void Player::getPayed(int amount) { money += amount; }