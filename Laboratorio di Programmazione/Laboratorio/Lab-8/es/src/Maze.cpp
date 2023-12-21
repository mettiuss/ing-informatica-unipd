#include "../include/Maze.h"

#include <fstream>
#include <iostream>
#include <vector>

Maze::Maze(std::string fileName) {
  std::vector<bool> emptyVector(9, false);
  std::vector<std::vector<bool>> emptyMatrix(9, emptyVector);
  matrix = emptyMatrix;

  std::ifstream fin(fileName);
  char c;
  int x = 0, y = 0;
  while (fin.get(c)) {
    if (x > 9) throw Invalid();
    if (y > 8) throw Invalid();
    switch (c) {
      case '*':
        matrix[y][x] = true;
        x++;
        break;
      case ' ':
        matrix[y][x] = false;
        x++;
        break;
      case '\n':
        x = 0;
        y++;
        break;
      case 'E':
        matrix[y][x] = false;
        exits.push_back(Position(x, y));
        x++;
        break;
      case 'S':
        matrix[y][x] = false;
        robot = Position(x, y);
        x++;
        break;
    }
  }
  fin.close();
}

bool Maze::isPosValid(Position pos) {
  if (pos.getX() > 8 || pos.getY() > 8 || pos.getX() < 0 || pos.getY() < 0)
    return false;
  if (matrix[pos.getY()][pos.getX()]) return false;
  return true;
}

void Maze::setRobotPos(Position pos) {
  if (!isRobotValidPos(pos)) throw Invalid();
  robot = pos;
}

bool Maze::isRobotValidPos(Position pos) {
  if (std::abs(pos.getX() - robot.getX()) > 1) return false;
  if (std::abs(pos.getY() - robot.getY()) > 1) return false;
  if (!isPosValid(pos)) return false;
  return true;
}

bool Maze::robotInExit() {
  for (int i = 0; i < exits.size(); i++) {
    if (robot == exits[i]) return true;
  }
  return false;
}

std::ostream &operator<<(std::ostream &stream, Maze operand) {
  for (int y = 0; y < operand.getMatrix().size(); y++) {
    for (int x = 0; x < operand.getMatrix()[y].size(); x++) {
      Position currentPos = Position(x, y);
      bool robot = false;
      bool exit = false;

      for (int i = 0; i < operand.getExits().size(); i++) {
        if (currentPos == operand.getExits()[i]) {
          exit = true;
        }
      }

      if (currentPos == operand.getRobot()) {
        robot = true;
      }

      if (robot && exit)
        stream << "&";
      else if (robot)
        stream << "R";
      else if (exit)
        stream << "E";
      else
        stream << (operand.getMatrix()[y][x] ? '+' : ' ');
    }
    stream << std::endl;
  }
  return stream;
}
