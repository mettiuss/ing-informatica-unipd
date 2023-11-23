/**
 * Date Class
 * Copyright (c) 2023 group: Laboratorio della Latrina
 *
 * Questa classe permette la creazione di oggetti di tipo Date
 * Ogni Date deve contenere diverse informazioni, quali:
 *  -Giorno
 *  -Mese
 *  -Anno
 */

#include <iostream>

class Date {
 public:
  class Invalid {};

  enum Month { jan = 1, feb, mar, apr, may, jun, jul, aug, sep, oct, nov, dec };

  //============= Costruttori ======================
  /* Costruttore di default, la data di default è 01/01/0000*/
  Date() : y{0}, m{jan}, d{1} {}

  /* Costruttore con parametri, setta anno/mese/giorno */
  Date(int yy, Month mm, int dd);

  //============= Funzioni get (const) =============
  /* Funzioni che permettono di ottenere le variabili membro private
     Le seguenti funzioni sono const => non possono in alcun modo
     modificare lo stato dell'oggetto Book */
  Month getMonth() const { return m; }
  int getDay() const { return d; }
  int getYear() const { return y; }

 private:
  int y, d;
  Month m;
};

//============= Overload operatori ===============
/* Overload operatore di confronto uguaglianza
   ritorna true <=> le due date sono uguali */
bool operator==(Date firstDate, Date secondDate);

/* Overload operatore di out stream
   permette di stampare a stream l'oggetto Date */
std::ostream &operator<<(std::ostream &stream, Date operand);

//============= LeapYear =========================
/* Funzione che ritorna una valore booleano a seconda
   se l'anno è bisestile o no. True <=> l'anno è bisestile*/
bool isLeapYear(int year);
