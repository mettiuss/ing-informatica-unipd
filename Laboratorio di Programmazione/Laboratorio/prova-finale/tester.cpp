#include <iostream>

#include "./include/Board.h"
#include "./include/Dice.h"
#include "./include/Player/AIPlayer.h"
#include "./include/Player/HumanPlayer.h"

using namespace std;

void PlayerTester() {
  Dice dice = Dice();

  AIPlayer ai("CPU1");
  HumanPlayer human("TopG");

  cout << "I due giocatori sono:" << endl << human << endl << ai << endl;

  cout << human.getName() << " paga 20€ a " << ai.getName() << endl;

  human.pay(20, ai);

  cout << "I due giocatori sono:" << endl << human << endl << ai << endl;

  bool response = ai.wantBuy();
  cout << ai.getName() << " vuoi comprare la proprietà? " << response << endl;
  if (response) {
    ai.pay(40);
  }

  cout << "I due giocatori sono:" << endl << human << endl << ai << endl;

  cout << human.getName() << " vuoi comprare la proprietà? Y/n" << endl;
  response = human.wantBuy();
  if (response) {
    human.pay(40);
  }

  cout << "I due giocatori sono:" << endl << human << endl << ai << endl;

  int steps = dice.throwDice();
  cout << "Il giocatore " << human.getName() << " avanza di " << steps
       << " posizioni" << endl;
  human.advance(steps);

  steps = dice.throwDice();
  cout << "Il giocatore " << ai.getName() << " avanza di " << steps
       << " posizioni" << endl;
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