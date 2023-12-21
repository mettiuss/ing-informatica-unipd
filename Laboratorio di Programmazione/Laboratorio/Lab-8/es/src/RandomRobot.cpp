#include "../include/RandomRobot.h"

#include <iostream>
#include <vector>

#include "../include/Maze.h"

void RandomRobot::move(Maze& maze) {
  Position robot = maze.getRobot();
  std::vector<Position> validPositions;

  for (int dx = -1; dx <= 1; dx++) {
    for (int dy = -1; dy <= 1; dy++) {
      if (dx == 0 && dy == 0) continue;
      Position p = Position(robot.getX() + dx, robot.getY() + dy);
      if (maze.isRobotValidPos(p)) validPositions.push_back(p);
    }
  }
  int random = rand() % (validPositions.size() - 1);
  maze.setRobotPos(validPositions[random]);
}