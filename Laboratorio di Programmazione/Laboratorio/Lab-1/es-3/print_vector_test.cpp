#include <iostream>
#include <vector>
#include "print_vector.h"

int main() {
	std::vector<int> v = {1, 2, 3};
	
	print_vector("Il vettore vale: ", &v);
	
	return 0;
}
