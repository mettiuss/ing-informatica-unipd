#include "MyVector.h"
#include <iostream>

//===== Costruttore =====//
MyVector::MyVector(int dimension){
    sz = dimension;
    true_sz = dimension;
    reserved = 0;
    array = new double[dimension];
}

//===== Distruttore =====//
MyVector::~MyVector(){
    delete[] array;
}

//===== Secure set & Secure get =====//
void MyVector::safe_set(int index, double value) {
    if(index >= 0 && index < sz){
        array[index] = value;
    } else {
        throw Invalid();
    }
}

double MyVector::safe_get(int index) const {
    if(index >= 0 && index < sz)
        return array[index];
    
    throw Invalid();
}

double& MyVector::safe_get_reference(int index) {
	if(index >= 0 && index < sz)
        return array[index];
    
    throw Invalid();
}

//===== Private function =====//
void MyVector::resize(int dim) {
	double* t = new double[dim];
	std::copy(array, array + sz, t);
	delete[] array;
	array = t;
}

//===== Overload operatore [] =====//
double MyVector::operator[](int index) const{
	return array[index];
}

double& MyVector::operator[](int index) {
    return array[index];
}

//===== Funzione at() =====//
double MyVector::at(int index) const {
	return safe_get(index);
}
double& MyVector::at(int index) {
	return safe_get_reference(index);
}

//===== push_back() =====//
void MyVector::push_back(double value) {
	if (true_sz == sz) {
		true_sz = true_sz * 2;
		resize(true_sz);
	}
	sz++;
	safe_set(sz - 1, value);
}

//===== pop_back() =====//
double MyVector::pop_back() {
    if (sz == 0) throw Invalid();
	double el = safe_get(sz - 1);
	if (true_sz/3 == sz && true_sz/3 > reserved) {
		true_sz = true_sz/2;
		resize(true_sz);
	}
	sz--;
	return el;
}

//===== reserve() =====//
void MyVector::reserve(int n) {
    if (n < 0) throw Invalid();
    if (true_sz < n) resize(n);
    reserved = n;
}
