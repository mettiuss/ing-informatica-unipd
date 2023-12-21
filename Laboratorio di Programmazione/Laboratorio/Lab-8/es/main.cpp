#include <unistd.h>

#include "./include/Maze.h"
#include "./include/RightHandRuleRobot.h"

int main() {
  srand(time(NULL));

  Maze m = Maze("maze.txt");
  std::cout << m << "\x1b[9A";

  int i = 0;
  while (!m.robotInExit()) {
    usleep(100000);
    RightHandRuleRobot r = RightHandRuleRobot();
    r.move(m);
    std::cout << m << "\x1b[9A";
    i++;
  }

  std::cout << m << "in " << i << " moves" << std::endl;
  return 0;
}