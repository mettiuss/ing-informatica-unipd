#ifndef HUMANPLAYER_H
#define HUMANPLAYER_H

#include "Player.h"

#include <iostream>

class HumanPlayer : public Player {
 public:
  HumanPlayer(std::string n);

  bool wantBuy() const override;
};

std::ostream& operator<<(std::ostream& os, HumanPlayer player);

#endif