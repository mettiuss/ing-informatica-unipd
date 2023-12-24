#include <iostream>

#include "./include/HumanPlayer.h"
#include "./include/AIPlayer.h"

using namespace std;

void PlayerTester(){
    AIPlayer ai("CPU1");
    HumanPlayer human("TopG");

    cout << "I due giocatori sono:" << endl << human << endl << ai << endl;

    cout << human.getName() << " paga 20€ a " << ai.getName() << endl;

    human.pay(20, ai);

    cout << "I due giocatori sono:" << endl << human << endl << ai << endl;

    bool response = ai.wantBuy();
    cout << ai.getName() << " vuoi comprare la proprietà? " << response << endl;
    if(response){
        ai.pay(40);
    }

    cout << "I due giocatori sono:" << endl << human << endl << ai << endl;

    cout << human.getName() << " vuoi comprare la proprietà? Y/n" << endl;
    response = human.wantBuy();
    if(response){
        human.pay(40);
    }

    cout << "I due giocatori sono:" << endl << human << endl << ai << endl;

    cout << "Il giocatore " << human.getName() << " avanza di 4 posizioni" << endl;
    human.advance(4);
    
    cout << "Il giocatore " << ai.getName() << " avanza di 6 posizioni" << endl;
    ai.advance(6);

    cout << "I due giocatori sono:" << endl << human << endl << ai << endl;


}

int main(){

    PlayerTester();
    
    return 0;
}