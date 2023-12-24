#ifndef AIPLAYER_H
#define AIPLAYER_H

#include <iostream>
#include "Player.h"

class AIPlayer : public Player {
 public:
  AIPlayer(std::string n);

  bool wantBuy() const override;

};

std::ostream& operator<<(std::ostream& os, AIPlayer player);

#endif