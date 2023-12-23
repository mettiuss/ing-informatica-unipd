#ifndef IAPLAYER_H
#define IALAYER_H

#include <iostream>
#include "Player.h"

class IaPlayer : public Player {
 public:
  IaPlayer(std::string name);

  bool wantBuy();

};

#endif