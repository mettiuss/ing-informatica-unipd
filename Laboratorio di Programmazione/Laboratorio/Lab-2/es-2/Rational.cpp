#include <iostream>

using namespace std;

class Rational {
public:
	Rational(): numerator{0}, denominator{1} {}
	Rational(int num): numerator{num}, denominator{1} {}
	Rational(int num, int denom): numerator{num}, denominator{denom} {}
	int getNumerator() { return numerator; }
	int getDenominator() { return denominator; }
	void operator=(const Rational& element);
	Rational operator+(Rational operand);
    Rational operator+(int operand);
	Rational operator-(Rational element);
	Rational operator*(Rational element);
	Rational operator/(Rational element);
	bool operator==(Rational element);
	bool operator>(Rational element);
	bool operator<(Rational element);
	double to_double();
private:
	int numerator, denominator;
};

ostream& operator<< (ostream& stream, Rational r) {
	return stream << r.getNumerator() << "/" << r.getDenominator();
}

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

void Rational::operator=(const Rational& element) {
	numerator = element.numerator;
	denominator = element.denominator;
}

Rational Rational::operator+(Rational operand) {
	int denom = mcm(denominator, operand.denominator);
	
	int first_numerator = (denom / denominator) * numerator;
	int second_numerator = (denom / operand.denominator) * operand.numerator;
	return Rational(first_numerator + second_numerator, denom);
}

Rational Rational::operator+(int operand) {
    Rational o = Rational(operand, 1);
	
    int denom = mcm(denominator, o.denominator);
	
	int first_numerator = (denom / denominator) * numerator;
	int second_numerator = (denom / o.denominator) * o.numerator;
	return Rational(first_numerator + second_numerator, denom);
}

Rational Rational::operator-(Rational element) {
	int denom = mcm(denominator, element.denominator);
	
	int first_numerator = (denom / denominator) * numerator;
	int second_numerator = (denom / element.denominator) * element.numerator;
	return Rational(first_numerator - second_numerator, denom);
}

Rational Rational::operator*(Rational element) {
	return Rational(numerator * element.numerator, denominator * element.denominator);
}

Rational Rational::operator/(Rational element) {
	return Rational(numerator * element.denominator, denominator * element.numerator);
}

bool Rational::operator==(Rational element) {
	return element.numerator == numerator && element.denominator == denominator;
}

bool Rational::operator>(Rational element) {
	return to_double() > element.to_double();
}

bool Rational::operator<(Rational element) {
	return to_double() < element.to_double();
}


double Rational::to_double() { 
	double n = numerator;
	double d = denominator;
	return n / d;
}

int main() {
	return 0;
}

