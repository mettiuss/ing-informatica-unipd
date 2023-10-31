#include <iostream>
#include "Rational.h"

using namespace std;

int mcd(int a, int b) {
	while (b != 0) {
		int x = b;
		b = a % b;
		a = x;
	}
	return a;
}

int mcm(int a, int b) {
	return (a * b) / mcd(a, b);
}

Rational::Rational(int num, int denom) {
    if (denom == 0) throw std::invalid_argument("Cannot divide by 0");
    numerator = num;
    denominator = denom;
}

int Rational::getNumerator() const {
    return numerator;
}

int Rational::getDenominator() const {
    return denominator;
}

void Rational::setNumerator(int num) {
    numerator = num;
}

void Rational::setDenominator(int denom) {
    if (denom == 0) throw std::invalid_argument("Cannot divide by 0");
    denominator = denom;
}

ostream& operator<<(ostream& stream, Rational operand) {
    return stream << operand.getNumerator() << "/" << operand.getDenominator();
}

void Rational::operator=(const Rational& operand) {
    numerator = operand.getNumerator();
    denominator = operand.getDenominator();
}

Rational operator+(Rational lvalue, Rational rvalue) {
    int denom = mcm(lvalue.getDenominator(), rvalue.getDenominator());
	
	int first_numerator =  (denom / lvalue.getDenominator()) * lvalue.getNumerator();
	int second_numerator = (denom / rvalue.getDenominator()) * rvalue.getNumerator();
	return Rational(first_numerator + second_numerator, denom);
}

Rational operator+(Rational lvalue, int rvalue) {
	int second_numerator = lvalue.getDenominator() * rvalue;
	return Rational(lvalue.getNumerator() + second_numerator, lvalue.getDenominator());
}

Rational operator-(Rational lvalue, Rational rvalue) {
    rvalue.setNumerator(-rvalue.getNumerator());
	return lvalue + rvalue;
}

Rational operator*(Rational lvalue, Rational rvalue) {
	return Rational(lvalue.getNumerator() * rvalue.getNumerator(), lvalue.getDenominator() * rvalue.getDenominator());
}

Rational operator/(Rational lvalue, Rational rvalue) {
	return Rational(lvalue.getNumerator() * rvalue.getDenominator(), lvalue.getDenominator() * rvalue.getNumerator());
}

bool operator==(Rational lvalue, Rational rvalue) {
	return lvalue.getNumerator() == rvalue.getNumerator() && lvalue.getDenominator() == rvalue.getDenominator();
}

bool operator>(Rational lvalue, Rational rvalue) {
	return (lvalue.getNumerator() * rvalue.getDenominator()) > (rvalue.getNumerator() * lvalue.getDenominator());
}

bool operator<(Rational lvalue, Rational rvalue) {
	return !(lvalue > rvalue);
}

double to_double(Rational operand) {
    double num = operand.getNumerator();
    double denom = operand.getDenominator();
    return num/denom;
}