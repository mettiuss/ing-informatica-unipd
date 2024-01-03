#include "../../include/Player/Player.h"
#include "../../include/Game.h"

#include <iostream>

void Player::advance(int steps) { 
  position = (position + steps) % 28;  
}

void Player::pay(int amount) { money -= amount; }

void Player::pay(int amount, Player &player) {
  money -= amount;

  player.getPayed(amount);
}

void Player::getPayed(int amount) { money += amount; }