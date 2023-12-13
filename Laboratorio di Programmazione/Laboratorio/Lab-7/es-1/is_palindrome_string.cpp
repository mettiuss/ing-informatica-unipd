#include <iostream>

bool is_palindrome(const std::string &s) {
  std::string temp = "";
  for (int i = s.length(); i >= 0; i--) {
    temp += s[i];
  }
  return temp == s;
}

int main() {
  std::cout << is_palindrome("anna") << std::endl;
  std::cout << is_palindrome("bnna") << std::endl;
  return 0;
}