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
  if (pos.getX() > 8 || pos.getY() > 8) return false;
  if (matrix[pos.getY()][pos.getX()]) return false;
  return true;
}

void Maze::setRobotPos(Position pos) {
  if (std::abs(pos.getX() - robot.getX()) > 1) throw Invalid();
  if (std::abs(pos.getY() - robot.getY()) > 1) throw Invalid();
  if (!isPosValid(pos)) throw Invalid();
  robot = Position(pos.getX(), pos.getY());
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
      bool item = false;

      for (int i = 0; i < operand.getExits().size(); i++) {
        if (currentPos == operand.getExits()[i]) {
          stream << "E";
          item = true;
        }
      }

      if (currentPos == operand.getRobot()) {
        stream << "S";
        item = true;
      }

      if (!item) stream << (operand.getMatrix()[y][x] ? '+' : ' ');
    }
    stream << std::endl;
  }
  return stream;
}
