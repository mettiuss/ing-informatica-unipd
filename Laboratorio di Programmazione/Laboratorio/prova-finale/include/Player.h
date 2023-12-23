#ifndef PLAYER_H
#define PLAYER_H

#include <iostream>

class Player {
 public:
  Player() = delete;

  void advance(int steps);

  void pay(int amount, Player player);
  void getPayed(int amount);

  virtual bool wantBuy() = 0;

  std::string getName() { return name; }
  int getMoney() { return money; }
  int getPosition() { return position; }

 private:
  std::string name;
  int money, position;
};

std::ostream& operator<<(std::ostream& os, Player player);
    //return os << "Nome: " << player.getName() << std::endl << "Posizione: " << player.getPosition() << ", Saldo: " << player.getMoney();

#endif