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

  std::string getName() const { return name; }
  int getMoney() const { return money; }
  int getPosition() const { return position; }

 protected:
  Player(std::string n) : name{n}, money{100}, position{0} {}

  std::string name;
  int money, position;
};

#endif