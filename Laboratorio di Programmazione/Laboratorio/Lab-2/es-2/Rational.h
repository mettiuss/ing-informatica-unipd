#include <iostream>

using namespace std;

class Rational {
public:
	Rational() {};
	Rational(int num): numerator{num} {}
	Rational(int num, int denom);
	int getNumerator() const;
	int getDenominator() const;
	void setNumerator(int num);
    void setDenominator(int denom);
    void operator=(const Rational& operand);
private:
	int numerator {0}, denominator {1};
};

Rational operator+(Rational lvalue, Rational rvalue);
Rational operator+(Rational lvalue, int rvalue);
Rational operator-(Rational lvalue, Rational rvalue);
Rational operator*(Rational lvalue, Rational rvalue);
Rational operator/(Rational lvalue, Rational rvalue);
bool operator==(Rational lvalue, Rational rvalue);
bool operator<(Rational lvalue, Rational rvalue);
bool operator>(Rational lvalue, Rational rvalue);
ostream& operator<<(ostream& stream, Rational operand);
double to_double(Rational operand);