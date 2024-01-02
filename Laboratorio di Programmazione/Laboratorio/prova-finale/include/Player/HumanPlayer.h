#ifndef HUMANPLAYER_H
#define HUMANPLAYER_H

#include <iostream>

#include "Player.h"

class HumanPlayer : public Player {
 public:
  HumanPlayer();

  bool wantBuy() const override;
};

std::ostream& operator<<(std::ostream& os, HumanPlayer player);

#endif