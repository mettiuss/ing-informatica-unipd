#include "./include/Maze.h"

int main() {
  Maze v = Maze("maze.txt");
  std::cout << v;

  for (int i = 0; i < v.getAvaiableMoves().size(); i++) {
    std::cout << v.getAvaiableMoves()[i];
  }
  return 0;
}