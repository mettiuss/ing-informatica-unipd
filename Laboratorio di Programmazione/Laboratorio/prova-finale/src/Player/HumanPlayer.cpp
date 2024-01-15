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
std::string HumanPlayer::wantBuy(std::string question) const {
  std::cout << question;

  while (true) {
    std::string input;
    std::cin >> input;

    // Converto input in lowercase
    for (int i = 0; i < input.length(); i++) {
      input[i] = std::tolower(input[i]);
    }

    // Se l'input non corrisponde ad un valore atteso
    // si richiede l'immissione della risposta
    if (input != "si" && input != "no" && input != "show" && input != "quit") {
      std::cout << "Errore! Rispondere solo con 'Si', 'No', 'Show' per vedere "
                   "il tabellone o 'Quit' per uscire dalla partita\n";
      continue;
    }

    return input;
  }
}

// Chiede all'utente la prossima azione del gioco da eseguire
// (Tirare i dati / Visualizzare il tabellone)
std::string HumanPlayer::beginTurn() const {
  std::cout << "Giocatore " << id
            << " specifica l'azione da eseguire:" << std::endl
            << "Tira i dadi (Tira)" << std::endl
            << "Visualizza il tabellone (Show)" << std::endl
            << "Esci dalla partita (Quit)" << std::endl;

  while (true) {
    std::string input;
    std::cin >> input;

    // Converto input in lowercase
    for (int i = 0; i < input.length(); i++) {
      input[i] = std::tolower(input[i]);
    }

    // Se l'input non corrisponde ad un valore atteso
    // si richiede l'immissione della risposta
    if (input != "tira" && input != "show" && input != "quit") {
      std::cout << "Errore! Rispondere solo con 'Tira', 'Show' o 'Quit";
      continue;
    }

    // Se l'input corrisponde ad 's', il giocatore visualizza il tabellone
    // altrimenti lancia i dadi
    return input;
  }
}