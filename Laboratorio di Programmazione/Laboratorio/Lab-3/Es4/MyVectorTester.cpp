#include <iostream>
#include "MyVector.h"


int main(){

    MyVector a = MyVector(5);

    a.safe_set(3, 2.4);    
    a.safe_set(2, 3.8);

    std::cout << "Posizione 2: " << a.safe_get(2) << std::endl;    
    std::cout << "Posizione 3: " << a[3] << std::endl;    

    a.~MyVector();

    std::cout << "Posizione 1: " << a.safe_get(1) << std::endl;    

    return 0;
}