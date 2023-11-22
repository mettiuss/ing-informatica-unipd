#include <iostream>

class Date {
 public:
  class Invalid {};
  enum Month { jan = 1, feb, mar, apr, may, jun, jul, aug, sep, oct, nov, dec };
  Date() : y{0}, m{jan}, d{1} {}
  Date(int yy, Month mm, int dd);
  Month getMonth() const { return m; }
  int getDay() const { return d; }
  int getYear() const { return y; }

 private:
  int y, d;
  Month m;
};

bool operator==(Date firstDate, Date secondDate);
std::ostream &operator<<(std::ostream &stream, Date operand);
bool isLeapYear(int year);
