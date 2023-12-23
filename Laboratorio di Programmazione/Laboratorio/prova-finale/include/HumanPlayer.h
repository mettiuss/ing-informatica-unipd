#ifndef HUMANPLAYER_H
#define HUMANPLAYER_H

#include <iostream>
#include "Player.h"

class HumanPlayer : public Player {
 public:
  HumanPlayer(std::string name);

  bool wantBuy();

};

#endif