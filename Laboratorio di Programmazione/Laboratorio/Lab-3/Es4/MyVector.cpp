#include "MyVector.h"

//===== Costruttore =====//
MyVector::MyVector(int dimension){
    sz = dimension;
    array = new double[dimension];
}
//=======================//

//===== Distruttore =====//
MyVector::~MyVector(){
    delete[] array;
}
//=======================//

//===== Secure set & Secure get =====//
void MyVector::safe_set(int index, double value){        //Throw an exception if position is out of bound
    if(index >= 0 && index < sz){
        array[index] = value;
    } else {
        throw Invalid();
    }
}

double MyVector::safe_get(int index){                    //Throw an exception if position is out of bound
    if(index >= 0 && index < sz)
        return array[index];
    
    throw Invalid();
}
//===================================//

//===== Overload ooperatore [] =====//
double MyVector::operator[](int index) const{
    if(index >= 0 && index < sz)
        return array[index];

    throw Invalid();
}
//==================================//
 
//===== size =====//
int MyVector::size() const{ return sz; }            //Return the safe of MyVector
//================//