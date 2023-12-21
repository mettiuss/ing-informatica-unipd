#include "../include/RightHandRuleRobot.h"

#include <iostream>
#include <vector>

#include "../include/Maze.h"

void RightHandRuleRobot::move(Maze& maze) {
  Position robot = maze.getRobot();
  std::vector<Position> wallPositions;
  std::vector<Position> validDir;

  for (int dx = -1; dx <= 1; dx++) {
    for (int dy = -1; dy <= 1; dy++) {
      if (dx == 0 && dy == 0) continue;

      Position p = Position(robot.getX() + dx, robot.getY() + dy);

      if (maze.isRobotValidPos(p)) validDir.push_back(p);

      if (p.getX() > 8 || p.getY() > 8 || p.getX() < 0 || p.getY() < 0)
        continue;

      if (!maze.isRobotValidPos(p)) wallPositions.push_back(p);
    }
  }

  if (wallPositions.size() == 0) {
    if (this->dir == Position(0, 0)) {
      int r = rand() % (validDir.size() - 1);
      this->dir = validDir[r];
    }
    maze.setRobotPos(Position(robot.getX() + this->dir.getX(),
                              robot.getY() + this->dir.getY()));
    return;
  } else {
    std::vector<Position> rightAntiClockWise = {
        Position(1, 0),  Position(1, -1), Position(0, -1), Position(-1, -1),
        Position(-1, 0), Position(-1, 1), Position(0, 1),  Position(1, 1)};
    bool wallFound = false;

    // std::cout << robot << std::endl;

    for (int i = 0; i < 8; i++) {
      Position p = Position(robot.getX() + rightAntiClockWise[i].getX(),
                            robot.getY() + rightAntiClockWise[i].getY());
      bool isWallInPos = false;

      for (int j = 0; j < wallPositions.size(); j++) {
        if (wallPositions[j] == p) isWallInPos = true;
      }

      // for (Position i : wallPositions) std::cout << i << ' ';
      // std::cout << std::endl << p << isWallInPos << wallFound << std::endl;

      if (isWallInPos && !wallFound) wallFound = true;
      if (!isWallInPos && wallFound) {
        maze.setRobotPos(p);
        return;
      }
    }
  }
}