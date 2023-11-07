#include <iostream>

void arrayPrint(int a[], int length){
	
	std::cout << "Array: [";
	for(int i=0; i<length; i++){
		std::cout << a[i];
		
		if(i < (length-1)){
			std::cout << ", ";
		} else {
			std::cout << "]" << std::endl;
		}
	}

}

void f(){
	
	int cArray[10] = {0,0,0,0,0,0,0,0,0,0};
	
	int* pointer = &cArray[5];
	
	pointer[2] = 9;
	pointer[-3] = 5;
	
	arrayPrint(cArray, 10);
	
}

void f_illegal(){
	int illegalArray[10] = {0,0,0,0,0,0,0,0,0,0};
	
	int* pointer = &illegalArray[4];
	
	pointer[8] = 9;
	pointer[9] = 5;
	
	arrayPrint(illegalArray, 10);
}

int main(){

	//f();		 //Funzione che scrive dei valori in un array, 
				 //attraverso un puntatore ad una cella i-esima
	
	f_illegal(); //Funzione che scrive fuori dai limiti dell'array 	
	
	return 0;
}
