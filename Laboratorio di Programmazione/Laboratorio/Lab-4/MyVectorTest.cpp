#include "MyVector.h"
#include <iostream>

void arrayPrint(double a[], int length, int size){	
	std::cout << "Array: [";
	for(int i=0; i<length; i++){
		std::cout << a[i];

        if (i == (size - 1)) {
            std::cout  << "|";
        }
		
		if(i < (length-1)){
			std::cout << ", ";
		} else {
			std::cout << "]" << std::endl;
		}
	}
}

int main() {
    MyVector a = MyVector(5);

    a[3] = 2.4;

    return 0;
}
