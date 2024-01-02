#include <iostream>

#include "./include/Board.h"
#include "./include/Dice.h"
#include "./include/Game.h"
#include "./include/Player/AIPlayer.h"
#include "./include/Player/HumanPlayer.h"

using namespace std;

void PlayerTester() {
  Dice dice = Dice();

  AIPlayer ai = AIPlayer();
  HumanPlayer human = HumanPlayer();

  cout << "I due giocatori sono:" << endl << human << endl << ai << endl;

  cout << "human paga 20€ a ai" << endl;

  human.pay(20, ai);

  cout << "I due giocatori sono:" << endl << human << endl << ai << endl;

  bool response = ai.wantBuy();
  cout << "ai vuoi comprare la proprietà? " << response << endl;
  if (response) {
    ai.pay(40);
  }

  cout << "I due giocatori sono:" << endl << human << endl << ai << endl;

  cout << "human vuoi comprare la proprietà? Y/n" << endl;
  response = human.wantBuy();
  if (response) {
    human.pay(40);
  }

  cout << "I due giocatori sono:" << endl << human << endl << ai << endl;

  int steps = dice.throwDice();
  cout << "Il giocatore human avanza di " << steps << " posizioni" << endl;
  human.advance(steps);

  steps = dice.throwDice();
  cout << "Il giocatore ai avanza di " << steps << " posizioni" << endl;
  ai.advance(steps);

  cout << "I due giocatori sono:" << endl << human << endl << ai << endl;
}

void BoardTester() {
  Board board = Board();
  cout << board;
}

int main() {
  PlayerTester();
  BoardTester();

  return 0;
}