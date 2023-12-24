#include <iostream>

#include "../include/Player.h"

void Player::advance(int steps){
    position = (position + steps) % 29;
}

void Player::pay(int amount){
    money -= amount;
}

void Player::pay(int amount, Player &player){
    money -= amount;

    player.getPayed(amount);
}

void Player::getPayed(int amount){
    money += amount;
}