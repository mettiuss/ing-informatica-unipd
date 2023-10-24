#include <iostream>

int global_n = 6;
int global_uninitialized_n;

int main() {
	int local_n = 2;
	static int static_initialized_n = 4;
	static int static_uninitialized_n;
	return 0;
}
