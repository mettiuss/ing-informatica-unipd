#ifndef PLAYER_H
#define PLAYER_H

#include <iostream>

class Player {
 public:
  void advance(int steps);

  void pay(int amout);
  void pay(int amount, Player &player);
  void getPayed(int amount);

  virtual bool wantBuy() const = 0;

  int getMoney() const { return money; }
  int getPosition() const { return position; }

 protected:
  Player() : money{100}, position{0} {}

  int money, position;
};

#endif