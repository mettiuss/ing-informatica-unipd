#include <iostream>

#include "Link.h"

void printLink(std::string name, Link* n) {
  std::cout << "name:" << name << std::endl
            << "prev:"
            << ((n->previous() != nullptr) ? n->previous()->value : "null")
            << std::endl
            << "succ:" << ((n->next() != nullptr) ? n->next()->value : "null")
            << std::endl
            << std::endl;
}

int main() {
  // basic setup
  Link* first = new Link("First");
  Link* last = new Link("Last");
  Link* current = new Link("This");
  current->insert(first);
  current->add(last);

  printLink("This", current);
  printLink("First", first);
  printLink("Last", last);
  return 0;
}