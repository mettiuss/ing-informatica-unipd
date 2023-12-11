#include "../include/Maze.h"

#include <fstream>
#include <iostream>
#include <vector>

std::vector<int> Maze::Position::getPos() {
  std::vector<int> v{x, y};
  return v;
}

void Maze::Position::setPos(int xPos, int yPos) {
  x = xPos;
  y = yPos;
}

std::ostream &operator<<(std::ostream &stream, Maze::Position operand) {
  return stream << "(" << operand.getPos()[0] << ", " << operand.getPos()[1]
                << ")";
}

bool operator==(Maze::Position firstPos, Maze::Position secondPos) {
  std::vector<int> a = firstPos.getPos();
  std::vector<int> b = secondPos.getPos();
  return a[0] == b[0] && a[1] == b[1];
}

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

bool Maze::isPosValid(int x, int y) {
  if (x > 8 || y > 8) return false;
  if (matrix[y][x]) return false;
  return true;
}

void Maze::setRobotPos(int x, int y) {
  std::vector<int> rPos = robot.getPos();
  if (std::abs(x - rPos[0]) > 1) throw Invalid();
  if (std::abs(y - rPos[1]) > 1) throw Invalid();
  if (!isPosValid(x, y)) throw Invalid();
  robot = Position(x, y);
}

bool Maze::robotInExit() {
  for (int i = 0; i < exits.size(); i++) {
    if (robot == exits[i]) return true;
  }
  return false;
}

std::vector<Maze::Position> Maze::getAvaiableMoves() {
  std::vector<Position> v;
  for (int dx = -1; dx < 2; dx++) {
    for (int dy = -1; dy < 2; dy++) {
      if (dx == 0 && dy == 0) continue;
      int x = robot.getPos()[0] + dx;
      int y = robot.getPos()[1] + dy;
      if (isPosValid(x, y)) v.push_back(Position(x, y));
    }
  }
  return v;
}

std::ostream &operator<<(std::ostream &stream, Maze operand) {
  for (int y = 0; y < operand.getMatrix().size(); y++) {
    for (int x = 0; x < operand.getMatrix()[y].size(); x++) {
      Maze::Position currentPos = Maze::Position(x, y);
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
