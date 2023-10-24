#include <iostream>
#include <vector>
#include "../es-3/print_vector.h"
#include "fibonacci.h"

int main() {
	std::vector<int> v;
	
	fibonacci(1, 2, v, 5);
	
	print_vector("La sequenza di fibonacci richiesta è: ", v);
	
	return 0;
}