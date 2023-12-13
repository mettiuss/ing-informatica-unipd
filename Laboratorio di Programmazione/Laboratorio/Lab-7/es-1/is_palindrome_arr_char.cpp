#include <iostream>

bool is_palindrome(const char s[], int length) {
  for (int i = 0; i <= length / 2; i++) {
    if (s[i] != s[length - 1 - i]) return false;
  }
  return true;
}

int main() {
  char a[4] = {'a', 'n', 'n', 'a'};
  std::cout << is_palindrome(a, 4) << std::endl;
  char b[4] = {'b', 'n', 'n', 'a'};
  std::cout << is_palindrome(b, 4) << std::endl;
  return 0;
}