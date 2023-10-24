#include <iostream>
#include <vector>
#include "../es-3/print_vector.h"
#include "../es-4/fibonacci.h"

int main() {
    std::vector<int> v;
    std::vector<int> results {1, 2};
    while (results.back() > 0) {
        fibonacci(results[results.size() - 2], results[results.size() - 1], v, 3);
        results.push_back(v.back());
    }

    results.pop_back();

    print_vector("Fibonacci numbers that can be rapresented by a 32 bit integer: ", results);
    std::cout << "Appoximation of biggest number rapresentable by a 32 bit integer: " << results.back() << std::endl;
    return 0;
}