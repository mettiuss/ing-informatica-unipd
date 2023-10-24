#include <iostream>

void call_counter() {
    static int counter = 0;
    counter++;
    std::cout << counter << std::endl;
}
