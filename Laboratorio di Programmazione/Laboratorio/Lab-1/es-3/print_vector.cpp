#include <iostream>
#include <vector>
#include "print_vector.h"

void print_vector(std::string s, std::vector<int> *v) {
	std::cout << s << "[";
	for (int i = 0; i < (*v).size(); i++) {
		std::cout << (*v)[i];
		if (i != (*v).size() - 1) {
			std::cout << ", ";
		}
	}
	std::cout << "]" << std::endl;
}

void print_vector(std::string s, std::vector<int> &v) {
	std::cout << s << "[";
	for (int i = 0; i < v.size(); i++) {
		std::cout << v[i];
		if (i != v.size() - 1) {
			std::cout << ", ";
		}
	}
	std::cout << "]" << std::endl;
}

