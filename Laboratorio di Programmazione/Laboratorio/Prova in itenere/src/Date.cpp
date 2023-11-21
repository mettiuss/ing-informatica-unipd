#include "../include/Date.h"

#include <iostream>

Date::Date(int yy, Month mm, int dd) {
  // Controlli di base
  if (mm < 1 || mm > 12) throw Invalid();
  if (dd < 1 || dd > 31) throw Invalid();

  // Aprile, Giugno, Settembre e Novembre hanno 30 giorni
  if (dd == 31 && (mm == 4 || mm == 6 || mm == 9 || mm == 11)) throw Invalid();

  // Controllo su Febbraio
  if (mm == 2 && ((dd == 29 && !isLeapYear(yy)) || dd > 29)) throw Invalid();

  y = yy;
  m = mm;
  d = dd;
}

bool isLeapYear(int year) {
  if (year % 400 == 0)
    return true;
  else if (year % 100 == 0)
    return false;
  else if (year % 4 == 0)
    return true;
  return false;
}

std::ostream &operator<<(std::ostream &stream, Date operand) {
  return stream << operand.getDay() << "/" << operand.getMonth() << "/"
                << operand.getYear();
}