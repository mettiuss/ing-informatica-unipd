//
//  HumanPlayer.cpp
//
//  Creato da Mattia Boscolo Meneguolo (2066700)
//

#include "../../include/Player/HumanPlayer.h"

#include <iostream>

#include "../../include/Game.h"

HumanPlayer::HumanPlayer() : Player() {}

// Chiede all'utente se intende acquistare/migliorare la proprietà
// (override della funzione wantBuy di player.h)
bool HumanPlayer::wantBuy(std::string question) const {
  std::cout << question;

  while (true) {
    char input;
    std::cin >> input;

    input = std::tolower(input);

    // Se l'input non corrisponde ad un valore atteso
    // si richiede l'immissione della risposta
    if (input != 's' && input != 'n') {
      std::cout << "Errore! Rispondere solo con 's' o 'n'";
      continue;
    }

    // Se l'input corrisponde ad 's', si procede all'acquisto/miglioramento
    // altrimenti si passa al giocatore successivo
    return input == 's';
  }
}

// Chiede all'utente la prossima azione del gioco da eseguire
// (Tirare i dati / Visualizzare il tabellone)
bool HumanPlayer::showBoard() const {
  std::cout << "Giocatore " << id
            << " specifica l'azione da eseguire:" << std::endl
            << "Tira i dadi (D)" << std::endl
            << "Visualizza il tabellone (S)";

  while (true) {
    char input;
    std::cin >> input;

    input = std::tolower(input);

    // Se l'input non corrisponde ad un valore atteso
    // si richiede l'immissione della risposta
    if (input != 's' && input != 'd') {
      std::cout << "Errore! Rispondere solo con 'S' o 'D'";
      continue;
    }

    // Se l'input corrisponde ad 's', il giocatore visualizza il tabellone
    // altrimenti lancia i dadi
    return input == 's';
  }
}