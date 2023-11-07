#include <iostream>

void print_array_length(double a[]){

	int dimension = sizeof(a)/sizeof(a[0]);
		
	std::cout << "La dimensione dell'array è di " << dimension << " elementi" << std::endl;
}

int main(){
	
	const int length = 15;
	double array[length];

	int dimension = sizeof(array)/sizeof(array[0]);
		
	std::cout << "La dimensione dell'array è di " << dimension << " elementi" << std::endl;
	
	print_array_length(array);
	
	return 0;
}
