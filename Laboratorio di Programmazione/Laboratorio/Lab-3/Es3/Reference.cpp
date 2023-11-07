#include <iostream>

void print_reference(const double& dValue, const int& iValue){
	std::cout << dValue << ", " << iValue << std::endl;
}

void print_pointer(double *dValue, int *iValue){
	std::cout << *dValue << ", " << *iValue << std::endl;
}


int main(){

	int iValue = 5;
	double dValue = 2.8;
	
	print_reference(dValue, iValue);
	print_pointer(&dValue, &iValue);

	return 0;
}
