#include <iostream>

bool is_palindrome(const char* first, const char* last) {
  while (first < last) {
    if (*first != *last) return false;
    first++;
    last--;
  }
  return true;
}

int main() {
  char a[4] = {'a', 'n', 'n', 'a'};
  char* b = a + 3;
  std::cout << is_palindrome(a, b) << std::endl;
  char c[4] = {'b', 'n', 'n', 'a'};
  char* d = c + 3;
  std::cout << is_palindrome(c, d) << std::endl;

  return 0;
}
